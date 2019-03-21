package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que verifica los datos que se envían desde el formulario de
 * index.html, en función de la contraseña escrita nos redirige a una página de
 * error o de acceso.
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtenemos dato contraseña, si es la definida hace la llamada al servlet
		// encargado de pintar la página de acceso, de no coincidir llama al servlet
		// encargado de generar la página de error.
		String pass = request.getParameter("pass");

		RequestDispatcher rd = null;
		if (pass.equals("curso")) {
			// Transferir a servlet Bienvenido.
			rd = request.getRequestDispatcher("Bienvenido");
		} else {
			// Transferir a servlet Error.
			rd = request.getRequestDispatcher("Error");
		}

		// Ejecutar transferencia
		rd.forward(request, response);
	}

}
