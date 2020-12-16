<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="es.uco.pw.business.contacto.Contacto"%>


<html>

<head>
	<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/boardView.css">
	
	
	
	<title>BOARD</title>
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
		<a class="active" href="crearAnuncio.jsp">Crear anuncio</a> 
		<a href="#contact">Mis anuncios</a> 
		<a href="../../index.jsp">Cerrar Sesión | <%=userLogged.getNombre() %> <%=userLogged.getApellidos() %></a>
	</div>



	<%
		}
	%>

</body>

</html>