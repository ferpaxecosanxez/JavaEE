package servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * El contéo de los votos se hace mediante el envío de un atributo de petición,
 * el cuál puede contener un voto u otro.
 * <p>
 * Enviamos respuesta a cliente para evitarnos el configurar el navegador. Si no
 * enviamos respuesta al cliente, puede que los resultado no se actualicen.
 * 
 * @author fips
 * 
 */
@SuppressWarnings("serial")
@WebServlet("/CuentaVotos")
public class CuentaVotos extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtener el dato del formulario index.html
		String voto = request.getParameter("voto");
		// Obtener aplicación.
		ServletContext aplicacion = this.getServletContext();

		int contSi, contNo;
		synchronized (aplicacion) {
			if (voto.equals("si")) {
				contSi = (Integer) aplicacion.getAttribute("si");
				contSi++;
				// Actualizamos valor.
				aplicacion.setAttribute("si", contSi);
			} else {
				contNo = (Integer) aplicacion.getAttribute("no");
				contNo++;
				// Actualizamos valor.
				aplicacion.setAttribute("no", contNo);
			}
		}

		// request.getRequestDispatcher("index.html").forward(request, response);
		// Enviamos respuesta a cliente para que los resultados se actualicen sin
		// necesidad de configurar el navegador.
		response.sendRedirect("index.html");
	}

}
