package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que verifica los datos que se env�an desde el formulario de
 * index.html, en funci�n de la contrase�a escrita nos redirige a una p�gina de
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
		// Obtenemos dato contrase�a, si es la definida hace la llamada al servlet
		// encargado de pintar la p�gina de acceso, de no coincidir llama al servlet
		// encargado de generar la p�gina de error.
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
