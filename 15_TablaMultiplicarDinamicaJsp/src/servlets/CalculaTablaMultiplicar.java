package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que obtiene el valor enviado desde index.html para generar las
 * multiplicaciones definidas.
 * <p>
 * Si el dato es incorrecto, se re direcciona a página de error.jsp informando
 * del error sobre el dato introducido.
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
@WebServlet("/CalculaTablaMultiplicar")
public class CalculaTablaMultiplicar extends HttpServlet {
	private final int MAX = 12;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String numeroS = request.getParameter("numero");

		try {
			// Validamos si el dato es un número entero.
			int num = Integer.parseInt(numeroS);
			// Rellenamos resultados.
			int resultados[] = new int[MAX];
			for (int i = 0; i < MAX; i++) {
				resultados[i] = (i + 1) * num;
			}
			// Guardamos atributo de petición.
			request.setAttribute("resultados", resultados);
			// No enviamos respuesta a cliente e invocamos JSP.
			request.getRequestDispatcher("muestraResultados.jsp").forward(request, response);
		} catch (Exception e) {
			// Si el dato introducido es incorrecto. No enviamos respuesta a cliente.
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}

	}

}
