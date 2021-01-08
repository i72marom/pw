package es.uco.pw.Servlet;




import java.io.IOException;

import java.sql.SQLException;


import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.business.contacto.Contacto;

import es.uco.pw.data.dao.DAOException;
import es.uco.pw.data.mysqldao.MySQLDAOManager;


/**
 * Servlet implementation class LoginAction
 */
//@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		
		
		
	
		
		String mail=request.getParameter("mail");
		String password=request.getParameter("pass");
		
		MySQLDAOManager manager = null;
		try {
			manager = MySQLDAOManager.getInstance();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		try {
			
			Contacto login = manager.getContactoDAO().validarLogin(mail, password);
			
			//SI NO ESTÁ LOGEADO, CREO UNA SESSION DE 30 MINS
			if(login != null)
			{
				

				HttpSession session = request.getSession(true);
				session.setMaxInactiveInterval(60*30);
				session.setAttribute("usuarioLogeado", login);
				
				
				
				response.sendRedirect(request.getContextPath()+"/Tablon");
				//request.getRequestDispatcher("/Tablon").forward(request, response);
			}
			else
			{
				request.setAttribute("errorLogin", "true");
				request.getRequestDispatcher("index.jsp").forward(request, response);;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
			
		
		//doGet(request, response);
	}

}

