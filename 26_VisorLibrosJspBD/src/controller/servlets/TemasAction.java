package controller.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.GestionTema;
import model.service.LibreriaFactory;

/**
 * Servlet que se ejecuta cuando el usuario realiza el login con éxito.
 * <p>
 * Se encarga de obtener todos temas para luego guardarlos como un atributo de
 * petición y finaliza invocando a la JSP encargada de pintar dicha lista para
 * que el usuario pueda seleccionar un tema.
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
@WebServlet("/TemasAction")
public class TemasAction extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Invocar al servicio.
		GestionTema gestionTema = LibreriaFactory.getGestionTema();

		// Obtener temas, guardarlos en atriuto de petición.
		request.setAttribute("listaTemas", gestionTema.getAll());

		// Invocar temas JSP sin enviar respuesta a cliente para no perder el atributo
		// de petición que acabamos de guardar.
		request.getRequestDispatcher("Controller?op=toTemas").forward(request, response);
	}

}
