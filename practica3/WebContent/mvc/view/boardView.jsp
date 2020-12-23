<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="es.uco.pw.business.contacto.Contacto,es.uco.pw.data.mysqldao.MySQLDAOManager,es.uco.pw.business.anuncio.*,java.util.ArrayList,java.sql.SQLException, es.uco.pw.business.tipos.*"%>


<html>

<head>
	<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/tablon.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css">
	
	
	
	<title>Tablón de anuncios</title>
</head>

<body>


	<%
	HttpSession objSession = request.getSession(false);
	Contacto userLogged = (Contacto) objSession.getAttribute("usuarioLogeado");

	if (userLogged == null) {
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	} else {
	%>
	
	
	
	<!-- aqui va la navigation bar -->
	
	<div class="navbar">
		<div class="content">
			<a href="<%=request.getContextPath()%>/Tablon" class="active">Inicio</a>
			<a href="<%=request.getContextPath()%>/Crear">Crear Anuncio</a> 
			<a href="<%=request.getContextPath()%>/MisAnuncios">Mis Anuncios</a>
			<div class="desplegable">
				<button class="boton">
					<p><%=userLogged.getNombre()%> <%=userLogged.getApellidos() %>&#9660;</p>
				</button>
				<div class="cuenta">
					<a href="">Configurar cuenta</a>
					<a href="<%=request.getContextPath()%>/index.jsp">Cerrar Sesión</a>
				</div>
			</div>
		</div>
	</div>
	
	
	
	<div class="body">
		
		<!-- aqui van los filtros -->		

		<jsp:include page="filtrosTablon.jsp" />
		
		
		<!-- aqui van los resultados -->
		<div id="tablon">	
	
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
		
		
		GestorAnuncios gestor = GestorAnuncios.getInstance();
		
		
		ArrayList<Anuncio> anuncios = null;
		
		anuncios = manager.getAnuncioDAO().obtenerTodos();
		
		ArrayList<Anuncio> anunciosAMostrar = gestor.anadirAnunciosAMostrar(anuncios, userLogged.getId());
		
		
			
		
		
		for(int i = 0;i<anunciosAMostrar.size();i++)
		{
			Anuncio anuncio = anunciosAMostrar.get(i);
			
			String contenido = anuncio.getCuerpo();
			String contenidoReducido = "";
			if(contenido.length() > 20)
			{
				contenidoReducido = contenido.substring(0,20);
			}
			
			
			%>
			<div class="anuncio-class" id="<%=i%>">
				<h1 class="titulo"><%=anuncio.getTitulo()%></h1>
				
				<p class="autor" style="display:none;"><%=anuncio.getAutor().getNombre()%> <%=anuncio.getAutor().getApellidos() %></p>
				<p class="fecha" style="display:none;"><%=anuncio.getFecha().toString() %></p>
				
				<p class="fecha"><%=anuncio.getFecha().toString() %> - <span class="autor"><%=anuncio.getAutor().getNombre()%> <%=anuncio.getAutor().getApellidos() %></span></p>
				<%
				if(contenidoReducido.equals(""))
				{
					%>
					
					<p class="anuncioReducido text"><%=contenido %></p>
					
					<%
				}
				else
				{
					%>
					
					<p class="anuncioReducido text"><%=contenidoReducido %>...</p>
					
					<%
				}
				%>
				
				<div class="desplegar" style="display:none;">
					<p class="anuncioCompleto text" style="display:none;">
						<%=contenido%>
					</p>
	
					
					<br/>
					<br/>
				</div>
				
				<%
				if(!contenidoReducido.equals(""))
				{
					%>
					
					
					<a href="javascript:void(0);" class="mas enlaces" id="verMas<%=i%>">Leer Más</a>
					<a href="javascript:void(0);" class="menos enlaces" id="verMenos<%=i%>">Ocultar</a>
					
					
					<%
				}
				
				%>

			</div>	
		
			<br/> 
		
			<% 
		}
		
		%>
		</div>	
	</div>
	
	
		<%
	
	}
	%>



	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tablon.js"></script>


</body>

</html>