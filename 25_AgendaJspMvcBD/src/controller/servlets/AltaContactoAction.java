package controller.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Contacto;
import model.bean.Telefono;
import model.bean.enums.TipoTelefono;
import model.service.GestionContacto;
import model.service.GestionTelefono;

/**
 * Servlet que lee los datos enviados desde index.html para generar un CONTACTO
 * y TELEFONO en la BD de agenda, ya que los registros de estas dos tablas están
 * relacionados.
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
@WebServlet("/AltaContactoAction")
public class AltaContactoAction extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtener servicios.
		GestionContacto serviceContacto = new GestionContacto();
		GestionTelefono serviceTelefono = new GestionTelefono();

		Contacto contacto = new Contacto();
		contacto.setNombre(request.getParameter("nombre"));
		contacto.setDireccion(request.getParameter("direccion"));

		// Creamos registro CONTACTO para que se genere su ID.
		if (serviceContacto.create(contacto) == 1) {
			Telefono telefono = new Telefono();
			telefono.setTelefono(request.getParameter("telefono"));
			telefono.setTipo(TipoTelefono.getTipo(request.getParameter("tipo_telefono")));
			telefono.setIdContacto(contacto.getId());
			
			// Generar TELEFONO usando FK que es el ID de CONTACTO.
			if (serviceTelefono.create(telefono) == 1) {
				// Volvemos al menú principal una vez guardado el registro.
				request.getRequestDispatcher("Controlador?op=toIndex").forward(request, response);
			}
		}
	}

}
