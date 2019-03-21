package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet simple que genera y escupe código html mostrando el mensaje de "hola
 * mundo".
 * <p>
 * Para ver el resultado debemos ejecutar el servlet de forma directa en Tomcat
 * ya que el proyecto web no contiene el fichero web.xml
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
@WebServlet("/Saludo")
public class Saludo extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// El objeto response es lo que se escupe, aqui le decimos que tipo va a ser, el
		// tipo puede ser cualquier formato que pueda interpretar un navegador.
		response.setContentType("text/html");

		// Obtener el flujo de escritura.
		try (PrintWriter out = response.getWriter()) {
			// Escribimos código HTML5.
			out.println("<!DOCTYPE html>");
			out.println("<html lang='es'>");
			out.println("<body>");
			out.println("<h1>Hola mundo</h1>");
			out.println("</body>");
			out.println("</html>");
		}
	}

}
