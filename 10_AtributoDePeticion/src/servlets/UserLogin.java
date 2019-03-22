package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Usuario;

/**
 * Servlet que lee los datos enviados desde index.html y usa uno de ellos para
 * generar un bean de tipo Usuario para luego guardarlo como Atributo de
 * petición.
 * 
 * @author fpachecs
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
			 // Creamos un usuario por defecto y lo guardamos como atributo de petición, este
			 // atributo seguirá existiendo mientras no se de respuesta al cliente.
			Usuario unUser = new Usuario();
			unUser.setEmail("correo@fips.com");
			unUser.setNombre(user);
			unUser.setTelefono(601524963);
			
			// Método para almacenar un atributo de petición.
			request.setAttribute("userDefault", unUser);

			// Transferir a servlet Bienvenido.
			rd = request.getRequestDispatcher("Bienvenido");
		} else {
			// Transferir a servlet Error.
			rd = request.getRequestDispatcher("Error");
		}

		// Ejecutar transferencia.
		rd.forward(request, response);
	}

}
