<%@page language="java" pageEncoding="UTF-8"%>
<%@page import="model.service.LibreriaFactory"%>
<%@page import="model.service.GestionUsuario"%>

<!DOCTYPE html>
<html lang='es'>

<head>
	<meta charset='UTF-8'>
	<title>Error Acceso</title>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>

<body>
	<header>
		<h1>Error al acceder a la aplicación</h1>
	</header>
	
	<main>
		<%
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		
		// Comprobar si existe el nombre de usuario.
		GestionUsuario gestionUsuario = LibreriaFactory.getGestionUsuario();
		if(gestionUsuario.get(user) == null){
		%>
			<p>El usuario: "<b><%=user%></b>" no existe en la Base de Datos.</p>
		<%
		} else {
		%>
			<p>La contraseña: "<b><%=pass%></b>" es incorrecta.</p>
		<%
		}
		%>
		
		<br/>
		<input class="botonMedio" type="submit" value="Volver"
			onClick="window.location.href='Controller?op=toIndex'"/>
	</main>
</body>
</html>