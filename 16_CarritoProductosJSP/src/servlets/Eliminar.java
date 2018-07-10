package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Producto;

/**
 * Servlet implementation class Eliminar
 */
@SuppressWarnings("serial")
@WebServlet("/Eliminar")
public class Eliminar extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		// Otenemos el carrito que está almacenado en la sesión.
		@SuppressWarnings("unchecked")
		List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");

		// Eliminamos el producto.
		int indice = Integer.parseInt(request.getParameter("indice"));
		carrito.remove(indice);

		// Regargamos la página del carrito.
		request.getRequestDispatcher("verCarrito.jsp").forward(request, response);
	}
}