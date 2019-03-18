package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet simple que genera y escupe código html. El contenido html es una
 * tabla que contiene las multiplicaciones del 1 al 10.
 * <p>
 * Para ver el resultado debemos ejecutar el servlet de forma directa en Tomcat
 * ya que el proyecto web no contiene el fichero web.xml.
 * <p>
 * Agregamos indentación en el código html que escupe, esta acción se realiza
 * para cuando hacemos Ctrl + U en el navegador, este nos muestre un código
 * legible y ordenado.
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
			out.println("<html lang='es'>");
			out.println("<body>");
			out.println("");// lïnea en blanco.
			out.println("\t<table border='1'>");
			for (int i = 1; i <= 10; i++) {
				out.println("\t\t<tr>");
				for (int j = 1; j <= 10; j++) {
					// Si es el primer dato que se pinta, este debe estar indentado.
					if (j == 1) {
						out.print("\t\t\t");
					} else {
						// Lo pintanmos junto al dato anterior.
						out.print("<td>");
						out.print(i * j);
						out.print("</td>");
					}
					
					// Si es el último dato que pintamos, este debe tener un salto de línea.
					if (j == 10) {
						out.println("");
					}
				}
				out.println("\t\t</tr>");
			}
			out.println("\t</table>");
			out.println("");// lïnea en blanco.
			out.println("</body>");
			out.println("</html>");
		} // Fin de try
	}

}
