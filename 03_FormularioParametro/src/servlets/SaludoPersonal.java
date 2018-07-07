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
 * index.html
 * <p>
 * A partir de este proyecto o ejercicio empezamos a usar el fichero web.xml, el
 * cual nos permite ejecutar la aplicación desde el propio proyecto.
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
