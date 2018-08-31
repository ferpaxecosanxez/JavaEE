<%@page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder"%>
<%@ page import="java.io.UnsupportedEncodingException"%>

<!DOCTYPE html>
<html lang='es'>

<head>
	<meta charset='UTF-8'>
	<title>Login simple</title>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>

<body>
	<%!
	// Este método es local a este fichero jsp, no entra en el servlet
	// Para ello, usamos < % ! definición de mi método % >
	private String obtenerCookie(HttpServletRequest request){
		String valorDeCookie = "";
		Cookie[] todas = request.getCookies();
		if (todas != null) {
			for (Cookie ck : todas) {
				if (ck.getName().equals("ck_recordarUser")) {
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
	
	<header>
		<h1>Identificación:</h1>
	</header>
	
	<div class="main">
		<form action="UserLogin" method="post"
			onsubmit="return validaFormulario(this)">
			<span>Usuario:</span>
			<input class="dato" type="text" name="user" value="<%=obtenerCookie(request)%>"/>
			
			<br/><br/>
			<span>Contraseña:</span>
			<input class="dato" type="password" name="pass"/>
			
			<br/><br/>
			<div class="formOpciones">
				<input type="checkbox" name="recordarUser" value="si"/>
				<span>Recordar usuario.</span>
			</div>
			
			<br/><br/>
			<div class="formFooter">
				<input class="botonMedio" type="submit" value="Entrar"/>
			</div>
		</form>
	</div>
	
	<script src="js/functions.js"></script>
</body>
</html>