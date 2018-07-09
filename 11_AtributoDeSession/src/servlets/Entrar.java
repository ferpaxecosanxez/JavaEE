package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet que simula una entrada, por cada vez que se de al bot�n, se sumar�
 * una entrada. Como ejemplo, podr�amos decir que es un contador de elementos.
 * <p>
 * Para probar que es un atributo de sesi�n, enviaremos la respuesta al cliente,
 * en este caso, los datos almacenados como atributos de sesi�n existir�n
 * mietras la sesi�n est� abierta, por tanto, el enviar respuesta al cliente no
 * le afecta.
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
@WebServlet("/Entrar")
public class Entrar extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Los atributos de sesion son accesibles por cualquiera, se env�e o no
		// respuesta al cliente, pero tiene un tiempo de vida, el se sessi�n.
		HttpSession session = request.getSession();

		// Cuando lo llamamos, hay que saber si es la primera vez que lo llamamos o no,
		// para hacer una acci�n u otra.
		int valor;
		if (session.getAttribute("contador") != null) {
			// El atributo de sesi�n existe.
			valor = (Integer) session.getAttribute("contador");
			valor++;
			session.setAttribute("contador", valor);
		} else {
			// El atributo de sesi�n no existe a�n, por tanto no tiene valor asignado, le
			// damos el valor de 1, ya que si este servlet se ejecuta, significa que ha
			// entrado una vez.
			valor = 1;
			session.setAttribute("contador", valor);
		}
		// Env�a respuesta a cliente, por tanto el conteo es instant�neo, no es
		// necesario configurar el explorador.
		response.sendRedirect("index.html");

		// Transferimos la petici�n a la p�gina inicio.html sin enviar respuesta a
		// cliente. Actualiza contador si de desactivan opciones del navegador.
		// request.getRequestDispatcher("index.html").forward(request, response);
	}

}
