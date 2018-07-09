package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que se ejecuta cuando el usuario ha puesto mal la
 * contraseña. Pinta la página de error.
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
@WebServlet("/Error")
public class Error extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// La contraseña es incorrecta, entra aquí.
		String pass = request.getParameter("pass");

		response.setContentType("text/html");
		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html lang='es'>");
			out.println("");
			out.println("<head>");
			out.println("<meta charset='UTF-8'>");
			out.println("<title>Error</title>");
			out.println("</head>");
			out.println("");
			out.println("<body>");
			out.println("<h1>Error en contraseña</h1>");
			out.println("<p>La contraseña: <b>" + pass + "</b> es icorrecta</p>");
			out.println("<br/><br/>");
			out.println("<input type='submit' value='Volver' onClick='window.location.href=\"index.html\"'/>");
			out.println("</body>");
			out.println("</html>");
		}
	}

}
