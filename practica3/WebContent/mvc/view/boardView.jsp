<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="es.uco.pw.business.contacto.Contacto,es.uco.pw.data.mysqldao.MySQLDAOManager,es.uco.pw.business.anuncio.Anuncio,java.util.ArrayList,java.sql.SQLException"%>


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
		<a class="active">Tablón</a> 
		<a href="crearAnuncio.jsp">Crear anuncio</a> 
		<a href="misAnuncios.jsp">Mis anuncios</a> 
		
		<a href="../../index.jsp">Cerrar Sesión | <%=userLogged.getNombre() %> <%=userLogged.getApellidos() %></a>
	</div>
	
	
	<div class="buscador">
	
		<input type="text" id="buscador" style="width: 200px;">
		<button id="botonBuscar" >Buscar</button>
		
		<div>
	    <input type="radio" id="buscarPorTitulo"
	     name="buscar" value="Titulo" checked="checked">
	    <label for="buscarPorTitulo">Titulo</label>
	
	    <input type="radio" id="buscarPorAutor"
	     name="buscar" value="Autor">
	    <label for="buscarPorAutor">Autor</label>
	
	    <input type="radio" id="buscarPorFecha"
	     name="buscar" value="Fecha">
	    <label for="buscarPorFecha">Fecha</label>
	    </div>
		
	</div>
	
	<div id="resultados">
	
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
		
		
		ArrayList<Anuncio> anuncios = manager.getAnuncioDAO().obtenerTodos();
		
		for(int i = 0;i<anuncios.size();i++)
		{
			Anuncio anuncio = anuncios.get(i);
			
			%>
		<div class="anuncio" style="display:none;">
			<p>TITULO : </p><p class="titulo"><%=anuncio.getTitulo()%></p>
			<p>AUTOR : </p><p class="autor"><%=anuncio.getAutor().getNombre() %></p>
			<p>FECHA : </p><p class="fecha"><%=anuncio.getFecha()%></p>
			<a href="../../Anuncio?id=<%= anuncio.getId() %>" class="botonDetalles">Detalles</a>
			<hr></hr>
		</div>	
		
		
		
			<% 
		}
		
		%>
		
		</div>
		
		<%
	
	}
	%>



	<script type="text/javascript" src="<%=request.getContextPath()%>/js/boardView.js"></script>


</body>

</html>