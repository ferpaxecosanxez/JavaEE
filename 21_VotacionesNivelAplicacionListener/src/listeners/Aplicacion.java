package listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Servlet de tipo Listener que inicializa dos variables a nivel de aplicación.
 * <p>
 * Listener que se encarga de gestionar los eventos generales de la aplicación
 * como son arranque y parada.
 * <p>
 * Información en: https://www.arquitecturajava.com/servletcontextlistener/
 * 
 * @author fips
 *
 */
@WebListener
public class Aplicacion implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// Obtener aplicación.
		ServletContext sc = arg0.getServletContext();

		// Inicializar variable globales.
		sc.setAttribute("si", 0);
		sc.setAttribute("no", 0);
	}

}
