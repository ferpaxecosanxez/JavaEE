package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet que obtiene la sesión actual para cerrarla, es decir, que borra toda
 * la información a nivel de petición y sesión.
 * 
 * @author fips
 */
@SuppressWarnings("serial")
@WebServlet("/CierraSesion")
public class CierraSesion extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtenemos sesión.
		HttpSession sesion = request.getSession();

		// Cerrar la sessión de usuario
		sesion.invalidate();

		// Transferir a servlet de inicio. Nos da igual enviar o no respuesta al
		// cliente, ya que estamos re dirigiendo a una página estática de HTML 5, la
		// cual no puede leer atributos de servidor.
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
