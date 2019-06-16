package controller.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Libro;
import model.service.GestionLibro;
import model.service.LibreriaFactory;

/**
 * Servlet que recoge el ID de Tema seleccionado en el ComboBox por el usuario
 * de temas.jsp para poder invocar al método correspondiente, es decir, mostrar
 * todos los libros o mostrar libros pertenecientes a un Tema.
 * <p>
 * Obtenida la lista de libros, esta es almacenada en un atributo de petición e
 * invoca a la JSP encargada de pintar los libros.
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
@WebServlet("/LibrosAction")
public class LibrosAction extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtener ID de tema seleccionado por el usuario en ComboBox.
		Integer id = new Integer(request.getParameter("id"));

		// Invocar servicio en función del tema seleccionado.
		GestionLibro gestionLibro = LibreriaFactory.getGestionLibro();
		List<Libro> listaLibros;
		if (id == 0) {
			listaLibros = gestionLibro.getAll();
		} else {
			listaLibros = gestionLibro.getAll(id);
		}

		// Guarda lista de libros en atributo de petición.
		request.setAttribute("listaLibros", listaLibros);

		// Invocar libros JSP sin enviar respuesta a cliente para no perder el atributo
		// de petición que acabamos de guardar.
		request.getRequestDispatcher("Controller?op=toLibros").forward(request, response);
	}

}
