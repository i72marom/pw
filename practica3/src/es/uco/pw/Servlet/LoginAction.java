package es.uco.pw.Servlet;



import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.business.contacto.Contacto;
import es.uco.pw.business.contacto.GestorContactos;
import es.uco.pw.data.dao.DAOException;
import es.uco.pw.data.mysqldao.MySQLDAOManager;


/**
 * Servlet implementation class LoginAction
 */
@WebServlet("/Login")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginAction() {
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
			manager = new MySQLDAOManager();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		try {
			
			Contacto login = manager.getContactoDAO().validarLogin(mail, password);
			
			if(login != null)
			{
				
				GestorContactos gestor = GestorContactos.getInstance();
				HttpSession session = request.getSession(true);	 
				session.setAttribute("usuarioLogeado", login);
				
				
				
				response.sendRedirect(request.getContextPath()+"/Tablon");
				//request.getRequestDispatcher("/Tablon").forward(request, response);
			}
			else
			{
				request.getRequestDispatcher("index.jsp").forward(request, response);;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
			
		
		//doGet(request, response);
	}

}

