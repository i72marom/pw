<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="es.uco.pw.business.contacto.*, es.uco.pw.data.mysqldao.MySQLDAOManager,es.uco.pw.business.anuncio.*,java.util.ArrayList,java.sql.SQLException, es.uco.pw.business.tipos.*"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	
	<!-- CSS -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/crearAnuncio.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/tablon.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/checkbox.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/buscador.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/menu_lateral.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/crear_anuncio.css">
	
	<!-- FAVICON -->
	<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath() %>/img/logo.png"/>	

	<!-- TITULO -->
	<title>Editar Anuncio</title>	
	
	<!-- JAVASCRIPT -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/editarAnuncio.js" charset="utf-8"></script>
	
	<!-- OTHERS -->
	<meta charset="UTF-8">
	<meta name="description" content="Página para editar el anuncio">
	<meta name="keywords" content="editar, anuncios, anuncio, modificar">
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
	} 
	else 
	{
		MySQLDAOManager manager = MySQLDAOManager.getInstance();
		GestorAnuncios gestor = GestorAnuncios.getInstance();
		String idAnuncio = (String) request.getParameter("idAnuncio");
			
		Long id = Long.parseLong(idAnuncio);
		
		Anuncio a = manager.getAnuncioDAO().obtener(id);
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
	
	
	<div id="formulario" class="crearForm">
		<form action="<%=request.getContextPath() %>/Editar" method="POST" id="formularioEditarAnuncio">		    
			<input type="hidden" name="idAnuncio" id="idAnuncio" value="<%=a.getId()%>">
			<input type="hidden" name="tipoAnuncio" id="tipoAnuncio" value=<%=a.getTipo().toString() %>>
			<!-- FILTROS -->
			<div id="filtros">
	
				<%
				
				if(a.getTipo() == Tipo.general)
				{
					%>
				<label class="checkbox-class create">General
				</label>					
					<%
				}
				else if(a.getTipo() == Tipo.flash)
				{
					%>
				<label class="checkbox-class create">Flash
				</label>					
					<%
				}
				else if(a.getTipo() == Tipo.individualizado)
				{
					%>
				<label class="checkbox-class create">Individualizado
				</label>					
					<%
				}
				else if(a.getTipo() == Tipo.tematico)
				{
					%>
				<label class="checkbox-class create">Temático
				</label>					
					<%
				}
				
				%>
				

				

				

	

			</div>
	
			<!-- CUERPO DEL FORMULARIO -->
			
			<div id="tablon">
				<div id="campos">
					<div class="row">
						<div class="col-1">
							<label for="titulo">Título</label>
						</div>
						<div class="col-2">
							<input type="text" id="titulo" name="titulo" value="<%=a.getTitulo()%>" required>
						</div>
					</div>
		
					
					<div class="row">
						<div class="col-1">
							<label for="contenido">Anuncio</label>
						</div>
						
						<div class="col-2">
							<textarea id="contenido" name="contenido" required><%=a.getCuerpo()%></textarea>
							
						</div>
					</div>
					
					<%
					if(a.getTipo() == Tipo.flash)
					{
						AnuncioFlash b = (AnuncioFlash) a;
						%>
						
						<div class="row">
							<div class="col-1">
								<label for="fecha_inicio">Fecha de inicio</label>
							</div>
							<div class="col-2">
								<input type="date" id="fechaInicio" name="fechaInicio" value="<%=b.getFechaInicioFormatted()%>" disabled>
							</div>
						</div>
						<div class="row">
							<div class="col-1">
								<label for="fecha_fin">Fecha de cierre</label>
							</div>
							<div class="col-2">
								<input type="date" id="fechaFin" name="fechaFin" value="<%=b.getFechaFinFormatted() %>" min="<%=gestor.fechaActual()%>" max="2030-12-31" onchange="">
							</div>
						</div>
								
						<%
					}
					if(a.getTipo() == Tipo.individualizado)
					{
						AnuncioIndividualizado b = (AnuncioIndividualizado) a;
						ArrayList<Contacto> todosLosUsers = manager.getContactoDAO().obtenerTodos();
						ArrayList<Long> destinatarios = b.getDestinatarios();
						
						%>
						<label class="label" for="destinatario">Destinatarios</label><br/>
						<input type="hidden" name="idDestinatarios" id="idDestinatarios">
						<%
						for(int i = 0;i<todosLosUsers.size();i++)
						{
							if(!todosLosUsers.get(i).getId().equals(userLogged.getId()))
							{
	
								if(gestor.esDestinatario(b.getDestinatarios(), todosLosUsers.get(i).getId()))
								{
									%>
							<label class="checkbox-class cb"><%=todosLosUsers.get(i).getNombre()%> <%=todosLosUsers.get(i).getApellidos()%> (<%=todosLosUsers.get(i).getEmail()%>)
								<input type ="checkbox" name ="destinatario" class="destinatario" id = "botonDestinatario<%=todosLosUsers.get(i).getId() %>" checked>
								<span class="checkmark cm"></span>
							</label>	
									<%
								}
								else
								{
									%>
							<label class="checkbox-class cb"><%=todosLosUsers.get(i).getNombre()%> <%=todosLosUsers.get(i).getApellidos()%> (<%=todosLosUsers.get(i).getEmail()%>)
								<input type ="checkbox" name ="destinatario" class="destinatario" id = "botonDestinatario<%=todosLosUsers.get(i).getId() %>">
								<span class="checkmark cm"></span>
							</label>									
									<%
								}
							}
							
							%>
							
							<%
						}
						%>
						
						<input type="hidden" id="idsDestinatarios" name="idsDestinatarios">
						
						<%
					}
					if(a.getTipo() == Tipo.tematico)
					{
						
						AnuncioTematico b = (AnuncioTematico) a;
						String[] tagsDisponibles = {"amor","cultura","deportes","entretenimiento","comida"};
						ArrayList<String> tagsDisponiblesArrayList = new ArrayList<String>();
						
						for(int i = 0;i<tagsDisponibles.length;i++)
							tagsDisponiblesArrayList.add(tagsDisponibles[i]);
						
						ArrayList<String> tags = b.getTemas();
						
						%>
						<label class="label" for="destinatario">Temas</label><br/>
						<input type="hidden" name="temas" id="temas">
						<%
						
						for(int i = 0;i<tagsDisponiblesArrayList.size();i++)
						{
							if(tags.contains(tagsDisponiblesArrayList.get(i)))
							{
							%>
								<label class="checkbox-class cb"><%=tagsDisponiblesArrayList.get(i)%>
									<input type ="checkbox" name ="tema" class="tema" id = "botonTema<%=tagsDisponiblesArrayList.get(i)%>" value="<%=tagsDisponiblesArrayList.get(i)%>" checked>
									<span class="checkmark cm"></span>
								</label>									
							<%
							}
							else
							{
								%>
								<label class="checkbox-class cb"><%=tagsDisponiblesArrayList.get(i)%>
									<input type ="checkbox" name ="tema" class="tema" id = "botonTema<%=tagsDisponiblesArrayList.get(i)%>" value="<%=tagsDisponiblesArrayList.get(i)%>">
									<span class="checkmark cm"></span>
								</label>										
								<%
							}
							%>
						
							<%
						}
					}
					
					%>
					
				</div>
				<div class="botonContinuar">
					<input type="button" value="Guardar" onclick="checkSubmit()">
				</div>
			</div>
		</form>
	</div>	
	
		
		<%
	}
		%>
</body>
</html>