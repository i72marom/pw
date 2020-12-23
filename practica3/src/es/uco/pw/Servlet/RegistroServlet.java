package es.uco.pw.Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uco.pw.business.contacto.Contacto;
import es.uco.pw.business.contacto.GestorContactos;
import es.uco.pw.data.dao.DAOException;
import es.uco.pw.data.mysqldao.MySQLDAOManager;

/**
 * Servlet implementation class RegistroServlet
 */
@WebServlet("/index")
public class RegistroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistroServlet() {
        super();
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
		
		response.setContentType("text/html");
		
		
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
		
		String nombre, apellido1, apellido2, correo, password, fecha_nac;
		int edad;
		
		nombre = request.getParameter("name");
		apellido1 = request.getParameter("prim_ap");
		apellido2 = request.getParameter("seg_ap");
		correo = request.getParameter("mail");
		edad = Integer.parseInt(request.getParameter("edad"));
		fecha_nac = request.getParameter("date");
		password = request.getParameter("pass");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fecha = LocalDate.parse(fecha_nac, formatter);
		
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("cultura");
		tags.add("amor");
		
		Contacto a = new Contacto(nombre, apellido1, apellido2, edad, correo, fecha, tags, password);
		GestorContactos gestor = GestorContactos.getInstance();
		
		try {
			if(gestor.existeContacto(a, manager) == -1)
			{
				manager.getContactoDAO().insertar(a);
				
				request.setAttribute("error", false);
				
			}
			else
			{
				request.setAttribute("error", true);
				
			}
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
