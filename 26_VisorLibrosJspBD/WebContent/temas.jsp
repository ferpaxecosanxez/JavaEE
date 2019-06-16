<%@page language="java" pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="model.bean.Tema"%>

<!DOCTYPE html>
<html lang='es'>

<head>
	<meta charset='UTF-8'>
	<title>Elegir Tema</title>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>

<body>
	<header>
		<h1>Seleccione un tema:</h1>
	</header>
	
	<main>
		<%
		@SuppressWarnings("unchecked")
		List<Tema> temas = (List<Tema>)request.getAttribute("listaTemas");
		%>
		<form action="Controller?op=doLibros" method="post">
			<select class="comboBox" name="id" size="1">
				<option value="0">Ver todos los temas</option>
				<%			
				for(Tema t : temas) {
				%>
				<option value="<%=t.getId()%>"><%=t.getTema()%></option>
				<%
				}
				%>
			</select>
			
			<br/><br/>
			<div class="formFooter">
				<input class="botonLargo" type="submit" value="Ver Libros"/>
			</div>
		</form>		
	</main>
</body>
</html>