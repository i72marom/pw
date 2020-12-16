<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="es.uco.pw.business.contacto.*,es.uco.pw.data.mysqldao.MySQLDAOManager,es.uco.pw.business.anuncio.Anuncio,java.util.ArrayList,java.sql.SQLException"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/boardView.css">
<title>Insert title here</title>
</head>
<body>

	<%
	HttpSession objSession = request.getSession(false);
	Contacto userLogged = (Contacto) objSession.getAttribute("usuarioLogeado");

	if (userLogged == null) {
		response.sendRedirect("../../index.jsp");
	} else {
	%>
	
	<div class="topnav">
		<a href="boardView.jsp">Tablón</a> 
		<a href="crearAnuncio.jsp">Crear anuncio</a> 
		<a class="active" href="#">Mis anuncios</a> 
		
		<a href="../../index.jsp">Cerrar Sesión | <%=userLogged.getNombre() %> <%=userLogged.getApellidos() %></a>
	</div>

	<%
	
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
		
		
		ArrayList<Anuncio> anuncios = manager.getAnuncioDAO().obtenerPorIdAutor(userLogged.getId());
		
		for(int i = 0;i<anuncios.size();i++)
		{
			Anuncio anuncio = anuncios.get(i);
			
			%>
		<div class="anuncio">
			<p>TITULO : <%=anuncio.getTitulo()%></p>
			<p>AUTOR : <%=anuncio.getAutor().getNombre() %></p>
			<a href="../../Anuncio?id=<%= anuncio.getId() %>">Detalles</a>
			</form>
			<hr></hr>
		</div>	
		
		
			<% 
		}
	
	}
	%>



</body>
</html>