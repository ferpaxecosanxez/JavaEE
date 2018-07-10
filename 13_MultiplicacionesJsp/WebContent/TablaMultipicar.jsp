<%@page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang='es'>

<head>
	<meta charset='UTF-8'>
	<title>Multiplicaciones</title>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>

<body>
	<header>
		<h1>Multiplicaciones</h1>
	</header>
	
	<div class="main">
		<table class="tabla">
			<%
			for (int i=1; i<=10; i++) {
			%>
			<tr class="tablaFila">
				<%
				for (int j=1; j<=10; j++) {
				%>
				<td class="tablaDato"><%=(i * j)%></td>
				<%
				}
				%>
			</tr>
			<%
			}
			%>
		</table>
	</div>
</body>
</html>
