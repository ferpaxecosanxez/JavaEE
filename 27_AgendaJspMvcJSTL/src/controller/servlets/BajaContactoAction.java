package controller.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.pojo.Telefono;
import model.service.GestionContacto;
import model.service.GestionTelefono;

/**
 * Servlet que da de baja un CONTACTO y TELEFONO, es decir, que borra un
 * registro de cada tabla ya que estas están relacionadas.
 * <p>
 * Para evitar errores, primero tendremos que borrar el registro de TELEFONO y
 * luego el de CONTACTO.
 * <p>
 * Para simplificar, daremos por hecho que cada contacto tiene un único teléfono
 * asociado, por tanto, si tenemos el id de un CONTACTO, podremos borrar el
 * registro de TELEFONO mediante su FK que es única en la misma. Es decir, que
 * no necesitamos recuperar un pojo de contacto y un pojo teléfono para luego
 * borraralo.
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
@WebServlet("/BajaContactoAction")
public class BajaContactoAction extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtener servicios.
		GestionContacto serviceContacto = new GestionContacto();
		GestionTelefono serviceTelefono = new GestionTelefono();

		// Obtener id de CONTACTO que el usuario ha seleccionado para borrar.
		int idContacto = Integer.parseInt(request.getParameter("idContacto"));

		// Borrar registro de TELEFONO.
		Telefono telefono = serviceTelefono.get(idContacto);
		if (telefono != null) {
			serviceTelefono.delete(telefono.getId());

			// Borrar registro de CONTACTO.
			serviceContacto.delete(idContacto);

			// Actualizamos vista.
			request.getRequestDispatcher("Controlador?op=doVerContactos").forward(request, response);
		}
	}

}