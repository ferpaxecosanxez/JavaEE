<%@page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang='es'>

<head>
	<meta charset='UTF-8'>
	<title>Bienvenido</title>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>

<body>
	<header>
		<h1>Bienvenido <%=session.getAttribute("usuario")%></h1>
	</header>
	
	<div class="main">
		<p>Tema elegido: <b><%=request.getParameter("tema")%></b></p>
		
		<br/><br/>
		<input class="botonLargo" type="button" value="Cerrar Sesión" 
			onClick="window.location.href='CierraSesion'"/>
		
		<br/><br/>
		<input class="botonLargo" type="button" value="Volver" 
			onClick="window.location.href='temas.html'"/>
	</div>
</body>
</html>
