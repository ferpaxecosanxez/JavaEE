<%@page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang='es'>

<head>
	<meta charset='UTF-8'>
	<title>Error</title>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>

<body>
	<header>
		<h1>Contraseña incorrecta</h1>
	</header>
	
	<div class="main">
		<p>La contraseña <b><%=request.getParameter("pass")%></b> es icorrecta</p>	
		
		<br/><br/>
		<input class="botonMedio" type="button" value="Volver" 
			onClick="window.location.href='index.jsp'"/>
	</div>
</body>
</html>
