package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojos.Producto;

/**
 * Servlet que recoge los datos del formulario de index.html para generar un
 * objeto de tipo Producto, generado el objeto, este se almacena en el carrito,
 * es decir, que se añade un elemento a la lista almacenada a nivel de sesión.
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
@WebServlet("/Agregar")
public class Agregar extends HttpServlet {

	@SuppressWarnings("unchecked")
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Definir en encodig antes de capturar los datos.
		request.setCharacterEncoding("UTF-8");
		
		// Obtener parámetros y crear objeto.
		String nombre = request.getParameter("nombre");
		BigDecimal precio = new BigDecimal(request.getParameter("precio"));
		String categoria = request.getParameter("categoria");
		
		Producto producto = new Producto(nombre, precio, categoria);

		// Obtener la sesión de usuario.
		HttpSession session = request.getSession();

		List<Producto> carrito;
		if (session.getAttribute("carrito") != null) {
			// El atributo de sesión (el carrito) existe.
			carrito = (List<Producto>) session.getAttribute("carrito");
			carrito.add(producto);
		} else {
			// El atributo de sesión (el carrito) no existe aún, por tanto lo creamos, ya
			// que si se ha invocado a este servlet, significa que ha creado un producto y
			// quiere guardarlo en el carrito.
			carrito = new ArrayList<>();
			carrito.add(producto);
			session.setAttribute("carrito", carrito);
		}

		// Volvemos a la página de inicio sin enviar respuesta a cliente ya que no
		// mostramos el estado de carrito.
		request.getRequestDispatcher("index.html").forward(request, response);
	}

}