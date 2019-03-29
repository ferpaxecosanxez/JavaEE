package listeners;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Servlet de tipo Listener que inicializa dos variables a nivel de sesión.
 * <p>
 * Listener que se encarga de gestionar los eventos generales de la sesión como
 * son arranque y parada.
 * <p>
 * Información en: https://www.arquitecturajava.com/servletcontextlistener/
 * 
 * @author fips
 *
 */
@WebListener
public class SesionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent arg0) {
		// Contador de entradas del usuario actual de la sesión.
		HttpSession sesion = arg0.getSession();
		sesion.setAttribute("contador", 0);

		// Cuando cada usuario se conecta a su sesion aumenta la variable de aplicación,
		// la de usuarios activos. Al ser un recurso compartido, debe ir con
		// synchronized.
		synchronized (arg0) {
			// Obtener aplicación a partir de la sesión del usuario actual.
			ServletContext aplicacion = sesion.getServletContext();

			Integer activos = (Integer) aplicacion.getAttribute("usuariosActivos");
			activos++;
			aplicacion.setAttribute("usuariosActivos", activos);

			// Traza en consola de eclipse.
			System.out.println("Un usuario se ha conectado: +1 Usuario");
		}
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		// Cuando acaba su sesion, normalmente por el TIMEOUT que definimos en el
		// fichero "web.xml", el contador de la variable "usuariosActivos" disminuye.
		synchronized (arg0) {
			// Obtener nivel aplicación.
			HttpSession sesion = arg0.getSession();
			ServletContext aplicacion = sesion.getServletContext();

			Integer activos = (Integer) aplicacion.getAttribute("usuariosActivos");
			activos--;
			aplicacion.setAttribute("usuariosActivos", activos);
			
			// Traza en consola de eclipse.
			System.out.println("Un usuario se ha desconectado: -1 Usuario");
		}
	}

}
