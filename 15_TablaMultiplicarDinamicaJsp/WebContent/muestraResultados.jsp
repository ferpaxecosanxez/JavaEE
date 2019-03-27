<%@page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang='es'>

<head>
	<meta charset='UTF-8'>
	<title>Resultados</title>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>

<body>
	<header>
		<%
		String numero = request.getParameter("numero");
		%>
		<h1>Resultados de: <%=numero%></h1>
	</header>
	
	<div class="main">
		<%
		int[] resultados = (int[])request.getAttribute("resultados");
		%>
		<table class="tabla">
			<%
			for(int i = 0; i < resultados.length; i++){
			%>
			<tr class="tablaFila">
				<td class="tablaDato"><%=numero%> X <%=(i+1)%></td>
				<td class="tablaDato"><%=resultados[i]%></td>
			</tr>
			<%
			}
			%>	
		</table>
	</div>
	
	<%@ include file="footer.jsp"%>
</body>
</html>