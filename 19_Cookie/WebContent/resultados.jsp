<%@page language="java" pageEncoding="ISO-8859-1"%>
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
	// Este m�todo es local a este fichero jsp, no entra en el servlet
	// Para ello, usamos < % ! definici�n de mi m�todo % >
	private String obtenerCookie(HttpServletRequest request){
		String valorDeCookie = "No ha entrado, por tanto no tiene fecha de �ltima visita";
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
		<p>Es el ejerccio 17, pero a�adida la funcionalidad de mostrar la fecha de �ltima
		visita, la cual se guard� en una cookie y se recupero la cookie para mostrarla</p>
		
		<br/>
		<h3>Nivel de sesi�n (Local): <b><%=local%></b></h3>
		<h3>Nivel de aplicaci�n (General): <b><%=global%></b></h3>
		<h3>Fecha de �ltima visita: <%=obtenerCookie(request) %></h3>
		
		<br/><br/>
		<input class="botonMedio" type="button" value="Volver" 
			onClick="window.location.href='index.html'"/>
	</div>
</body>
</html>