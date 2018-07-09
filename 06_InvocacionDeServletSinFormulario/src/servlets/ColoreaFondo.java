package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que muestra un mensaje de bienvenida con un color de fondo, dicho
 * color dependerá de la opción que el usuario elega desde las opciones de
 * index.html
 * <p>
 * Primer contacto con llamada a servlet sin formulario, esta llamada es igual
 * que si lo hiciesemos con un formulario de tipo GET, ya que la información de
 * la opción elegida viaja en la URL de forma visible, tal que:
 * ...?nombre=valor&nombre=valor&nombre=valor
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
