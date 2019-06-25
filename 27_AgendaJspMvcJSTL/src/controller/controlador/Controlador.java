package controller.controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Clase por la cual pasan todas las peticiones de la aplicación. En función de
 * la operación que lee muestra una vista o invoca una acción.
 * <p>
 * Las vistas y acciones las ordenamos en orden alfabético para que sean más
 * fáciles de identificarlas de un vistazo.
 * <p>
 * Definifimos el encoding a UTF-8 aquí, porque es aquí donde llegan todas las
 * peticiones y desde aquí es donde se invocan las acciones o vistas pero
 * pasando los parámetros que ya contengan. Este es el primer contacto que
 * tienen valores de los parámetros de formularios.
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
@WebServlet("/Controlador")
public class Controlador extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Definir en encodig antes de leer/obtener los datos.
		request.setCharacterEncoding("UTF-8");

		// Obtener petición.
		String op = request.getParameter("op");
		String url = "";

		switch (op) {
		// Acciones ("do").
		case "doAltaContacto":
			url = "AltaContactoAction";
			break;
		case "doBajaContacto":
			url = "BajaContactoAction";
			break;
		case "doVerContactos":
			url = "VerContactosAction";
			break;
		// Vistas ("to").
		case "toIndex":
			url = "index.html";
			break;
		case "toNuevoContacto":
			url = "nuevoContacto.html";
			break;
		case "toSinContactos":
			url = "sinContactos.html";
			break;
		case "toVisualizaContactos":
			url = "visualizaContactos.jsp";
			break;
		}

		// No enviamos respuesta a cliente para no perder posibles parámetros extras que
		// se hayan adjuntado en las peticiones.
		request.getRequestDispatcher(url).forward(request, response);
	}

}
