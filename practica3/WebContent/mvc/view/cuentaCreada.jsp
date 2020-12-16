<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="Refresh" content="3;url=<%=request.getContextPath()%>/index.jsp">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css">

<title>Insert title here</title>
</head>
<body>

	<%
	
	boolean error = (boolean)request.getAttribute("error");
	
	System.out.println(error);
	if(!error)
	{
	
	%>
	<h1>Cuenta creada satisfactoriamente.</h1>
	<% 
	}
	else
	{
	%>
	<h1>Ya hay una cuenta con esos datos.</h1>
	<%
	}
	%>
	

</body>
</html>