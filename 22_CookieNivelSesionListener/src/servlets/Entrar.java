package servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet que es una copia del ejercicio 19, pero le quitamos la funcionalidad
 * de inicializar las variables a nivel de aplicación y de sesión.
 * <p>
 * Se la quitamos porque hemos generado un Listener a nivel de aplicación y otro
 * a nivel de sesión, los cuales ya se enecargan de inicializar las variables.
 * 
 * @author fips
 * 
 */
@SuppressWarnings("serial")
@WebServlet("/Entrar")
public class Entrar extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtenemos la sesion del usuario.
		HttpSession session = request.getSession();
		
		// Ya no nos hace falta comprobar si existe o no el atributo de sesión, ya que
		// existirá si o si, porque lo hemos inicializado a 0 en un Listener de sesión.
		int valor = (Integer) session.getAttribute("contador");
		valor++;
		// Actualizamos valor a nivel de sesión.
		session.setAttribute("contador", valor);
		
		// Obtener aplicación para leer los atributos de aplicación.
		ServletContext aplicacion = this.getServletContext();

		int global;
		// Un atributo de aplicación es un recurso compartido por todos los usuarios,
		// por tanto debemos ponerlo en un bloque synchronized.
		synchronized (aplicacion) {
			// La línea siguiente no verifica si es nulo o no y es porque hemos iniciado el
			// valor a 0 con el método init().
			global = (Integer) aplicacion.getAttribute("global");
			global++;
			// Actualizamos valor a nivel de aplicación.
			aplicacion.setAttribute("global", global);
		}

		// Cramos cookie.
		crearCookie(response);

		// Para no configurar navegador, enviamos respuesta a cliente para que el
		// resultado se actualice a nivel de sesión y de aplicación.
		response.sendRedirect("index.html");

		// Para no perder tiempo, no enviamos respuesta al cliente, aunque puede que
		// tengamos que configurar el navegador para que refresque el contenido.
		// request.getRequestDispatcher("index.html").forward(request, response);
	}

	/**
	 * Método que genera un cookie, la cual almacena la fecha del último voto dado.
	 * <p>
	 * Se aplica formato de fecha, dicho formato contiene un espacio, el cual no se
	 * puede almacenar en una cookie, por tanto, nos apoyamos en el objeto capaz de
	 * codificar el resultado y en la JSP debemos usar el objeto que descodifica el
	 * contenido almacenado en la cookie.
	 * 
	 * @param response
	 *            Argumento que viene desde el método service(), se corresponde con
	 *            el objeto de la cabecera de la petición.
	 */
	private void crearCookie(HttpServletResponse response) {
		// Creamos fecha y le damos formato.
		LocalDateTime fecha = LocalDateTime.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String fechaStr = fecha.format(formato);

		// Creamos una cookie: Identificador de cookie y valor que se quiere guardar en
		// forma de String.
		Cookie ck;
		try {
			ck = new Cookie("ck_visita", URLEncoder.encode(fechaStr, "UTF-8"));
			// Duración de vida de la cookie en el pc del cliente.
			ck.setMaxAge(20000);
			// Añadimos la cookie en la cabecera de la petición.
			response.addCookie(ck);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
