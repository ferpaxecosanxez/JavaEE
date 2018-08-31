package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet que obtiene la sesi�n actual para cerrarla, es decir, que borra toda
 * la informaci�n a nivel de petici�n y sesi�n.
 * 
 * @author fips
 */
@SuppressWarnings("serial")
@WebServlet("/CierraSesion")
public class CierraSesion extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtenemos sesi�n.
		HttpSession sesion = request.getSession();

		// Cerrar la sessi�n de usuario
		sesion.invalidate();

		// Transferir a servlet de inicio. Nos da igual enviar o no respuesta al
		// cliente, ya que estamos re dirigiendo a una p�gina est�tica de HTML 5, la
		// cual no puede leer atributos de servidor.
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
