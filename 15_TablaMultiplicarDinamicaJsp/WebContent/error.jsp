<%@page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang='es'>

<head>
	<meta charset='UTF-8'>
	<title>Dato Incorrecto</title>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>

<body>
	<header>
		<h1>Error al procesar número</h1>
	</header>
	
	<div class="main">
		<%
		String numero = request.getParameter("numero");
		%>
		<p>Usted a introducido: <b><%=numero%></b></p>
		<p>Debe ser un número entero</p>
	</div>
	
	<%@ include file="footer.jsp" %>
</body>
</html>