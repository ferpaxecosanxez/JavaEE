<%@page language="java" pageEncoding="UTF-8"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.math.RoundingMode"%>

<!DOCTYPE html>
<html lang='es'>

<head>
	<meta charset='UTF-8'>
	<title>Resultados App</title>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>

<body>
	<%
    BigDecimal numSi = BigDecimal.ZERO;
    BigDecimal numNo = BigDecimal.ZERO;
    BigDecimal total = BigDecimal.ZERO;
    BigDecimal totalSi = BigDecimal.ZERO; 
    BigDecimal totalNo = BigDecimal.ZERO;
    // Votos de si.
    if(application.getAttribute("si") != null){
    	numSi = new BigDecimal((Integer) application.getAttribute("si"));
    }
    // Votos de no.
    if(application.getAttribute("no") != null){
    	numNo = new BigDecimal((Integer) application.getAttribute("no"));
    }
    // Dependiendo del valor obtenido, enviamos a una página u otra.
    if(numSi.equals(BigDecimal.ZERO) && numNo.equals(BigDecimal.ZERO)){
    %>
    	<!-- Invocar a página que informa que no hay votos -->
    	<jsp:forward page="noHayVotos.html"/>
    <%
    }else{
    	// El total de votos siempre será entero
    	total = numSi.add(numNo);
    	totalSi = (numSi.multiply(BigDecimal.valueOf(100))).divide(total, 2, RoundingMode.CEILING);
        totalNo = (numNo.multiply(BigDecimal.valueOf(100))).divide(total, 2, RoundingMode.CEILING);
    %>
	    <header>
	           <h1>Resultado de votaciones</h1>
	    </header>
	    
	    <div class="main">
	           <h3>Si: <%=numSi + " votos " + totalSi%>%</h3>
	           <h3>No: <%=numNo + " votos " + totalNo%>%</h3>
	           
	           <br/>
	           <h3>Total votos: <%=total%> votos</h3>
	           
	           <br/><br/>
	           <input class="botonMedio" type="submit" value="Volver" 
	                  onClick="window.location.href='index.html'"/>
	    </div>
    <%
    }      
    %>
</body>
</html>