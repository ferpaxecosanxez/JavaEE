package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que es invocado desde otro servlet, por tanto, el objeto
 * <code>request</code> en este caso ya contiene un dato, el cual se ha
 * capturado desde index.html y se lo pasa a este nuevo servlet, pero en la
 * invocación a este segundo servlet, vemos que le adjuntamos un dato desde el
 * primer servlet, dicho dato es aquí donde se recoge.
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
@WebServlet("/ServletDos")
public class ServletDos extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recoger parámetro de formulario como el que envía Servlet uno.
		String nombre = request.getParameter("nombre");
		String dato = request.getParameter("dato");

		response.setContentType("text/html");
		try (PrintWriter out = response.getWriter();) {
			out.println("<!DOCTYPE html>");
			out.println("<html lang='es'>");
			out.println("");
			out.println("<head>");
			out.println("<meta charset='UTF-8'>");
			out.println("<title>Adjuntar dato</title>");
			out.println("</head>");
			out.println("");
			out.println("<body>");
			out.println("<h1>Hola " + nombre + "</h1>");
			out.println("<p>Dato generado en Servlet uno: <b>" + dato + "</b>");
			out.println("<br/><br/>");
			out.println("<input type='submit' value='Volver' onClick='window.location.href=\"index.html\"'/>");
			out.println("</body>");
			out.println("</html>");
		}
	}

}
