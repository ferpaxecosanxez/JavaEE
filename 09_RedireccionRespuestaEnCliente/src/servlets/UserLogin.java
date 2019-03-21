package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que verifica los datos que se envían desde el formulario de
 * index.html, en función de la contraseña escrita nos redirige a una página de
 * error o de acceso.
 * <p>
 * En este caso, vamos a perder los datos cuando invoquemos al servlet de
 * Bienvenido o de Error, ya que los invocaremos enviando respuesta al cliente.
 * <p>
 * Usaremos el objeto response.
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtenemos datos del formulario.
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");

		// Si no adjuntamos los datos como parámetros query (?clave=valor), en el
		// servlet destino, los datos no estarán disponibles.
		if (pass.compareTo("curso") == 0) {
			// Transferir a servlet Bienvenido.
			response.sendRedirect("Bienvenido?user=" + user);
		} else {
			// Transferir a servlet Error.
			response.sendRedirect("Error?pass=" + pass);
		}
	}

}
