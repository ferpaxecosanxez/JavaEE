package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Usuario;

/**
 * Servlet que muestra información del usuario logado y muestra el tema elegido
 * desde la página temas.html, la cual invoca a este servlet.
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
@WebServlet("/TemaElegido")
public class TemaElegido extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtenemos el bean de sesion.
		HttpSession sesion = request.getSession();
		Usuario unUser = (Usuario) sesion.getAttribute("userDefault");
		// Obtenemos tema elegido,
		String tema = request.getParameter("tema");

		response.setContentType("text/html");
		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html lang='es'>");
			out.println("");
			out.println("<head>");
			out.println("<meta charset='UTF-8'>");
			out.println("<title>Tema</title>");
			out.println("</head>");
			out.println("");
			out.println("<body>");
			out.println("<h1>Tema seleccionado</h1>");
			out.println("<p>Su nombre es: " + unUser.getNombre() + "</p>");
			out.println("<p>Su correo por defecto es: " + unUser.getEmail() + "</p>");
			out.println("<p>Su teléfono por defecto es: " + unUser.getTelefono() + "</p>");
			out.println("<p>Tema seleccionado es: <b>" + tema + "</b></p>");
			out.println("<br/><br/>");
			out.println("<input type='submit' value='Cerrar Sesion' onClick='window.location.href=\"CierraSesion\"'/>");
			out.println("<br/><br/>");
			out.println("<input type='submit' value='Volver' onClick='window.location.href=\"temas.html\"'/>");
			out.println("</body>");
			out.println("</html>");
		}
	}
}
