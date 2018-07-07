package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que muestra las multiplicaciones del 1 al 10 de un número introducido
 * por el usuario en el formulario definido en el fichero index.html
 * <p>
 * Agregamos botón para volver, es código JS que nos envía a la página de
 * inicio. Se añade validación de dato en formulario mediante JS.
 * <p>
 * Los estilos CSS y las funciones JS no afectan a la página que genera el
 * servlet porque no estamos importanto los ficheros, ya que no es agradable
 * estar creando más lineas con PrintWriter.
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
@WebServlet("/CalculaTablaMultiplicar")
public class CalculaTablaMultiplicar extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String numParaCalcular = request.getParameter("numero");

		response.setContentType("text/html");
		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html lang='es'>");
			out.println("<body>");
			out.println("<h1> Tabla del " + numParaCalcular + "</h1>");
			out.println("<table border='1'>");
			for (int i = 1; i <= 10; i++) {
				out.println("<tr>");
				out.println("<td>" + numParaCalcular + " x " + i + ": </td>");
				out.println("<td>");
				out.println(Integer.parseInt(numParaCalcular) * i);
				out.println("</td>");
				out.println("</tr>");
			}
			out.println("</table>");

			out.println("<br/><br/>");
			out.println("<input type='submit' value='Volver' onClick='window.location.href=\"inicio.html\"'/>");

			out.println("</body>");
			out.println("</html>");
		}
	}

}
