package es.uco.pw.Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import es.uco.pw.business.anuncio.Anuncio;
import es.uco.pw.business.contacto.Contacto;
import es.uco.pw.data.dao.DAOException;
import es.uco.pw.data.mysqldao.MySQLDAOManager;

/**
 * Servlet implementation class AdminServlet
 */
//@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/mvc/view/dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//OBTIENE LOS DATOS DEL USER LOGEADO
		
		
		MySQLDAOManager manager = null;
		try {
			manager = MySQLDAOManager.getInstance();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String idUser = request.getParameter("id");
		String accion = request.getParameter("accion");
		
		
		
		//SI PASAN POR PARAMETRO UN ID Y UNA ACCION, SE REALIZA
		if(!idUser.equals("") && !accion.equals(""))
		{
			if(accion.equals("hacerAdmin"))
			{
				try {
					Contacto user = manager.getContactoDAO().obtener(Long.parseLong(idUser));
					user.setAdmin(true);
					
					manager.getContactoDAO().Modificar(user);
					
				} catch (NumberFormatException | DAOException e) {
					e.printStackTrace();
				}
				
			}
			if(accion.equals("eliminarCuenta"))
			{
				try {
					Contacto user = manager.getContactoDAO().obtener(Long.parseLong(idUser));
					
					ArrayList<Anuncio> anuncios = manager.getAnuncioDAO().obtenerPorIdAutor(Long.parseLong(idUser));
					for(int i = 0;i<anuncios.size();i++)
					{
						manager.getAnuncioDAO().borrar(anuncios.get(i));
					}
					manager.getContactoDAO().borrar(user);
				} catch (NumberFormatException | DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(accion.equals("eliminarAnuncio"))
			{
				try {
					Anuncio anuncio = manager.getAnuncioDAO().obtener(Long.parseLong(idUser));
					manager.getAnuncioDAO().borrar(anuncio);
					
				} catch (NumberFormatException | DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(accion.equals("quitarAdmin"))
			{
				try {
					Contacto user = manager.getContactoDAO().obtener(Long.parseLong(idUser));
					user.setAdmin(false);
					
					manager.getContactoDAO().Modificar(user);
					
				} catch (NumberFormatException | DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}
		}

		//REDIRECCIONA A /ADMIN
		response.sendRedirect(request.getContextPath()+"/Admin");
		
	}

}
