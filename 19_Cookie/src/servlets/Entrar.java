package servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet que a�ade la funcionalidad de agregar una cookie, es decir, que este
 * c�digo es igual que el c�digo del ejercicio 17_AtributoDeAplicacion.
 * 
 * @author fips
 * 
 */
@SuppressWarnings("serial")
@WebServlet("/Entrar")
public class Entrar extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		// En este m�todo no es recomendable usar la palabra clave this para acceder a
		// recursos del objeto, ya que este m�todo parte de antes de la creaci�n de este
		// m�todo, por tanto, para ahorrarnos excepciones de null, no usaremos this.

		// Este m�todo se usa para inicializar valores o realizar acciones antes de
		// generar el servlet, en este caso, inicializamos el valor de la variable de
		// aplicaci�n a 0 para luego no estar vericando si es null o no.
		ServletContext sc = config.getServletContext();
		sc.setAttribute("global", 0);

		// Llamada al m�todo init padre.
		super.init(config);
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtenemos la sesion del usuario.
		HttpSession session = request.getSession();
		int valor;

		// Varificar si el contador est� inicializado.
		if (session.getAttribute("contador") != null) {
			// Existe el atributo de sesi�n.
			valor = (Integer) session.getAttribute("contador");
			valor++;
			// Actualizamos valor a nivel de sesi�n.
			session.setAttribute("contador", valor);
		} else {
			// Si no existe, lo creamos y tendr� el valor de uno porque est� entrando a este
			// servlet.
			valor = 1;
			session.setAttribute("contador", valor);
		}

		// Obtener aplicaci�n para leer los atributos de aplicaci�n.
		ServletContext aplicacion = this.getServletContext();

		int global;
		// Un atributo de aplicaci�n es un recurso compartido por todos los usuarios,
		// por tanto debemos ponerlo en un bloque synchronized.
		synchronized (aplicacion) {
			// La l�nea siguiente no verifica si es nulo o no y es porque hemos iniciado el
			// valor a 0 con el m�todo init().
			global = (Integer) aplicacion.getAttribute("global");
			global++;
			// Actualizamos valor a nivel de aplicaci�n.
			aplicacion.setAttribute("global", global);
		}

		// Esta el la funcionalidad nueva: Creamos cookie.
		crearCookie(response);

		// Para no configurar navegador, enviamos respuesta a cliente para que el
		// resultado se actualice a nivel de sesi�n y de aplicaci�n.
		response.sendRedirect("index.html");

		// Para no perder tiempo, no enviamos respuesta al cliente, aunque puede que
		// tengamos que configurar el navegador para que refresque el contenido.
		// request.getRequestDispatcher("index.html").forward(request, response);
	}

	/**
	 * M�todo que genera un cookie, la cual almacena la fecha del �ltimo voto dado.
	 * <p>
	 * Se aplica formato de fecha, dicho formato contiene un espacio, el cual no se
	 * puede almacenar en una cookie, por tanto, nos apoyamos en el objeto capaz de
	 * codificar el resultado y en la JSP debemos usar el objeto que descodifica el
	 * contenido almacenado en la cookie.
	 * 
	 * @param response
	 *            Argumento que viene desde el m�todo service(), se corresponde con
	 *            el objeto de la cabecera de la petici�n.
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
			// Duraci�n de vida de la cookie en el pc del cliente.
			ck.setMaxAge(20000);
			// A�adimos la cookie en la cabecera de la petici�n.
			response.addCookie(ck);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
