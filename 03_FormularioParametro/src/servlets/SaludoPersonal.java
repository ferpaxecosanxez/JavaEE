package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que muestra un mensaje de saludo personalizado en función de que
 * nombre escriba el usuario en el formulario, este está definido en el fichero
 * index.html, este debe tener la etiqueta <meta charset="ISO-8859-1"> para los
 * caractéres en español.
 * <p>
 * Enviar dato a travez de formulario de tipo POST. Con el tipo POST estamos
 * ocultando la información que enviamos.
 * <P>
 * A partir de este ejercicio, todos los proyectos contendrán el fichero
 * web.xml, es decir, que podemos ejecutar desde el propio proyecto y empezamos
 * a usar páginas HTML5 y estilos CSS, a medida que avancemos en los ejercicios,
 * el CSS irá creciendo (Por el momento solo afecta a los ficheros html).
 * <p>
 * Definimos en fichero web.xml la página de inicio del proyecto, en nuestro
 * caso index.html
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
@WebServlet("/SaludoPersonal")
public class SaludoPersonal extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nomPersona = request.getParameter("nomPersona");

		response.setContentType("text/html");
		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html lang='es'>");
			out.println("<body>");
			out.println("<h1> Bienvenido " + nomPersona + "</h1>");
			out.println("</body>");
			out.println("</html>");
		}
	}

}
