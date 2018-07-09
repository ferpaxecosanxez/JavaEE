package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CierraSesion
 */
@WebServlet("/CierraSesion")
public class CierraSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// obtenemos HttpSession
		HttpSession sesion = request.getSession();

		// Cerrar la sessión de usuario
		sesion.invalidate();

		// Transferir a servlet de inicio.
		request.getRequestDispatcher("index.html").forward(request, response);
	}

}
