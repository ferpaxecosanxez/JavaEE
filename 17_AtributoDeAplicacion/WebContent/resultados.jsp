<%@page language="java" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang='es'>

<head>
	<meta charset='UTF-8'>
	<title>Resultados App</title>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>

<body>
	<%
	int local = 0, global = 0;
	// De sesi�n:
	if (session.getAttribute("contador") != null) {
		// Existe el contador de sesi�n.
		local = (Integer) session.getAttribute("contador");
	}

	// De aplicaci�n. Aqu� si que verificamos que no sea null porque el usuario
	// puede acceder directamente a los resultados sin pasar por el servlet.
	if (application.getAttribute("global") != null) {
		// Existe el contador de aplicaci�n.
		global = (Integer) application.getAttribute("global");
	}
	%>
	<header>
		<h1>Contadores:</h1>
	</header>
	
	<div class="main">
		<h3>Nivel de sesi�n (Local): <b><%=local%></b></h3>
		<h3>Nivel de aplicaci�n (General): <b><%=global%></b></h3>
		<br/><br/>
		<input class="botonMedio" type="button" value="Volver" 
			onClick="window.location.href='index.html'"/>
	</div>
</body>
</html>