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
	
	<!-- TITULO -->
	<title>Administrador</title>
	
	<!-- FAVICON -->
	<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath() %>/img/logo.png"/>
	
	<!-- OTHERS -->
	<meta charset="UTF-8">
	<meta name="description" content="Página de administrador">
	<meta name="keywords" content="admin, moderador">
	<meta name="author" content="Manuel Jesus Mariscal Romero">
	<meta name="author" content="Javier Luna Carmona">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<!-- JAVASCRIPT -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/dashboard.js" charset="utf-8"></script>

</head>

<body>


	<%
	HttpSession objSession = request.getSession(false);
	Contacto userLogged = (Contacto) objSession.getAttribute("usuarioLogeado");

	if (userLogged == null || !userLogged.getAdmin()) {
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	} else {
		
		MySQLDAOManager manager = MySQLDAOManager.getInstance();
		
		ArrayList<Contacto> users = manager.getContactoDAO().obtenerTodos();
		
		ArrayList<Anuncio> anuncios = manager.getAnuncioDAO().obtenerTodos();
		
		
	%>
	
	
	
	<!-- aqui va la navigation bar -->
	
	<div class="navbar">
		<div class="content">
			<a href="<%=request.getContextPath()%>/Tablon">Inicio</a>
			<a href="<%=request.getContextPath()%>/Crear">Crear Anuncio</a> 
			<a href="<%=request.getContextPath()%>/MisAnuncios">Mis Anuncios</a>
			<%
			if(userLogged.getAdmin())
			{
				%>
			<a href="<%=request.getContextPath()%>/Admin" class="active">Panel de control ( ADMIN )</a> 
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
	
	
	<!-- PARTE PRINCIPAL -->
	<div class="body">
					<!-- FILTROS -->
			<div id="filtros">
	
				<label class="checkbox-class create">Administrar moderadores
					<input type="radio" id="radioModeradores" name="administrar" value="moderadores" checked  onclick="cambiar()" >
				</label>
				
				<label class="checkbox-class create">Administrar anuncios
					<input type="radio"  id="radioAnuncios" name="administrar" value="anuncios" onclick="cambiar()">
				</label>
				
				<label class="checkbox-class create">Administrar usuarios
					<input type="radio"  id="radioUsuarios" name="administrar" value="usuarios" onclick="cambiar()">
				</label>

			</div>
			
			
			<!-- CUERPO DEL FORMULARIO -->
			<div id="tablon">
				<form action="Admin" method="POST" id="formHacerAdmin">
					<input type="hidden" name="accion" id="accion" value="">
					<input type="hidden" name="id" id="idUser" value="">
					<div id="camposModeradores">
					<h3>USUARIOS</h3>
					<br/>
					<%
					
					for(int i = 0;i<users.size();i++)
					{
						%>
						<div class="row">
							<div class="col-1">
								<%=users.get(i).getNombre()%> <%=users.get(i).getApellidos() %> (<%=users.get(i).getEmail() %>)
							</div>
							<div class="col-2">
						<%
						if(!users.get(i).getAdmin())
						{
						%>
							<input type="button"  value="Hacer Admin" class="botonesMisAnuncios" onclick="hacerAdmin(<%=users.get(i).getId()%>)">
						<%	
						}
						else
						{
						%>
							<input type="button" value="Quitar Admin" class="botonesMisAnuncios" onclick="quitarAdmin(<%=users.get(i).getId()%>)">
						<%
						}
						%>
							
								
							</div>
						</div>		
						<br/>			
						
						<%
					}
					
					%>
					</div>
					
					<div id="camposUsers" style="display:none">
						<h3>USUARIOS</h3>
						<br/>
					<%
					for(int i = 0;i<users.size();i++)
					{
						%>
						<div class="row">
							<div class="col-1">
								<%=users.get(i).getNombre()%> <%=users.get(i).getApellidos() %> (<%=users.get(i).getEmail() %>)
							</div>
							<div class="col-2">
						<%
						if(!users.get(i).getAdmin())
						{
						%>
							<input type="button"  value="Eliminar cuenta" class="botonesMisAnuncios" onclick="eliminarCuenta(<%=users.get(i).getId()%>)">
						<%	
						}
						%>
							
								
							</div>
						</div>		
						<br/>			
						
						<%
					}		
					%>
					</div>
					
					
					<div id="camposAnuncios" style="display:none">
						<h3>ANUNCIOS</h3>
						<br/>
						
						<%
						
						for(int i = 0;i<anuncios.size();i++)
						{
						
						%>
						<div class="row">
							<div class="col-1">
								<p>Titulo : <%=anuncios.get(i).getTitulo() %> | Autor : <%=anuncios.get(i).getAutor().getEmail() %> | Tipo : <%=anuncios.get(i).getTipo().name() %> | Estado : <%=anuncios.get(i).getEstado().name() %></p>
							</div>
							<div class="col-2">
								<input type="button"  value="Eliminar anuncio" class="botonesMisAnuncios" onclick="eliminarAnuncio(<%=anuncios.get(i).getId()%>)">				
							</div>
						</div>								
						<%
						
						}
						
						%>
						
					</div>
				</form>
			</div>
			
			</div>
			
			
		
	</div>
	
	
		<%
	
	}
	%>





</body>

</html>