package controller.servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.service.GestionUsuario;
import model.service.LibreriaFactory;

/**
 * Servlet que lee los datos enviados desde index.html y usa el nombre de
 * usuario para guardarlo como Atributo de sesión.
 * <p>
 * El atributo de sesión se genera, si solo si, el login es correcto. Se
 * verifica que el login sea correcto realizando consulta con los datos
 * capturados en "index.html" sobre la BD
 * <p>
 * Contiene la funcionalidad de Cookie, la cual, almacena el nombre de usuario
 * cuando se loguea.
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
@WebServlet("/LoginAction")
public class LoginAction extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Valores obtenidos desde formulario.
		String pass = request.getParameter("pass");
		String user = request.getParameter("user");

		// Ahora GestionUsuario es una interfaz, pero aplicando polimorfismo le estamos
		// dando una clase que implementa esa interfaz, por tanto, al llamar a los
		// métodos de la interfaz, estamos llamando a última versión del método, es
		// decir, la definición del método.
		GestionUsuario gestionUsuario = LibreriaFactory.getGestionUsuario();

		RequestDispatcher rd = null;
		if (gestionUsuario.get(user, pass) != null) {
			// Login correcto, guardamos nombre de usuario como atributo de sesión
			HttpSession session = request.getSession();
			session.setAttribute("usuario", user);

			// Antes de irme compruebo si ha marcado el check de guardar usuario.
			String recordarUser = request.getParameter("recordarUser");
			if (recordarUser != null) {
				// Ha marcado el checkbox.
				crearCookie(response, user);
			} else {
				// Si en algún momento marcó la opción y la desmarca, no se debe de recordar el
				// nombre de usuario.
				crearCookie(response, "");
			}

			// Enviar respuesta a cliente y transferir a la acción temas.
			response.sendRedirect("Controller?op=doTemas");
		} else {
			// Transferir a servlet Error sin enviar respuesta a cliente, por lo que no
			// perdemos los datos capturados del formulario de index.html
			rd = request.getRequestDispatcher("Controller?op=toErrorLogin");
			// Ejecutar transferencia.
			rd.forward(request, response);
		}
	}

	/**
	 * Método que genera un cookie, la cual almacena el nombre de usuario que se ha
	 * logueado.
	 * <p>
	 * El nombre de usuario puede contener espacios, los cuales no se puede
	 * almacenar en una cookie, por tanto, nos apoyamos en el objeto capaz de
	 * codificar el resultado y en la JSP debemos usar el objeto que descodifica el
	 * contenido almacenado en la cookie.
	 * 
	 * @param response
	 *            Argumento que viene desde el método service(), se corresponde con
	 *            el objeto de la cabecera de la petición.
	 * @param valor
	 *            Nombre de usuario que se va ha almacenar.
	 */
	private void crearCookie(HttpServletResponse response, String valor) {
		// Creamos una cookie: Identificador de cookie y valor que se quiere guardar en
		// forma de String.
		Cookie ck;
		try {
			ck = new Cookie("ck_recordarUser", URLEncoder.encode(valor, "UTF-8"));
			// Duración de vida de la cookie en el pc del cliente.
			ck.setMaxAge(20000);
			// Añadimos la cookie en la cabecera de la petición.
			response.addCookie(ck);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
