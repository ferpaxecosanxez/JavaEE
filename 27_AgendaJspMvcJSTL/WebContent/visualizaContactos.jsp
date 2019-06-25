<%@page language="java" pageEncoding="UTF-8"%>
<%@page import="model.domain.pojo.ContactoAgenda"%>
<%@page import="java.util.List"%>

<!-- Importar librería JSTL que incluye EL -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang='es'>

<head>
	<meta charset='UTF-8'>
	<title>Lista Contactos</title>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>

<body>
	<header>
		<h1>Contactos actuales</h1>
	</header>

	<main>
		<c:set var="contactosAgenda" value="${requestScope.contactos_agenda}" />
		<c:choose>
			<c:when test="${empty contactosAgenda}">
				<jsp:forward page="Controlador?op=toSinContactos"/>
			</c:when>
			
			<c:otherwise>
				<table class="tabla">
					<!-- Cabeceras de las columnas -->
					<tr class="tablaFila">
						<th class="tablaTitulo">Nombre</th>
						<th class="tablaTitulo">Teléfono</th>
						<th class="tablaTitulo">Dirección</th>
						<th class="tablaTitulo">Opción</th>
					</tr>
					
					<!-- Datos de las columnas -->
					<c:forEach var="contactoAgenda" items="${contactosAgenda}" varStatus="i">
						<tr class="tablaFila">					
							<td class="tablaDato">${contactoAgenda.getNombre()}</td>
							<td class="tablaDato">${contactoAgenda.getTelefono()}</td>
							<td class="tablaDato">${contactoAgenda.getDireccion()}</td>
							<td class="tablaDato">
								<input class="botonMedio" type="button" value="Eliminar" 
									onClick="window.location.href='Controlador?op=doBajaContacto&idContacto=${contactoAgenda.getId()}'"/>
							</td>
						</tr>
					</c:forEach>
				</table>
				
				<br/><br/>
				<input class="botonMedio" type="button" value="Volver" 
					onClick="window.location.href='Controlador?op=toIndex'"/>
			</c:otherwise>
		</c:choose>
	</main>
</body>
</html>