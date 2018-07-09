package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que muestra informaci�n capturada de formulario implementado en
 * fichero index.html, el formulario contiene validaciones propias, es decir, en
 * la propia etiqueta INPUT.
 * <p>
 * La informaci�n se env�a mediante GET. Usamos funci�n JS para validar campos
 * del formulario.
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
@WebServlet("/LeerParametros")
public class LeerParametros extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Captura de los par�metros enviados por par�metro
		String nombre = request.getParameter("nombre");
		String edad = request.getParameter("edad");
		String fecha = request.getParameter("fecha");
		String genero = request.getParameter("genero");
		String zona = request.getParameter("zona");
		String[] temas = request.getParameterValues("tema");

		response.setContentType("text/html");
		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html lang='es'>");
			out.println("<body>");
			out.println("<h1>Bienvenido: " + nombre + "</h1>");
			out.println("<p>Su edad: <b>" + edad + "</b></p>");
			out.println("<p>Su fecha: <b>" + fecha + "</b></p>");
			out.println("<p>Su zona: <b>" + zona + "</b></p>");
			out.println("<p>Temas seleccionados: </p>");
			if (temas != null) {
				for (String tema : temas) {
					out.println("<br/>");
					out.println("<b>" + tema + "</b>");
				}
			}
			out.println("<p>G�nero: <b>" + genero + "</b></p>");
			
			out.println("<br/><br/>");
			out.println("<input type='submit' value='Volver' onClick='window.location.href=\"inicio.html\"'/>");

			out.println("</body>");
			out.println("</html>");
		}
	}

}
