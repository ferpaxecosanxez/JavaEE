<%@page language="java" pageEncoding="UTF-8"%>
<%@page import="model.domain.pojo.ContactoAgenda"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html lang='es'>

<head>
	<meta charset='UTF-8'>
	<title>Lista Contactos</title>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>

<body>
	<%
	@SuppressWarnings("unchecked")
	List<ContactoAgenda> contactosAgenda = (List<ContactoAgenda>) request.getAttribute("contactos_agenda");
	
	if (contactosAgenda == null || contactosAgenda.size() == 0) {
	%>
		<jsp:forward page="Controlador?op=toSinContactos"/>
	<%
	} else {
	%>
		<header>
			<h1>Contactos actuales</h1>
		</header>
	
		<div class="main">
			<table class="tabla">
				<tr class="tablaFila">
					<th class="tablaTitulo">Nombre</th>
					<th class="tablaTitulo">Teléfono</th>
					<th class="tablaTitulo">Dirección</th>
					<th class="tablaTitulo">Opción</th>
				</tr>
				<%
				for (ContactoAgenda contactoAgenda : contactosAgenda) { 
				%>
				<tr class="tablaFila">					
					<td class="tablaDato"><%=contactoAgenda.getNombre()%></td>
					<td class="tablaDato"><%=contactoAgenda.getTelefono()%></td>
					<td class="tablaDato"><%=contactoAgenda.getDireccion()%></td>
					<td class="tablaDato">
						<input class="botonMedio" type="button" value="Eliminar" 
							onClick="window.location.href='Controlador?op=doBajaContacto&idContacto=<%=contactoAgenda.getId()%>'"/>
					</td>
				</tr>
				<%
				}
				%>
			</table>
			
			<br/><br/>
			<input class="botonMedio" type="button" value="Volver" 
				onClick="window.location.href='Controlador?op=toIndex'"/>
		</div>
	<%
	}
	%>
</body>
</html>