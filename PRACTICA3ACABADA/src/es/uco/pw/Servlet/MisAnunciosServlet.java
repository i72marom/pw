package es.uco.pw.Servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.business.anuncio.Anuncio;
import es.uco.pw.business.anuncio.GestorAnuncios;
import es.uco.pw.business.contacto.Contacto;
import es.uco.pw.business.tipos.Estado;
import es.uco.pw.data.dao.DAOException;
import es.uco.pw.data.mysqldao.MySQLDAOManager;

/**
 * Servlet implementation class MisAnunciosServlet
 */
//@WebServlet("/MisAnuncios")
public class MisAnunciosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MisAnunciosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		if(request.getParameter("accion") != null && request.getParameter("idAnuncio") != null)
		{
			if(!request.getParameter("accion").equals("") && !request.getParameter("idAnuncio").equals(""))
			{
				HttpSession objSession = request.getSession(false);
				Contacto userLogged = (Contacto) objSession.getAttribute("usuarioLogeado");
				
				GestorAnuncios gestor = GestorAnuncios.getInstance();
				MySQLDAOManager manager = null;
				
				try {
					manager = MySQLDAOManager.getInstance();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Long id = Long.parseLong(request.getParameter("idAnuncio"));
				
				if(request.getParameter("accion").equals("archivar"))
				{
					try {
						Anuncio a = manager.getAnuncioDAO().obtener(id);
						a.setEstado(Estado.archivado);
						manager.getAnuncioDAO().Modificar(a);
						request.getRequestDispatcher("/mvc/view/misAnuncios.jsp").forward(request, response);
						
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				else if(request.getParameter("accion").equals("publicar"))
				{
					try {
						Anuncio a = manager.getAnuncioDAO().obtener(id);
						a.setEstado(Estado.publicado);
						manager.getAnuncioDAO().Modificar(a);
						request.getRequestDispatcher("/mvc/view/misAnuncios.jsp").forward(request, response);
						
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
				}
				else if(request.getParameter("accion").equals("recuperar"))
				{
					try {
						Anuncio a = manager.getAnuncioDAO().obtener(id);
						a.setEstado(Estado.editado);
						manager.getAnuncioDAO().Modificar(a);
						request.getRequestDispatcher("/mvc/view/misAnuncios.jsp").forward(request, response);
						
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
				}
				else if(request.getParameter("accion").equals("eliminar"))
				{
					try {

						Anuncio a = manager.getAnuncioDAO().obtener(id);
						
						manager.getAnuncioDAO().borrar(a);
						response.sendRedirect(request.getContextPath()+"/MisAnuncios");
						
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
				}
				else if(request.getParameter("accion").equals("editar"))
				{
					try {
						Anuncio a = manager.getAnuncioDAO().obtener(id);
						
						
						if(a.getAutor().getId().equals(userLogged.getId()))
						{
							request.setAttribute("idAnuncio", id.toString());
							request.getRequestDispatcher("Editar").forward(request, response);
						}
						
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
				}
			}
			else
			{
				request.getRequestDispatcher("/mvc/view/misAnuncios.jsp").forward(request, response);
			}
		}
		else
		{
			request.getRequestDispatcher("/mvc/view/misAnuncios.jsp").forward(request, response);
		}
		
		
	}

}
