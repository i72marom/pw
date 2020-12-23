<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="es.uco.pw.business.contacto.Contacto, es.uco.pw.data.mysqldao.MySQLDAOManager,es.uco.pw.business.anuncio.Anuncio,java.util.ArrayList,java.sql.SQLException"%>


<html>

<head>
	<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/boardView.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css">
	
	
	<title>BOARD</title>
</head>

<body onload="cargarScript()">

	<script type="text/javascript">
			
	
			
	var arrayContactos = [];		
			
	
	function Contacto(nombre, id) {
		  this.nombre = nombre;
		  this.id = id;
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
			manager = new MySQLDAOManager();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Contacto> users = manager.getContactoDAO().obtenerTodos();
		
		for(int i = 0;i<users.size();i++)
		{
			
		
		
			if(users.get(i).getId() != userLogged.getId())
			{
				

		
	%>

			<script type="text/javascript">
			
			
			
			var nombre = "<%=users.get(i).getNombre()%>";
			var id = "<%=users.get(i).getId()%>";
			
			arrayContactos.push(new Contacto(nombre, id));
			
			
			
			
			
			
			</script>
	
	<%
			}
		}
	
	%>

	<div class="navbar">
		<div class="content">
			<a href="<%=request.getContextPath()%>/Tablon">Inicio</a>
			<a href="<%=request.getContextPath()%>/Crear" class="active">Crear Anuncio</a> 
			<a href="<%=request.getContextPath()%>/MisAnuncios">Mis Anuncios</a>
			<div class="desplegable">
				<button class="boton">
					<p><%=userLogged.getNombre()%> <%=userLogged.getApellidos() %>&#9660;</p>
				</button>
				<div class="cuenta">
					<a href="">Configurar cuenta</a> <a href="#" id="noche"
						onclick="modoNoche(noche)">Modo oscuro</a> <a href="#" id="dia"
						onclick="modoNoche(dia)">Modo claro</a> <a
						href="<%=request.getContextPath()%>/index.jsp">Cerrar Sesión</a>
				</div>
			</div>
		</div>
	</div>

	
	<br/>
	<br/>
	<br/>
	<br/>
	
	<div id="formulario">
			<form action="Crear" method="post" id="formularioCrearAnuncio">
			<div id="campos">
			
				
				<div id="campo">
					<label class="label" for="titulo">Título</label><br/>
					<input type="text" id="titulo" name="titulo" placeholder="Título" required>
				</div>
				<br/>
	
				<div id="campo">
					<label class="label" for="contenido">Contenido</label><br/>
					<input type="text" id="contenido" name="contenido" placeholder="Contenido" required>
				</div>
				
				<br/>
				
				<div id="campo">
					<label class="label" for="tipoAnuncio">Seleccione el tipo de anuncio</label><br/>
					<select id="tipoAnuncio" onchange="cargarScript()" name="tipo">
					
						<option value="general" id="general">General</option>
						<option value="tematico" id="tematico">Temático</option>
						<option value="individualizado" id="individualizado">Individualizado</option>
						<option value="flash" id="flash">Flash</option>
					</select>
				
				</div>
				
				<br/>
				
			</div>
			<br/>
			
			<div class="button">
				<input id="boton" type="button" value="Continuar" onclick="checkSubmit()"></input>
			</div>
			
		</form>
	</div>



	<%
		}
	%>




	<script type="text/javascript" src="<%=request.getContextPath()%>/js/crearAnuncio.js"></script>
</body>

</html>