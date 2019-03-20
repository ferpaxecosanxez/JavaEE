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
 * Los TYPE de INPUT que usamos son: text, number, date, checkbox y radio. Para
 * el ComboBox hemos usado el tag select.
 * <p>
 * Enviar dato a trav�z de formulario de tipo GET y usamos atributo ONSUBMIT. El
 * modo GET muestra los valores que se est�n enviando en la URL, es decir, son
 * visibles.
 * <p>
 * Usamos funci�n JS que contiene validaciones sobre los campos del formulario.
 * <p>
 * Se a�ade filtro en el campo num�rico, en concreto, de m�nimo y m�ximo de
 * edad, definido en la propia etiqueta INPUT.
 * <p>
 * Todas las p�ginas que no tienen CSS ni JS son las que genera y escupe el
 * propio Servlet.
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
