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
 * Servlet con m�todo init() que realiza acciones antes de generar el servlet en
 * s�.
 * <p>
 * Gestiona atributos a nivel de sesi�n y de aplicaci�n, para la gesti�n de
 * atributos de aplicaci�n usa el m�todo "synchronized()" para no perder
 * informaci�n de las N sesiones.
 * <p>
 * Realiza el cont�o a nivel de sesi�n y aplicaci�n, en el de sesi�n se refleja
 * las entradas en la sesi�n y en la de aplicaci�n refleja el cont�o de todas
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

		// Para no configurar navegador, enviamos respuesta a cliente para que el
		// resultado se actualice a nivel de sesi�n y de aplicaci�n.
		response.sendRedirect("index.html");

		// Para no perder tiempo, no enviamos respuesta al cliente, aunque puede que
		// tengamos que configurar el navegador para que refresque el contenido.
		// request.getRequestDispatcher("index.html").forward(request, response);
	}

}
