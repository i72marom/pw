package es.uco.pw.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TablonServlet
 */
//@WebServlet("/Tablon")
public class TablonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TablonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String search = null;
		String orderby = null;
		
		
		if(request.getParameter("search") != null)
			search = request.getParameter("search");
		
		if(request.getParameter("order_by") != null)
			search = request.getParameter("order_by");
		
		if(search == null && orderby == null)
		{
			
			//response.sendRedirect(request.getContextPath()+"/mvc/view/boardView.jsp");
		}
			
		
		else 
		{
			request.setAttribute("search", search);
			request.setAttribute("orderby", orderby);
		}
		
		request.getRequestDispatcher("/mvc/view/boardView.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = null;
		String orderby = null;
		
		
		if(request.getParameter("search") != null)
			search = request.getParameter("search");
		
		if(request.getParameter("order_by") != null)
			search = request.getParameter("order_by");
		
		if(search == null && orderby == null)
		{
			request.getRequestDispatcher("/mvc/view/boardView.jsp").forward(request, response);
			//response.sendRedirect(request.getContextPath()+"/mvc/view/boardView.jsp");
		}
			
		
		else 
		{
			request.setAttribute("search", search);
			request.setAttribute("orderby", orderby);
		}
		
		request.getRequestDispatcher("/mvc/view/boardView.jsp").forward(request, response);
		
	}

}
