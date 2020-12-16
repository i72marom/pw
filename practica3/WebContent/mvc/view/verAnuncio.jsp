<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="es.uco.pw.business.contacto.Contacto,es.uco.pw.data.mysqldao.MySQLDAOManager,es.uco.pw.business.anuncio.*,java.util.ArrayList,java.sql.SQLException, es.uco.pw.business.tipos.*"%>
<!DOCTYPE html>
<html>
<head>

	<meta charset="ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/boardView.css"/>

<title>Insert title here</title>
</head>
<body>

	<%
	HttpSession objSession = request.getSession(false);
	Contacto userLogged = (Contacto) objSession.getAttribute("usuarioLogeado");

	if (userLogged == null) {
		String path = request.getContextPath()+"/index.jsp";
		response.sendRedirect(path);
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
		
		
		
		Long id = Long.parseLong((String)request.getAttribute("id"));
		
		Anuncio a = manager.getAnuncioDAO().obtener(id);
		
		
	%>



	<div class="topnav">
		<a class="active" href="<%=request.getContextPath()%>/mvc/view/boardView.jsp">Tablón</a> 
		<a href="crearAnuncio.jsp">Crear anuncio</a> 
		<a href="#contact">Mis anuncios</a> 
		
		<a href="../../index.jsp">Cerrar Sesión | <%=userLogged.getNombre() %> <%=userLogged.getApellidos() %></a>
	</div>



	<h1>TITULO : <%=a.getTitulo() %> AUTOR : <%=a.getAutor().getNombre() %> <%=a.getAutor().getApellidos() %></h1>
	<h5>FECHA : <%=a.getFecha() %></h5>
	
	<%
	
		if(a.getTipo() == Tipo.flash)
		{
			AnuncioFlash b = (AnuncioFlash) a;
			%>
			<p>Desde <%=b.getFechaInicio()%> hasta <%=b.getFechaFin() %></p>
			<% 
		}
		else if(a.getTipo() == Tipo.tematico)
		{
			AnuncioTematico b = (AnuncioTematico) a;
			ArrayList<String> temas = b.getTemas();
			%>
			<p>Temas : 
			<%
			for(int i = 0;i<temas.size();i++)
			{
				%>
				<%=temas.get(i) %>, 
				<%
			}
			
			
		}
		else if(a.getTipo() == Tipo.individualizado)
		{
			AnuncioIndividualizado b = (AnuncioIndividualizado) a;
			ArrayList<Long> idDestinatarios = b.getDestinatarios();
			
			%>
			<p>Destinatarios : 
			<%
			for(int i = 0;i<idDestinatarios.size();i++)
			{
				Contacto destinatario = manager.getContactoDAO().obtener(idDestinatarios.get(i));
				%>
				<%=destinatario.getNombre()%> <%=destinatario.getApellidos() %>, 
				<%
			}
			
		}
	%>
	
	<p><%=a.getCuerpo()%></p>
		
	
	<a href="<%=request.getContextPath()%>/mvc/view/boardView.jsp"><img src="<%=request.getContextPath()%>/img/volver.png" height="33" width="33" alt="Volver"></a>
	
	<%
	
		if(a.getAutor().getId() == userLogged.getId())
		{
		
	
	
	%>
	
	
	<form action="EliminarAnuncio?id=<%=a.getId()%>" id="formEliminar" method="post">
		<a href="#" onclick="mostrarConfirmacion()"><img src="<%=request.getContextPath()%>/img/delete.png" height="33" width="33"></a>
	</form>
	
	
	<%
	
		}
	}
	
	%>
	
	
	
<script type="text/javascript" src="js/verAnuncio.js"></script>

</body>
</html>