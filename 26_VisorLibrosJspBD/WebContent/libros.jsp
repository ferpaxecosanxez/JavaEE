<%@page language="java" pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="model.bean.Libro"%>

<!DOCTYPE html>
<html lang='es'>

<head>
	<meta charset='UTF-8'>
	<title>Libros</title>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>

<body>
    <header>
		<h1>Lista de libros:</h1>
	</header>
	
	<main>
		<%
		@SuppressWarnings("unchecked")
		List<Libro> libros = (List<Libro>)request.getAttribute("listaLibros");
		if(libros.size() == 0) {
		%>
			<jsp:forward page="Controller?op=toSinLibros"/>
		<%
		} else {
		%>		
		<table class="tabla">
			<tr class="tablaFila">
				<th class="tablaTitulo">Titulo</th>
				<th class="tablaTitulo">Autor</th>
				<th class="tablaTitulo">Precio</th>
				<th class="tablaTitulo">Páginas</th>
			</tr>
			<%
			for(Libro l : libros) {
			%>   
			<tr class="tablaFila">             
              	<td class="tablaDato"><%=l.getTitulo()%></td>
				<td class="tablaDato"><%=l.getAutor()%></td>
				<td class="tablaDato"><%=l.getPrecio()%></td>
				<td class="tablaDato"><%=l.getPaginas()%></td>
			</tr>
			<%
			}
			%>
		</table>
		    
		<br/><br/>
		<div class="formFooter">
			<input class="botonLargo" type="button" value="Elegir otro tema" 
				onClick="window.location.href='Controller?op=doTemas'"/>
			<input class="botonLargo" type="button" value="Salir" 
				onClick="window.location.href='Controller?op=doIndex'"/>
		</div>
	<%
	}
	%>
	</main>
</body>
</html>