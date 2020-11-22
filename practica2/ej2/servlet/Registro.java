package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.Consultas;

public class InicioSesion extends HttpServlet {
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String nombre    = request.getParameter("name");
		String primer_ap = request.getParameter("prim_ap");
		String seg_ap    = request.getParameter("seg_ap");
		String mail      = request.getParameter("mail");
		String date      = request.getParameter("date");
		String pass      = request.getParameter("pass");

		Consultas c = new Consultas();
		if (c.registrar(nombre, primer_ap, seg_ap, mail, date, pass)) response.sendRedirect("index.html");
		else response.sendRedirect("login.jsp");
	}
}
