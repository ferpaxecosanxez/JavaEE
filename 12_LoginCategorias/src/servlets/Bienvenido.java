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
 * Servet que se ejecuta cuando el usuario a escrito la contraseña correcta. Lee
 * el objeto de sesión para mostrar sus datos.
 * <p>
 * Para leer un atributo de sesión, tenemos que obtener la sesión actual y luego
 * obtener los atributos, por ello le pasamos el parámetro "false".
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
@WebServlet("/Bienvenido")
public class Bienvenido extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtener la sesión existente, por ello le pasamos "false" al constructor.
		HttpSession session = request.getSession(false);
		Usuario unUser = (Usuario) session.getAttribute("userDefault");

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
			out.println("<h1>Bienvenido " + unUser.getNombre() + "</h1>");
			out.println("<p>Su correo por defecto es: " + unUser.getEmail() + "</p>");
			out.println("<p>Su teléfono por defecto es: " + unUser.getTelefono() + "</p>");
			out.println("<br/><br/>");
			out.println("<input type='submit' value='Volver' onClick='window.location.href=\"index.html\"'/>");
			out.println("</body>");
			out.println("</html>");
		}
	}

}
