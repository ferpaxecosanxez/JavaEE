<%@page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder"%>
<%@ page import="java.io.UnsupportedEncodingException"%>

<!DOCTYPE html>
<html lang='es'>

<head>
	<meta charset='UTF-8'>
	<title>Resultados App</title>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>

<body>
	<%!
	// Este método es local a este fichero jsp, no entra en el servlet
	// Para ello, usamos < % ! definición de mi método % >
	private String obtenerCookie(HttpServletRequest request) {
		String valorDeCookie = "No ha entrado, por tanto no tiene fecha de última visita";
		Cookie[] todas = request.getCookies();
		if (todas != null) {
			for (Cookie ck : todas) {
				if (ck.getName().equals("ck_visita")) {
					valorDeCookie = ck.getValue();
					break;
				}
			}
		}
		// Descodificamos contenido.
		try {
			valorDeCookie = URLDecoder.decode(valorDeCookie, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		return valorDeCookie;
	}
	%>
	
	<%
	int local = 0, global = 0;
	// De sesión:
	if (session.getAttribute("contador") != null) {
		// Existe el contador de sesión.
		local = (Integer) session.getAttribute("contador");
	}

	// De aplicación. Aquí si que verificamos que no sea null porque el usuario
	// puede acceder directamente a los resultados sin pasar por el servlet.
	if (application.getAttribute("global") != null) {
		// Existe el contador de aplicación.
		global = (Integer) application.getAttribute("global");
	}
	%>
	<header>
		<h1>Contadores:</h1>
	</header>
	
	<div class="main">
		<p>Es el ejerccio 17, pero añadida la funcionalidad de mostrar la fecha de última
		visita, la cual se guardó en una cookie y se recupero la cookie para mostrarla</p>
		
		<br/>
		<h3>Nivel de sesión (Local): <b><%=local%></b></h3>
		<h3>Nivel de aplicación (General): <b><%=global%></b></h3>
		<h3>Fecha de última visita: <%=obtenerCookie(request) %></h3>
		
		<br/><br/>
		<input class="botonMedio" type="button" value="Volver" 
			onClick="window.location.href='index.html'"/>
	</div>
</body>
</html>