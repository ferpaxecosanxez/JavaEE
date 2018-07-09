package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Usuario;

/**
 * Servlet que lee los datos enviados desde index.html y usa uno de ellos para
 * generar un bean de tipo Usuario para luego guardarlo como Atributo de sesi�n.
 * <p>
 * El atributo de sesi�n se genera, si solo si, el login es correcto.
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Valores obtenidos desde formulario.
		String pass = request.getParameter("pass");
		String user = request.getParameter("user");

		RequestDispatcher rd = null;
		if (pass.compareTo("curso") == 0) {
			// Creamos un usuario por defecto y lo guardamos como atributo de petici�n, este
			// atributo seguir� existiendo mientras no se de respuesta al cliente.
			Usuario unUser = new Usuario(user, "email@pordefecto.com", 123456789);
			// M�todo para almacenar un atributo de sesion.
			HttpSession session = request.getSession();
			session.setAttribute("userDefault", unUser);

			// Enviar respuesta a cliente y transferir a temas.
			response.sendRedirect("temas.html");
		} else {
			// Transferir a servlet Error sin enviar respuesta a cliente, por lo que no
			// perdemos los datos y podemos obtner pass desde el servlet destino.
			rd = request.getRequestDispatcher("Error");
			// Ejecutar transferencia.
			rd.forward(request, response);
		}
	}

}
