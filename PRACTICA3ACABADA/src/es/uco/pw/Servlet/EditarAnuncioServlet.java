package es.uco.pw.Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import es.uco.pw.business.anuncio.Anuncio;
import es.uco.pw.business.anuncio.AnuncioFlash;

import es.uco.pw.business.anuncio.AnuncioTematico;


import es.uco.pw.business.tipos.Tipo;
import es.uco.pw.data.dao.DAOException;
import es.uco.pw.data.mysqldao.MySQLDAOManager;

/**
 * Servlet implementation class EditarAnuncioServlet
 */
//@WebServlet("/Editar")
public class EditarAnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarAnuncioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		if(request.getParameter("idAnuncio") != null)
		{
			if(request.getParameter("tipoAnuncio") == null) 
				request.getRequestDispatcher("/mvc/view/editarAnuncio.jsp").forward(request, response);
			else
			{
				MySQLDAOManager manager = null;
				
				try {
					manager = MySQLDAOManager.getInstance();
					String titulo = request.getParameter("titulo");
					String contenido = request.getParameter("contenido");

					
					
					Long idLong = Long.parseLong(request.getParameter("idAnuncio"));
					
					Anuncio a = manager.getAnuncioDAO().obtener(idLong);
					
					a.setTitulo(titulo);
					a.setCuerpo(contenido);
					
					if(a.getTipo() == Tipo.flash)
					{
						AnuncioFlash b = (AnuncioFlash) a;
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						
						String fechaFin = request.getParameter("fechaFin");
						
						LocalDate fechaFinLocalDate = LocalDate.parse(fechaFin, formatter);
						

						manager.getAnuncioDAO().updateFechas(b.getFechaInicio(), fechaFinLocalDate, a.getId());
						
					}
					else if(a.getTipo() == Tipo.individualizado)
					{
					
						
						String idsDestinatarios = request.getParameter("idsDestinatarios");
						
						String idsDestinatariosArray[] = idsDestinatarios.split(",");
						
						manager.getAnuncioDAO().borrarDestinatarios(a.getId());
						
						for(int i = 0;i<idsDestinatariosArray.length;i++)
						{
							manager.getAnuncioDAO().agregarDestinatario(Long.parseLong(idsDestinatariosArray[i]), a.getId());
						}
						
					}
					else if(a.getTipo() == Tipo.tematico)
					{
						AnuncioTematico b  = (AnuncioTematico) a;
						
						
						
						String[] temas = request.getParameterValues("tema");
						ArrayList<String> temasArrayList = new ArrayList<String>();
						for(int i = 0;i<temas.length;i++)
							temasArrayList.add(temas[i]);
					

						b.setTemas(temasArrayList);
						
						
						
						manager.getAnuncioDAO().modificarTags(a.getId(), temasArrayList);
						
					}
					
					manager.getAnuncioDAO().Modificar(a);
					
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		response.sendRedirect(request.getContextPath()+"/MisAnuncios");
		
	}

}
