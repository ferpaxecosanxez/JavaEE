package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Invocación de Servlet que muestra un mensaje de bienvenida con un color de
 * fondo, dicho color dependerá de la opción que el usuario eliga desde las
 * opciones de index.html y que lo aplica directamente en el código html que
 * genera y escupe, es decir, no usa ningún fichero CSS.
 * <p>
 * En este caso se hace la llamada directa del servlet desde un link o botón,
 * tenemos que tener en cuenta que el dato que estamos enviando desde index.html
 * al servlet, lo adjuntamos en la llamada al servlet, por tanto, es como si
 * usásemos un formulario de tipo GET, ya que el dato viaja en la URL tal que:
 * <p>
 * MiServlet?nombre=valor&nombre=valor&nombre=valor
 * <p>
 * En este ejercicio solo usamos CSS. Fijamos un tamaño de 100px para el botón
 * de tamaño medio, así evitamos que su tamaño esté en función de su texto.
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
@WebServlet("/ColoreaFondo")
public class ColoreaFondo extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Leer parámetro.
		String color = request.getParameter("color");

		response.setContentType("text/html");
		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html lang='es'>");
			out.println("");
			out.println("<head>");
			out.println("<meta charset='UTF-8'>");
			out.println("<title>Fondo</title>");
			out.println("</head>");
			out.println("");
			out.println("<body bgcolor='" + color + "'>");
			out.println("<h1>Bienvenido!</h1>");
			out.println("<p>El color del fondo es el que se ha seleccionado</p>");
			out.println("<br/><br/>");
			out.println("<input type='submit' value='Volver' onClick='window.location.href=\"index.html\"'/>");
			out.println("</body>");
			out.println("</html>");
		}
	}

}
