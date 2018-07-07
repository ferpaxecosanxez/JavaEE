package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que genera código html y su contenido es una tabla que contiene las
 * multiplicaciones del 1 al 10.
 * <p>
 * Para ejecutar la aplicación debe ejecutarse directamente el servlet, porque
 * el proyecto web no contiene el fichero web.xml, el cual no es necesario para
 * este ejercicio.
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
@WebServlet("/Multiplicaciones")
public class Multiplicaciones extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// El objeto response es lo que se escupe, aqui le decimos que tipo va a ser, el
		// tipo puede ser cualquier formato que pueda interpretar un navegador.
		response.setContentType("text/html");

		// Try con recursos.
		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html lang=\"es\">");
			out.println("<body>");
			out.println("<table border='1'>");
			for (int i = 1; i <= 10; i++) {
				out.println("<tr>");
				for (int j = 1; j <= 10; j++) {
					out.println("<td>");
					out.println(i * j);
					out.println("</td>");
				}
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("</body>");
			out.println("</html>");
		} // Fin de try
	}

}
