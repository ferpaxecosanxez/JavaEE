package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet que obtiene la sesión actual y la cierra. Una vez que ha cerrado la
 * sesión nos re-dirige a la página de inicio.
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
@WebServlet("/CierraSesion")
public class CierraSesion extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtenemos HttpSession actual.
		HttpSession sesion = request.getSession(false);

		// Cerrar la sessión de usuario obtenida.
		sesion.invalidate();

		// Transferir a servlet de inicio sin dar respuesta a cliente.
		request.getRequestDispatcher("index.html").forward(request, response);
	}

}
