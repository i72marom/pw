<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="es.uco.pw.business.contacto.*, es.uco.pw.data.mysqldao.MySQLDAOManager,es.uco.pw.business.anuncio.Anuncio,java.util.ArrayList,java.sql.SQLException"%>


<html>

<head>
	<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
	
	
	<!-- CSS -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/crearAnuncio.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/tablon.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/checkbox.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/buscador.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/menu_lateral.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/crear_anuncio.css">
	
	<!-- TITULO -->
	<title>Crear</title>
	
	<!-- FAVICON -->
	<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath() %>/img/logo.png"/>	
	
	<!-- OTHERS -->
	<meta charset="UTF-8">
	<meta name="description" content="Se crea un anuncio">
	<meta name="keywords" content="anuncio, anuncios, crear">
	<meta name="author" content="Manuel Jesus Mariscal Romero">
	<meta name="author" content="Javier Luna Carmona">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	

</head>

<body onload="cambiarTipo()">

	<script type="text/javascript">
			
	
			
	var arrayContactos = [];		
			
	
	function Contacto(nombre, id, email, apellidos) {
		  this.nombre = nombre;
		  this.id = id;
		  this.email = email;
		  this.apellidos = apellidos;
	}
			
			
			
	</script>


	<%
	HttpSession objSession = request.getSession(false);
	Contacto userLogged = (Contacto) objSession.getAttribute("usuarioLogeado");

	if (userLogged == null) {
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	} else {
		
		
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
		
		GestorContactos gestor = GestorContactos.getInstance();
		String temasDisponibles = "amor,cultura.deportes,entretenimiento,comida";
		
		ArrayList<Contacto> users = manager.getContactoDAO().obtenerTodos();
		
		for(int i = 0;i<users.size();i++)
		{
			
		
		
			if(users.get(i).getId() != userLogged.getId())
			{
				

		
	%>

			<script type="text/javascript">
			
			
			
			var nombre = "<%=users.get(i).getNombre()%>";
			var id = "<%=users.get(i).getId()%>";
			var email = "<%=users.get(i).getEmail()%>";
			var apellidos = "<%=users.get(i).getApellidos()%>";
			arrayContactos.push(new Contacto(nombre, id, email, apellidos));
			
			
			
			
			
			
			</script>
	
	<%
			}
		}
	
	%>

	<!-- BARRA DE NAVEGACION -->

	<div class="navbar">
		<div class="content">
			<a href="<%=request.getContextPath()%>/Tablon">Inicio</a>
			<a href="<%=request.getContextPath()%>/Crear" class="active">Crear Anuncio</a> 
			<a href="<%=request.getContextPath()%>/MisAnuncios">Mis Anuncios</a>
			<%
			if(userLogged.getAdmin())
			{
				%>
			<a href="<%=request.getContextPath()%>/Admin" >Panel de control ( ADMIN )</a> 
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

	
	<br/>
	<br/>
	<br/>
	<br/>
	
	<!-- PARTE PRICIPAL DE LA PÁGINA -->
	
	<div id="formulario" class="crearForm">
		<form action="Crear" method="POST" id="formularioCrearAnuncio">		    
			
			<!-- FILTROS -->
			<div id="filtros">
				<h2>Elige el tipo de anuncio</h2>
	
				<label class="checkbox-class create">General
					<input type="radio" name="tipoAnuncio" value="general" checked="checked" onclick="cambiarTipo()" id="radioGeneral">
				</label>
				
				<label class="checkbox-class create">Temático
					<input type="radio" name="tipoAnuncio" value="tematico" onclick="cambiarTipo()" id="radioTematico">
				</label>
				
				<label class="checkbox-class create">Individualizado
					<input type="radio" name="tipoAnuncio" value="individualizado" onclick="cambiarTipo()" id="radioIndividualizado">
				</label>
	
				<label class="checkbox-class create">Flash
					<input type="radio" name="tipoAnuncio" value="flash" onclick="cambiarTipo()" id="radioFlash">
				</label>
			</div>
	
			<!-- CUERPO DEL FORMULARIO -->
			<div id="tablon">
				<div id="campos">
					<div class="row">
						<div class="col-1">
							<label for="titulo">Título</label>
						</div>
						<div class="col-2">
							<input type="text" id="titulo" name="titulo" placeholder="Título"required>
						</div>
					</div>
		
					
					<div class="row">
						<div class="col-1">
							<label for="contenido">Anuncio</label>
						</div>
						
						<div class="col-2">
							<textarea id="contenido" name="contenido" placeholder="Escribe tu anuncio..." required></textarea>
							
						</div>
					</div>
				</div>
				<div class="botonContinuar">
					<input type="button" value="Continuar" onclick="checkSubmit()">
				</div>
			</div>
		</form>
	</div>

	<%
		}
	%>


	<!-- JAVASCRIPT -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/crearAnuncio.js" charset="utf-8"></script>
	

	
</body>

</html>