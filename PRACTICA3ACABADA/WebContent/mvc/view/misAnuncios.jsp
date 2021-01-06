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
	<title>Mis Anuncios</title>
	
	<!-- FAVICON -->
	<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath() %>/img/logo.png"/>
	

	<!-- OTHERS -->
	<meta charset="UTF-8">
	<meta name="description" content="Pagina en la que se muestran los anuncios de los usuarios y ofrece diversas funcionalidades">
	<meta name="keywords" content="anuncios, anuncio, mis anuncios">
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
			<a href="<%=request.getContextPath()%>/Tablon">Inicio</a>
			<a href="<%=request.getContextPath()%>/Crear">Crear Anuncio</a> 
			<a href="<%=request.getContextPath()%>/MisAnuncios" class="active">Mis Anuncios</a>
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
					<p><%=userLogged.getNombre()%> <%=userLogged.getApellidos() %>&#9660;</p>
				</button>
				<div class="cuenta">
					<a href="<%=request.getContextPath()%>/Cuenta">Configurar cuenta</a>
					<a href="<%=request.getContextPath()%>/index.jsp">Cerrar Sesión</a>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Parte principal -->
	
	<div class="body">
		
		<!-- aqui van los filtros -->		

		<jsp:include page="filtrosMisAnuncios.jsp" />
		
		
		<!-- aqui van los resultados de los anuncios -->
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
		
		String buscar = "";
		String by = "";
		String order_by = null;
		if(request.getParameter("search") != null)
		{
			buscar = request.getParameter("search");
		}
		if(request.getParameter("by") != null)
		{
			by = request.getParameter("by");
		}
		ArrayList<Anuncio> anuncios = null;
		
		anuncios = manager.getAnuncioDAO().obtenerPorIdAutor(userLogged.getId());
		
		if(!buscar.equals("") && !by.equals(""))
		{
			anuncios = gestor.filtrar(anuncios, buscar, by);
		}
		
		if(request.getParameter("order_by") != null)
		{
			order_by = request.getParameter("order_by");
		}
		if(order_by != null)
		{
			if(order_by.equals("titulo"))
			{
				System.out.println("entra a titulo");
				anuncios = gestor.ordenarPorTitulo(anuncios);
				anuncios = gestor.revertir(anuncios);
			}
			else if(order_by.equals("fecha"))
			{
				System.out.println("entra a fecha");
				anuncios = gestor.ordenarPorFecha(anuncios);
			}
		}
		
		System.out.println(anuncios);
		
		if(anuncios.size() > 0)
		{
			for(int i = 0;i<anuncios.size();i++)
			{
				Anuncio anuncio = anuncios.get(i);
				
				String contenido = anuncio.getCuerpo();
				String contenidoReducido = "";
				if(contenido.length() > 80)
				{
					contenidoReducido = contenido.substring(0,80);
				}
				
				if(anuncio.getEstado() == Estado.publicado)
				{
					%>
				<div class="publicado-class" style="display:none">
					<%
				}
				else if(anuncio.getEstado() == Estado.editado)
				{
					%>
				<div class="editado-class" style="display:none">
					<%				
				}
				else if(anuncio.getEstado() == Estado.archivado)
				{
					%>
					<div class="archivado-class" style="display:none">
					<%				
				}
				else
				{
					%>
				<div class="espera-class" style="display:none">
					<%				
				}
				
				
				
				
				
				
				%>
					
					<h1 class="titulo"><%=anuncio.getTitulo()%></h1>
					
					<p class="autor" style="display:none;"><%=anuncio.getAutor().getNombre()%> <%=anuncio.getAutor().getApellidos() %></p>
					<p class="fecha" style="display:none;"><%=anuncio.getFecha().toString() %></p>
					
				<%
				if(anuncio.getEstado() == Estado.publicado)
				{
					%>
					<p class="fecha"><%=anuncio.getFecha().toString() %> - <span class="publicado">Publicado</span></p>
					<%
				}
				else if(anuncio.getEstado() == Estado.editado)
				{
					%>
					<p class="fecha"><%=anuncio.getFecha().toString() %> - <span class="editado">Editado</span></p>
					<%				
				}
				else if(anuncio.getEstado() == Estado.archivado)
				{
					%>
					<p class="fecha"><%=anuncio.getFecha().toString() %> - <span class="archivado">Archivado</span></p>
					<%				
				}
				else
				{
					%>
					<p class="fecha"><%=anuncio.getFecha().toString() %> - <span class="espera">En espera</span></p>
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
							<br/>
							<br/>
						<%
					}
					
					%>
					
					<form action="" method="POST" id="formAnuncio<%=i%>">
						<input type="hidden" class="accion" id="accion<%=i %>" name="accion" value="">
						<input type="hidden" name="idAnuncio" value="<%=anuncio.getId() %>">
					<%
					
					if(anuncio.getEstado() == Estado.publicado)
					{
						%>
						<div class="botonesAnuncios" style="display:inline-block;">
						<input type="button" class="botonesMisAnuncios botonArchivar" value="Archivar" id="archivar<%=i%>" onclick="archivar(<%=i%>)">
						</div>
						<%
					}
					if(anuncio.getEstado() == Estado.archivado)
					{
						%>
						<div class="botonesAnuncios" style="display:inline-block;">
						<input type="button" class="botonesMisAnuncios botonRecuperar" value="Recuperar" id="recuperar<%=i%>" onclick="recuperar(<%=i%>)">
						<input type="button" class="botonesMisAnuncios botonEliminar" value="Eliminar" id="eliminar<%=i%>" onclick="eliminar(<%=i%>)">
						</div>						
						<%					
					}
					if(anuncio.getEstado() == Estado.editado)
					{
						%>
						<div class="botonesAnuncios" style="display:inline-block;">
						<input type="button" class="botonesMisAnuncios botonPublicar" value="Publicar" id="editarublicar<%=i%>" onclick="publicar(<%=i%>)">
						<input type="button" class="botonesMisAnuncios botonEditar" value="Editar" id="editar<%=i%>" onclick="editar(<%=i%>)">
						<input type="button" class="botonesMisAnuncios botonEliminar" value="Eliminar" id="eliminar<%=i%>" onclick="eliminar(<%=i%>)">
						</div>							
						<%						
					}
					if(anuncio.getEstado() == Estado.en_espera)
					{
						%>
						<div class="botonesAnuncios" style="display:inline-block;">
						<input type="button" class="botonesMisAnuncios botonArchivar" value="Archivar" id="archivar<%=i%>" onclick="archivar(<%=i%>)">
						</div>						
						<%						
					}
					
					%>
					
					</form>
					
				</div>	
			
				<br/> 
				
				
				<% 
			}			
		}
		else
		{
			%>
			<!-- En caso de que no haya resultados -->
			<div class="noResultados anuncio-class">
				<h1>No hay resultados.</h1>
			</div>
			<%
		}

		
		%>
		</div>	
	</div>
	
	
		<%
	
	}
	%>


	<!-- JAVASCRIPT -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/misAnuncios.js" charset="utf-8"></script>


</body>

</html>