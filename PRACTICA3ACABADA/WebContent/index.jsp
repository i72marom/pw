<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<title>Inicio | Tablon de anuncios</title>


	

	<!--
		-Resumen: Pagina principal
		-Autor: Manuel Jesus Mariscal Romero
		-Autor: Javier Luna Carmona
	-->

	<!-- CSS -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css">

	<!-- FAVICON -->
	<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath() %>/img/logo.png"/>

	<!-- OTHERS -->
	<meta name="description" content="Pagina principal de la aplicaci�n. En ella se perimite elegir iniciar sesion o registrarse.">
	<meta name="keywords" content="tablon, anuncios, anunico">
	<meta name="author" content="Manuel Jesus Mariscal Romero">
	<meta name="author" content="Javier Luna Carmona">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta charset="UTF-8">

	<!-- JS -->
	 
</head>
<body>

	<%
	
	HttpSession sesion = request.getSession(false);
	sesion.setAttribute("usuarioLogeado", null);
	
	
	%>

	<!-- BOTONES PRINCIPALES -->
	<!-- <div id="logo"><img class="logo-img" src="img/logo.png"></div> -->
	<div id="logo">
		<h1>Tabl�n de anuncios | Programaci�n web</h1>
		<p class="p">Javier Luna Carmona</p>
		<p class="p">Manuel Mariscal Romero</p>
	</div>
	
	
<!-- mensaje de error en caso de login o registro erroneo-->
<%

if(request.getAttribute("error") != null)
{
	boolean error =(boolean)request.getAttribute("error");
	if(error){
		%>
		
		<script type="text/javascript" charset="utf-8">
		
		alert("Ya hay una cuenta con ese email");
		
		</script>
		
		<%
	}
	else
	{
		%>
		
		<script type="text/javascript" charset="utf-8">
		
		alert("Cuenta creada satisfactoriamente.");
		
		</script>
		
		<%
	}
}
if(request.getAttribute("errorLogin") != null)
{
	%>
		<script type="text/javascript" charset="utf-8">
		
		alert("Datos erroneos");
		
		</script>	
	<%
}
%>
<!-- fin mensaje de error -->




	<!-- BOTONES PRINCIPALES -->
	<div class="main">
		<div class="login">
			<button id="left" onclick="muestraLogin()">Login</button>
		</div>
		<div class="register">
			<button id="right" onclick="muestraRegistro()">Registro</button>
		</div>
	</div>

	<!-- FORMULARIO DE INICIO DE SESION -->
	<div id="login-form">
		<button id="close" onclick="cierraLogin()"><img class="media" src="img/close.png" /></button>
		<form action="Login" method="post">
			<div id="campo">
			<label class="label" for="mail">Correo electr�nico</label>
				<input type="text" id="user" name="mail" placeholder="Correo electr�nico" required>
			</div>

			<br/>

			<div id="campo">
				<label class="label" for="pass">Contrase�a</label>
				<input type="password" id="pass" name="pass" placeholder="Contrase�a" required>
			</div>

			<br>

			<div class="button">
				<input id="boton" type="submit" value="Iniciar sesi�n">
			</div>
		</form>
	</div>

	<!-- FORMULARIO DE REGISTRO -->
	<div id="registro-form">
		<button id="close" onclick="cierraRegistro()"><img class="media" src="img/close.png" /></button>
		<form action="Index" method="post" id="registroForm">
			<div id="campo">
				<label class="label" for="nombre">Nombre</label>
				<input type="text" id="nombre" name="name" placeholder="Nombre" required>
			</div>
			<br/>

			<div id="campo">
				<label class="label" for="prim_ap">Primer apellido</label>
				<input type="text" id="prim_ap" name="prim_ap" placeholder="Primer apellido" required>
			</div>

			<br/>

			<div id="campo">
				<label class="label" for="seg_ap">Segundo apellido</label>
				<input type="text" id="seg_ap" name="seg_ap" placeholder="Segundo apellido" required>
			</div>

			<br/>

			<div id="campo">
				<label class="label" for="edad">Edad</label>
				<input type="number" id="edad" name="edad" placeholder="Edad" required>
			</div>

			<br />

			<div id="campo">
				<label class="label" for="correo">Correo electr�nico</label>
				<input type="text" id="correo" name="mail" placeholder="Correo" required>
			</div>

			<br/>

			<div id="campo">
				<label class="label" for="fechanac">Fecha de nacimiento</label>
				<input type="date" id="fechanac" name="date" placeholder="dd/mm/yyyy" required>
			</div>

			<br/>

			<div id="campo">
				<label class="label" for="password">Contrase�a</label>
				<input type="password" id="password" name="password" placeholder="Contrase�a" required>
				<label class="label" for="password-rep">Confirmar Contrase�a</label>
				<input type="password" id="password-rep" name="password-rep" placeholder="Confirmar Contrase�a" required>
			</div>

			<br/>
			<br/>
			
			<div class="button">
				<input id="boton" type="button" value="Continuar" onclick="checkRegistro()"></input>
			</div>
		</form>
	</div>
	<script type="text/javascript" src="js/index.js" charset="utf-8">></script>
</body>
</html>