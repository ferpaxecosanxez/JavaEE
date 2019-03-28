<%@page language="java" pageEncoding="UTF-8"%>
<%@page import="beans.Producto"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang='es'>

<head>
	<meta charset='UTF-8'>
	<title>Carrito</title>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>

<body>
	<%
	@SuppressWarnings("unchecked")
	List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
	if(carrito == null || carrito.size() == 0){
	%>
	<!-- Re dirección sin enviar respuesta a cliente -->		
	<jsp:forward page="carritoVacio.html"/>
	<%
	}else{
	%>
	<!-- Mostrar productos del carrito -->
	<header>
		<h1>Productos actuales en carrito</h1>
	</header>
	
	<div class="main">
		<table class="tabla">
			<tr class="tablaFila">
				<th class="tablaTitulo">Opción</th>
				<th class="tablaTitulo">Nombre</th>
				<th class="tablaTitulo">Precio</th>
				<th class="tablaTitulo">Categoría</th>
			</tr>
			<%
			for(int i = 0; i < carrito.size(); i++){
				Producto p = carrito.get(i);
			%>
			<tr class="tablaFila">
				<td class="tablaDato">
					<input class="botonOpcion" type="button" value="Eliminar" 
						onClick="window.location.href='Eliminar?indice=<%=i%>'"/>
				</td>
				<td class="tablaDato"><%=p.getNombre()%></td>
				<td class="tablaDato"><%=p.getPrecio()%></td>
				<td class="tablaDato"><%=p.getCategoria()%></td>
			</tr>				
			<%
			}
			%>
		</table>
		<br/><br/>
		<input class="botonMedio" type="button" value="Volver" 
			onClick="window.location.href='index.html'"/>
	</div>		
	<%
	}
	%>
</body>
</html>
