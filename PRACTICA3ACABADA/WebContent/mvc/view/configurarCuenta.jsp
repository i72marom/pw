<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="es.uco.pw.business.contacto.*,es.uco.pw.data.mysqldao.MySQLDAOManager"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Configuración</title>
	<!--
		-Resumen: Pagina principal
		-Autor: Manuel Jesus Mariscal Romero
		-Autor: Javier Luna Carmona
	-->

	<!-- CSS -->	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/tablon.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/checkbox.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/buscador.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/menu_lateral.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/crear_anuncio.css">

	<!-- FAVICON -->
	<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath() %>/img/logo.png"/>
	

	<!-- OTHERS -->
	<meta charset="UTF-8">
	<meta name="description" content="Configuración de la cuenta del usuario.">
	<meta name="keywords" content="account, cuenta, configurar, configuracion">
	<meta name="author" content="Manuel Jesus Mariscal Romero">
	<meta name="author" content="Javier Luna Carmona">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">	
	
	<!-- JS -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/conf.js" charset="utf-8"></script>
	
	
	
</head>
<body id="body">


	<!-- AUTENTICACIÓN -->
	<%
	HttpSession objSession = request.getSession(false);
	Contacto userLogged = (Contacto) objSession.getAttribute("usuarioLogeado");

	if (userLogged == null) {
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	} else {
		
		GestorContactos gestor = GestorContactos.getInstance();
		
		if(request.getAttribute("passErronea") != null)
		{
			String passErroneaValue = (String) request.getAttribute("passErronea");
			
			if(passErroneaValue.equals("1"))
			{
				%>
				
				
				<script type="text/javascript">
					alert("No has introducido tu contraseña correctamente");
				</script>
				
								
				<%
			}
			else if(passErroneaValue.equals("2"))
			{
				%>
				
				<script type="text/javascript">
					alert("La contraseña introducida es igual a la anterior.");
				</script>
				
				<%
			}
			else if(passErroneaValue.equals("0"))
			{
				%>
				
				<script type="text/javascript">
					alert("Contraseña cambiada correctamente.");
				</script>
								
				<%
			}
		}
		
	%>



	<!-- HEADER -->
	<div class="navbar">
		<div class="content">
			<a href="<%=request.getContextPath()%>/Tablon">Inicio</a>
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
					<p><%=userLogged.getNombre()%> <%=userLogged.getApellidos() %> &#9660;</p>
				</button>
				<div class="cuenta">
					<a href="<%=request.getContextPath()%>/Cuenta" class="active">Configurar cuenta</a>
					<a href="<%=request.getContextPath()%>/index.jsp">Cerrar Sesión</a>
				</div>
			</div>
		</div>
	</div>

		
	<!-- FILTROS -->
	<div id="filtros">
		<h2>Configuración de la cuenta</h2>

		<a class="checkbox-class create" href="#" id="botonInfo" onclick="conf(info);">
			Información personal
		</a>
		
		<a class="checkbox-class create" href="#" id="botonContacto" onclick="conf(contacto);">
			Contacto
		</a>
		
		<a class="checkbox-class create" href="#" id="botonTema" onclick="conf(tema);")>
			Intereses
		</a>
		
		<a class="checkbox-class create" href="#" id="botonPass" onclick="conf(pass);">
			Seguridad
		</a>
	</div>

	<div id="tablon">
	
	

		<!-- INFORMACION PERSONAL -->
		<form action="<%=request.getContextPath()%>/Cuenta" method="POST" id="info">		    
			<input type="hidden" name="tipo" value="informacionpersonal">
			<div class="row">
				<div class="col-1">
					<label for="nombre">Nombre</label>
				</div>
				<div class="col-2">
					<input type="text" id="nombre" name="nombre" required value="<%=userLogged.getNombre()%>">
				</div>
			</div>

			<div class="row">
				<div class="col-1">
					<label for="prim_ap">Primer apellido</label>
				</div>
				<div class="col-2">
					<input type="text" id="prim_ap" name="prim_ap" value="<%=userLogged.getApellido1() %>" required>
				</div>
			</div>

			<div class="row">
				<div class="col-1">
					<label for="seg_ap">Segundo apellido</label>
				</div>
				<div class="col-2">
					<input type="text" id="seg_ap" name="seg_ap" value="<%=userLogged.getApellido2() %>" required>
				</div>
			</div>

			<div class="row">
				<div class="col-1">
					<label for="date">Fecha de nacimiento</label>
				</div>
				<div class="col-2">
					<input type="date" id="date" name="date" value="<%=userLogged.getFechaCumpleanos().toString()%>" disabled="" >
					<input type="button" value="Guardar" onclick="checkBotonInfo()">
				</div>
			</div>
		</form>

		<!-- CONTACTO -->
		<div id="contacto">
			<div class="row">
				<div class="col-1">
					<label for="nombre">E-mail</label>
				</div>
				<div class="col-2">
					<input type="text" id="nombre" name="nombre" disabled="" value="<%=userLogged.getEmail()%>">
				</div>
			</div>
		</div>

		<!-- INTERESES -->
		<form action="" method="" id="tema">		
			<input type="hidden" name="tipo" value="intereses">    
			<div class="row">
				<div class="col-1">
					<label for="tema">Tema</label>
				</div>
				<div class="col-2">
				
				<%
				
					String[] tags_disponibles = {"Amor", "Cultura", "Entretenimiento", "Comida", "Deportes"};
					for(int i = 0;i<tags_disponibles.length;i++)
					{
						if(gestor.perteneceAlTag(userLogged, tags_disponibles[i]))
						{
							%>
					<label class="checkbox-class cb"><%=tags_disponibles[i]%>
						<input type="checkbox" name="tema" value="<%=tags_disponibles[i] %>" checked>
						<span class="checkmark cm"></span>
					</label>							
							<%
						}
						else
						{
							%>
					<label class="checkbox-class cb"><%=tags_disponibles[i] %>
						<input type="checkbox" name="tema" value="<%=tags_disponibles[i] %>">
						<span class="checkmark cm"></span>
					</label>	
							<%							
						}
						
						
					}
				
				%>
					<input type="submit" value="Guardar">
				</div>
			</div>
		</form>

		<!-- INFORMACION PERSONAL -->
		<form action="<%=request.getContextPath()%>/Cuenta" method="POST" id="pass">	
			<input type="hidden" name="tipo" value="passwords">	    
			<div class="row">
				<div class="col-1">
					<label for="password">Contraseña actual</label>
				</div>
				<div class="col-2">
					<input type="password" id="password" name="password" required>
				</div>
			</div>

			<div class="row">
				<div class="col-1">
					<label for="new_pass">Nueva contraseña</label>
				</div>
				<div class="col-2">
					<input type="password" id="new_pass" name="new_pass" required>
				</div>
			</div>

			<div class="row">
				<div class="col-1">
					<label for="rep_new_pass">Repetir contraseña</label>
				</div>
				<div class="col-2">
					<input type="password" id="rep_new_pass" name="rep_new_pass" required>
					<input type="button" value="Guardar" onclick="checkBotonPass()">
				</div>
			</div>
		</form>
	</div>
	
	<%
	
	}
	
	%>
	
</body>
</html>