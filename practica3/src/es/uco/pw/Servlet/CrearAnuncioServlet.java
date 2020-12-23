package es.uco.pw.Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

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

import es.uco.pw.business.anuncio.AnuncioIndividualizado;
import es.uco.pw.business.anuncio.Generador;
import es.uco.pw.business.contacto.Contacto;
import es.uco.pw.business.tipos.Estado;
import es.uco.pw.business.tipos.Tipo;

/**
 * Servlet implementation class CrearAnuncioServlet
 */
@WebServlet("/Crear")
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
				manager = new MySQLDAOManager();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			AnuncioIndividualizado a = (AnuncioIndividualizado) anuncio.creaAnuncioIndividualizado();
			
			request.getAttribute("userLogged");
			
			String inputString = "11,10,13";
			String[] indicesSeleccionados = inputString.split(",");
			ArrayList<Long> destinatarios = new ArrayList<Long>();
			for(int i = 0;i<indicesSeleccionados.length;i++)
			{
				
				//System.out.println(gestor.buscarContactoPorId());
				destinatarios.add(Long.parseLong(indicesSeleccionados[i]));
			}
			
			
			LocalDate fechaActual = LocalDate.now();
			
			
			//a.setId(Long.valueOf(id));
			a.setCuerpo(request.getParameter("contenido"));
			a.setTitulo(request.getParameter("titulo"));
			a.setFecha(fechaActual);
			a.setAutor(userLogged);
			a.setTipo(Tipo.individualizado);
			a.setDestinatarios(destinatarios);
			a.setEstado(Estado.publicado);
			
			
			try {
				manager.getAnuncioDAO().insertar(a);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
