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
		String valorDeCookie = "Sin datos";
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
	// Todos los contadores existen y están inicializados a 0 porque lo hemos hecho en los Listeners
	int local, global, userActivos;	
	
	// Veces que una sesión hace click en botón "Entrar".		
	local = (Integer) session.getAttribute("contador");
	// Total de veces que se hace click en el botón "Entrar" de todas las sesiones activas.
	global = (Integer) application.getAttribute("global");
	// Total de usuraios activos en la aplicación.
	userActivos = (Integer)application.getAttribute("usuariosActivos");
	%>
	
	<header>
		<h1>Contadores:</h1>
	</header>
	
	<div class="main">
		<p>Es el ejerccio 19, pero se cambia la gestión de inicializar las
			variables a nivel de sesión y aplicación, ya que ahora se realiza
			mediante Listener.</p>

		<br/>
		<h3>Nivel de sesión (Local): <b><%=local%></b></h3>
		<h3>Nivel de aplicación (General): <b><%=global%></b></h3>
		<h3>Fecha de última visita: <%=obtenerCookie(request) %></h3>
		<h3>Usuarios activos: <%=userActivos%></h3>
		
		<br/><br/>
		<input class="botonMedio" type="button" value="Volver" 
			onClick="window.location.href='index.html'"/>
	</div>
</body>
</html>