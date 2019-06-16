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
 * pasando los parámetros que ya contengan.
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
@WebServlet("/Controller")
public class Controller extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Definir en encodig antes de leer/obtener los datos.
		request.setCharacterEncoding("UTF-8");

		// Obtener petición.
		String op = request.getParameter("op");
		String url = "";

		switch (op) {
		// Acciones ("do").
		case "doLibros":
			url = "LibrosAction";
			break;
		case "doLogin":
			url = "LoginAction";
			break;
		case "doRegistro":
			url = "RegistroAction";
			break;
		case "doTemas":
			url = "TemasAction";
			break;
		// Vistas ("to").
		case "toErrorLogin":
			url = "errorLogin.jsp";
			break;
		case "toIndex":
			url = "index.jsp";
			break;
		case "toLibros":
			url = "libros.jsp";
			break;
		case "toRegistro":
			url = "registro.html";
			break;
		case "toSinLibros":
			url = "sinLibros.html";
			break;
		case "toTemas":
			url = "temas.jsp";
			break;
		}

		// No enviamos respuesta a cliente para no perder posibles parámetros extras que
		// se hayan adjuntado en las peticiones.
		request.getRequestDispatcher(url).forward(request, response);
	}

}
