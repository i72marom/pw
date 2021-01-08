<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="es.uco.pw.business.contacto.Contacto,es.uco.pw.data.mysqldao.MySQLDAOManager,es.uco.pw.business.anuncio.*,java.util.ArrayList,java.sql.SQLException, es.uco.pw.business.tipos.*"%>


<html>

<head>
	<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
	
	<!-- CSS -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/tablon.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/checkbox.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/buscador.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/menu_lateral.css">
	
	<!-- TÍTULO -->
	<title>Tablón de anuncios</title>
	
	<!-- FAVICON -->
	<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath() %>/img/logo.png"/>
	
	<!-- OTHERS -->
	<meta charset="UTF-8">
	<meta name="description" content="Pagina principal dentro de la aplicación. En ella se muestra el tablon de anuncios.">
	<meta name="keywords" content="tablon, anuncios, anuncio">
	<meta name="author" content="Manuel Jesus Mariscal Romero">
	<meta name="author" content="Javier Luna Carmona">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	

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
			<%
			if(userLogged.getAdmin())
			{
				%>
			<a href="<%=request.getContextPath()%>/Admin">Panel de control ( ADMIN )</a> 
				<%
			}
			
			%>
			<div class="desplegable">
				<button class="boton">
					<p><%=userLogged.getNombre()%> <%=userLogged.getApellidos()%>&#9660;</p>
				</button>
				<div class="cuenta">
					<a href="<%=request.getContextPath()%>/Cuenta">Configurar cuenta</a>
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
			manager = MySQLDAOManager.getInstance();
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
		
		String buscar = "";
		String by = "titulo";
		
		
		
		
		
		if(request.getParameter("search") != null)
		{
			buscar = request.getParameter("search");
		}
		if(request.getParameter("by") != null)
		{
			by = request.getParameter("by");
		}
		
		
		ArrayList<Anuncio> anunciosAMostrar = gestor.anadirAnunciosAMostrar(anuncios, userLogged.getId(), buscar, by);
		
		
		
		String order_by = request.getParameter("order_by");
		
		if(order_by != null)
		{
			if(order_by.equals("titulo"))
			{
				anunciosAMostrar = gestor.ordenarPorTitulo(anunciosAMostrar);
				anunciosAMostrar = gestor.revertir(anunciosAMostrar);
			}
			else if(order_by.equals("autor"))
			{
				anunciosAMostrar = gestor.ordenarPorAutor(anunciosAMostrar);
				anunciosAMostrar = gestor.revertir(anunciosAMostrar);
			}
			else if(order_by.equals("fecha"))
			{
				anunciosAMostrar = gestor.ordenarPorFecha(anunciosAMostrar);
			}
		}
		else
		{
			anunciosAMostrar = gestor.ordenarPorFecha(anunciosAMostrar);
		}
		
		if(anunciosAMostrar.size()>0)
		{
			for(int i = 0;i<anunciosAMostrar.size();i++)
			{
				Anuncio anuncio = anunciosAMostrar.get(i);
				
				String contenido = anuncio.getCuerpo();
				String contenidoReducido = "";
				String temasString = "";
				if(contenido.length() > 80)
				{
					contenidoReducido = contenido.substring(0,80);
				}
				

				
				%>
				<div class="anuncio-class" id="<%=i%>">
					<h1 class="titulo"><%=anuncio.getTitulo()%></h1>
					
					<p class="autor" style="display:none;"><%=anuncio.getAutor().getNombre()%> <%=anuncio.getAutor().getApellidos() %></p>
					<p class="fecha" style="display:none;"><%=anuncio.getFecha().toString() %></p>
					
					
				<%
				
				
				if(anuncio.getTipo() == Tipo.tematico)
				{
					AnuncioTematico b = (AnuncioTematico) anuncio;
					ArrayList<String> temas = b.getTemas();
					for(int o = 0;o<temas.size();o++)
					{
						temasString += temas.get(o);
						temasString += ",";
					}
				}
				
				
				%>	
				
					<p class="temas" style="display:none;"><%=temasString%></p>
					
					
					<%
					
					if(anuncio.getTipo() == Tipo.flash)
					{
						
						AnuncioFlash b = (AnuncioFlash) anuncio;
						
						%>
						<p class="fecha"><%=anuncio.getFecha().toString() %> - <span class="autor"><%=anuncio.getAutor().getNombre()%> <%=anuncio.getAutor().getApellidos() %></span><span class="expira"> - [Expira el <%=b.getFechaFin().toString() %>]</span></p>
						<%
					}
					else if(anuncio.getTipo() == Tipo.tematico)
					{
						AnuncioTematico b = (AnuncioTematico) anuncio;
						%>
						<p class="fecha"><%=anuncio.getFecha().toString() %> - <span class="autor"><%=anuncio.getAutor().getNombre()%> <%=anuncio.getAutor().getApellidos() %></span><span class="expira"> - <%=b.getTemas().toString().replace(",", " |").toUpperCase() %></span></p>
						<%
					}
					else
					{
						%>
						<p class="fecha"><%=anuncio.getFecha().toString() %> - <span class="autor"><%=anuncio.getAutor().getNombre()%> <%=anuncio.getAutor().getApellidos() %></span></p>
						<%
					}
					
					%>
					
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
		}
		else
		{
			%>
			<div class="noResultados anuncio-class">
				<h1>No hay resultados con esos criterios de búsqueda...</h1>
			</div>
			<%
		}

		
		%>
		</div>	
	</div>
	
	
		<%
	
	}
	%>



	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tablon.js" charset="utf-8"></script>


</body>

</html>