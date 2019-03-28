package servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet con método init() que realiza acciones antes de generar el servlet en
 * sí.
 * <p>
 * Gestiona atributos a nivel de sesión y de aplicación, para la gestión de
 * atributos de aplicación usa el método "synchronized()" para no perder
 * información de las N sesiones.
 * <p>
 * Realiza el contéo a nivel de sesión y aplicación, en el de sesión se refleja
 * las entradas en la sesión y en la de aplicación refleja el contéo de todas
 * las sesiones.
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
@WebServlet("/Entrar")
public class Entrar extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		// En este método no es recomendable usar la palabra clave this para acceder a
		// recursos del objeto, ya que este método parte de antes de la creación de este
		// método, por tanto, para ahorrarnos excepciones de null, no usaremos this.

		// Este método se usa para inicializar valores o realizar acciones antes de
		// generar el servlet, en este caso, inicializamos el valor de la variable de
		// aplicación a 0 para luego no estar vericando si es null o no.
		ServletContext sc = config.getServletContext();
		sc.setAttribute("global", 0);

		// Llamada al método init padre.
		super.init(config);
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtenemos la SESION del usuario.
		HttpSession session = request.getSession();
		int valor;

		// Varificar si el contador de sesión está inicializado.
		if (session.getAttribute("contador") != null) {
			// Existe el atributo de sesión.
			valor = (Integer) session.getAttribute("contador");
			valor++;
			// Actualizamos valor a nivel de sesión.
			session.setAttribute("contador", valor);
		} else {
			// Si no existe, lo creamos y tendrá el valor de uno porque está entrando a este
			// servlet.
			valor = 1;
			session.setAttribute("contador", valor);
		}

		// Obtener APLICACION para leer los atributos de aplicación.
		ServletContext application = this.getServletContext();

		int global;
		// Un atributo de aplicación es un recurso compartido por todos los usuarios,
		// por tanto debemos ponerlo en un bloque synchronized.
		synchronized (application) {
			// No verificamos si es nulo o no porque hemos iniciado el valor a 0 con el
			// método init().
			global = (Integer) application.getAttribute("global");
			global++;
			// Actualizamos valor a nivel de aplicación.
			application.setAttribute("global", global);
		}

		// Para no configurar navegador, enviamos respuesta a cliente para que el
		// resultado se actualice a nivel de sesión y de aplicación.
		response.sendRedirect("index.html");

		// Para no perder tiempo, no enviamos respuesta al cliente, aunque puede que
		// tengamos que configurar el navegador para que refresque el contenido.
		// request.getRequestDispatcher("index.html").forward(request, response);
	}

}
