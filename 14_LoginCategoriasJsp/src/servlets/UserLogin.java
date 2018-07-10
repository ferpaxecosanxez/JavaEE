package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet que lee los datos enviados desde index.html y usa el nombre de
 * usuario para guardarlo como Atributo de sesión.
 * <p>
 * El atributo de sesión se genera, si solo si, el login es correcto.
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
			// Login correcto, guardamos nombre de usuario como atributo de sesión
			HttpSession session = request.getSession();
			session.setAttribute("usuario", user);

			// Enviar respuesta a cliente y transferir a temas.
			response.sendRedirect("temas.html");
		} else {
			// Transferir a servlet Error sin enviar respuesta a cliente, por lo que no
			// perdemos los datos y podemos obtener pass desde el servlet destino.
			rd = request.getRequestDispatcher("error.jsp");
			// Ejecutar transferencia.
			rd.forward(request, response);
		}
	}

}
