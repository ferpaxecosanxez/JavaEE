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

import beans.Producto;

/**
 * Servlet que recoge los datos del formulario de index.html para generar un
 * objeto de tipo Producto, generado el objeto, este se almacena en el carrito,
 * es decir, que se a�ade un elemento a la lista almacenada a nivel de sesi�n.
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
		// Obtener par�metros y crear objeto.
		String nombre = request.getParameter("nombre");
		BigDecimal precio = new BigDecimal(request.getParameter("precio"));
		String categoria = request.getParameter("categoria");

		Producto producto = new Producto(nombre, precio, categoria);

		// Obtener la sesi�n de usuario.
		HttpSession session = request.getSession();

		List<Producto> carrito;
		if (session.getAttribute("carrito") != null) {
			// El atributo de sesi�n (el carrito) existe.
			carrito = (List<Producto>) session.getAttribute("carrito");
			carrito.add(producto);
		} else {
			// El atributo de sesi�n (el carrito) no existe a�n, por tanto lo creamos, ya
			// que si se ha invocado a este servlet, significa que ha creado un producto y
			// quiere guardarlo en el carrito.
			carrito = new ArrayList<>();
			carrito.add(producto);
			session.setAttribute("carrito", carrito);
		}

		// Volvemos a la p�gina de inicio sin enviar respuesta a cliente.
		request.getRequestDispatcher("index.html").forward(request, response);
	}

}