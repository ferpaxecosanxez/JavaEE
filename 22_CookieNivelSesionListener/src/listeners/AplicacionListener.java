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
public class AplicacionListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext sesion = arg0.getServletContext();

		// Contador de entradas a nivel de aplicación.
		sesion.setAttribute("global", 0);
		
		// Contador de usarios activos en la aplicación.
		sesion.setAttribute("usuariosActivos", 0);
	}
	
}
