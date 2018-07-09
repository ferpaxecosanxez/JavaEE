package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet que muestra el n�mero de veces que se ha dado al bot�n "Entrar" que
 * est� en el fichero index.html
 * <p>
 * Tenemos dos casos:
 * <ul>
 * <li>No enviar respuesta a cliente: El n�mero de veces puede llegar a ser
 * siempre 1, ya que los navegadores no recargan contenido, para que lo recarge
 * debemos desactivar algunas opciones.</li>
 * <li>Enviar respuesta a cliente: Siempre recarga contenido, por tanto si se
 * contabiliza.</li>
 * </ul>
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
@WebServlet("/Resultados")
public class Resultados extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtener sesi�n actual.
		HttpSession session = request.getSession();
		int valor;

		if (session.getAttribute("contador") != null) {
			// Existe atributo de sessi�n.
			valor = (Integer) session.getAttribute("contador");
		} else {
			// No existe atributo de sessi�n, por tanto no ha entrado al servlet de Entrar.
			// Por tanto su valor ser� 0.
			valor = 0;
		}

		response.setContentType("text/html");
		try (PrintWriter out = response.getWriter();) {
			out.println("<!DOCTYPE html>");
			out.println("<html lang='es'>");
			out.println("");
			out.println("<head>");
			out.println("<meta charset='UTF-8'>");
			out.println("<title>Resultados</title>");
			out.println("</head>");
			out.println("");
			out.println("<body>");
			out.println("<h1>Has entrado <b>" + valor + "</b> veces</h1>");
			out.println("<br/><br/>");
			out.println("<input type='submit' value='Volver' onClick='window.location.href=\"index.html\"'/>");
			out.println("</body>");
			out.println("</html>");
		}
	}

}
