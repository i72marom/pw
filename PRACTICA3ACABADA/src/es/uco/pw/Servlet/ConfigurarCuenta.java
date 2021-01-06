package es.uco.pw.Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.business.contacto.Contacto;
import es.uco.pw.data.dao.DAOException;
import es.uco.pw.data.mysqldao.MySQLDAOManager;

/**
 * Servlet implementation class ConfigurarCuenta
 */
//@WebServlet("/Cuenta")
public class ConfigurarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfigurarCuenta() {
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
		
		
		
		
		if(request.getParameter("tipo") == null)
		{
			request.getRequestDispatcher("/mvc/view/configurarCuenta.jsp").forward(request, response);
		}
		else
		{
			HttpSession objSession = request.getSession(false);
			Contacto userLogged = (Contacto) objSession.getAttribute("usuarioLogeado");
			
			MySQLDAOManager manager = null;

			try {
				manager = MySQLDAOManager.getInstance();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(request.getParameter("tipo").equals("informacionpersonal"))
			{
				
				
				
				String nombre = request.getParameter("nombre");
				String apellido1 = request.getParameter("prim_ap");
				String apellido2 = request.getParameter("seg_ap");
				
				userLogged.setNombre(nombre);
				userLogged.setApellido1(apellido1);
				userLogged.setApellido2(apellido2);
				

				
				try {
					manager.getContactoDAO().Modificar(userLogged);
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			if(request.getParameter("tipo").equals("intereses"))
			{
				String[] temas = request.getParameterValues("tema");
				ArrayList<String> temasArrayList = new ArrayList<String>();
				for(int i = 0;i<temas.length;i++)
					temasArrayList.add(temas[i]);
				userLogged.setTags(temasArrayList);
				try {
					manager.getContactoDAO().Modificar(userLogged);
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(request.getParameter("tipo").equals("passwords"))
			{
				String passActual = request.getParameter("password");
				String passNueva = request.getParameter("new_pass");
				
				if(!passActual.equals(userLogged.getPassword()))
				{
					request.setAttribute("passErronea", "1"); // VALOR 1 PARA CUANDO LA CONTRASEÑA ACTUAL NO HA SIDO INTRODUCIDA CORRECTAMENTE.
				}
				else
				{
					if(passActual.equals(passNueva))
					{
						request.setAttribute("passErronea", "2"); // VALOR 2 PARA CUANDO LA NUEVA CONTRASEÑA ES IGUAL QUE LA ACTUAL.
					}
					else
					{
						userLogged.setPassword(passNueva);
						try {
							manager.getContactoDAO().Modificar(userLogged);
						} catch (DAOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
				
			}
			
			
			request.getRequestDispatcher("/mvc/view/configurarCuenta.jsp").forward(request, response);
			
		}
		
	}

}
