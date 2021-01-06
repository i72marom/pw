package es.uco.pw.Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import es.uco.pw.business.*;
import es.uco.pw.data.*;
import es.uco.pw.data.dao.DAOException;
import es.uco.pw.data.mysqldao.MySQLDAOManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.business.anuncio.AnuncioFlash;
import es.uco.pw.business.anuncio.AnuncioGeneral;
import es.uco.pw.business.anuncio.AnuncioIndividualizado;
import es.uco.pw.business.anuncio.AnuncioTematico;
import es.uco.pw.business.anuncio.Generador;
import es.uco.pw.business.contacto.Contacto;
import es.uco.pw.business.tipos.Estado;
import es.uco.pw.business.tipos.Tipo;

/**
 * Servlet implementation class CrearAnuncioServlet
 */
//@WebServlet("/Crear")
public class CrearAnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrearAnuncioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getParameter("titulo") == null || request.getParameter("contenido") == null)
		{
			request.getRequestDispatcher("/mvc/view/crearAnuncio.jsp").forward(request, response);
		}
		else
		{
			
			HttpSession objSession = request.getSession(false);
			Contacto userLogged = (Contacto) objSession.getAttribute("usuarioLogeado");
			
			
			Generador anuncio = new Generador();
			MySQLDAOManager manager = null;
			try {
				manager = MySQLDAOManager.getInstance();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String titulo = request.getParameter("titulo");
			String contenido = request.getParameter("contenido");
			String tipo = request.getParameter("tipoAnuncio");

			if(tipo.equals("general"))
			{
				AnuncioGeneral a = (AnuncioGeneral) anuncio.creaAnuncioGeneral();
				a.setAutor(userLogged);
				a.setCuerpo(contenido);
				a.setEstado(Estado.publicado);
				a.setFecha(LocalDate.now());
				a.setTipo(Tipo.general);
				a.setTitulo(titulo);

				
				try {
					manager.getAnuncioDAO().insertar(a);
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(tipo.equals("tematico"))
			{
				
				String[] temas= request.getParameterValues("tema");
				ArrayList<String> temasArrayList = new ArrayList<String>();
				
				for(int i = 0;i<temas.length;i++)
				{
					temasArrayList.add(temas[i]);
				}
				

				AnuncioTematico a = (AnuncioTematico) anuncio.creaAnuncioTematico();
				a.setTitulo(titulo);
				a.setCuerpo(contenido);
				a.setFecha(LocalDate.now());
				a.setAutor(userLogged);
				a.setTipo(Tipo.tematico);
				a.setEstado(Estado.publicado);
				a.setTemas(temasArrayList);
				
				
				try {
					manager.getAnuncioDAO().insertar(a);
				}catch (DAOException e){
					e.printStackTrace();
				}
			}
			else if(tipo.equals("flash"))
			{
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				
				AnuncioFlash a = (AnuncioFlash) anuncio.creaAnuncioFlash();
				
				String fechaInicio = request.getParameter("fechaInicio");
				String fechaFin = request.getParameter("fechaFin");
				
				LocalDate fechaInicioLocalDate = LocalDate.parse(fechaInicio, formatter);
				LocalDate fechaFinLocalDate = LocalDate.parse(fechaFin, formatter);
				a.setCuerpo(contenido);
				a.setTitulo(titulo);
				a.setFecha(LocalDate.now());
				a.setAutor(userLogged);
				a.setTipo(Tipo.flash);
				a.setEstado(Estado.publicado);
				a.setFechaInicio(fechaInicioLocalDate);
				a.setFechaFin(fechaFinLocalDate);
				
				
				try {
					manager.getAnuncioDAO().insertar(a);
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(tipo.equals("individualizado"))
			{
				AnuncioIndividualizado a = (AnuncioIndividualizado) anuncio.creaAnuncioIndividualizado();
				a.setCuerpo(contenido);
				a.setTitulo(titulo);
				a.setFecha(LocalDate.now());
				a.setAutor(userLogged);
				a.setTipo(Tipo.individualizado);
				a.setEstado(Estado.publicado);
				
				String ids = request.getParameter("idsDestinatarios");
				String[] idsArray = ids.split(",");
				
				ArrayList<Long> destinatarios = new ArrayList<Long>();
				

				destinatarios.add(userLogged.getId());
				for(int i = 0;i<idsArray.length;i++)
				{
					destinatarios.add(Long.parseLong(idsArray[i]));
					
				}
				
				a.setDestinatarios(destinatarios);
				
				
				try {
					manager.getAnuncioDAO().insertar(a);
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			

			
			/*try {
				manager.getAnuncioDAO().insertar(a);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			response.sendRedirect("Tablon");
			
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String titulo=request.getParameter("titulo");
		String contenido=request.getParameter("contenido");
		
		
		if(request.getParameter("titulo") == null || request.getParameter("contenido") == null || request.getParameter("titulo").equals("") || request.getParameter("contenido").equals(""))
		{
			request.getRequestDispatcher("/mvc/view/crearAnuncio.jsp").forward(request, response);
		}
		else
		{
			doGet(request, response);
		}
		
		
	}

}
