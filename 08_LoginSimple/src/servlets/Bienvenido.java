package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servet que se ejecuta cuando el usuario a escrito la contraseña correcta.
 * Pinta la página de acceso que es la de bienvenida.
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
@WebServlet("/Bienvenido")
public class Bienvenido extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// La contraseña es correcta, entra aquí.
		String user = request.getParameter("user");

		response.setContentType("text/html");
		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html lang='es'>");
			out.println("");
			out.println("<head>");
			out.println("<meta charset='UTF-8'>");
			out.println("<title>Bienvenido</title>");
			out.println("</head>");
			out.println("");
			out.println("<body>");
			out.println("<h1>Bienvenido " + user + "</h1>");
			out.println("<br/><br/>");
			out.println("<input type='submit' value='Volver' onClick='window.location.href=\"index.html\"'/>");
			out.println("</body>");
			out.println("</html>");
		}
	}

}
