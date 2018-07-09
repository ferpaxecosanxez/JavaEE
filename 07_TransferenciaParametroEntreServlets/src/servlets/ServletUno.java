package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que hace de puente para llegar a otro servlet (ServletDos) sin perder
 * la información que se captura, es decir, la información que se envía desde el
 * formulario definido en index.html.
 * <p>
 * Paso de información de un Servlet a otro, sin enviar respuesta al cliente,
 * ese detalle es importante, ya que si se envía la respuesta al cliente, el
 * dato capturado del formulario lo perdemos.
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
@WebServlet("/ServletUno")
public class ServletUno extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// El formulario invoca a este servlet, con esa onvocación, automáticamente esta
		// guardando el dato en el objeto request.
		String datoNuevo = "Dato nuevo";
		// Generamos un dato nuevo y lo adjuntamos al objeto request. En este caso lo
		// hacemos adjuntandolo en la llamada al segundo servlet, lo que significa que
		// este nuevo dato se podría ver en la URL (...?clave=valor&clave=valor).
		request.getRequestDispatcher("ServletDos?dato=" + datoNuevo).forward(request, response);
		// Otra manera de adjuntar el nuevo dato, es el uso de variable de petición (Que
		// lo veremos en el ejercicio 10 con más detalle), la cual se genera:
		
		//request.setAttribute("dato", datoNuevo);
		//request.getRequestDispatcher("ServletDos").forward(request, response);
	}
}
