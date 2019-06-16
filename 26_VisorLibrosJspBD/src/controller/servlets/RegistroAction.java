package controller.servlets;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Cliente;
import model.bean.Usuario;
import model.service.GestionCliente;
import model.service.GestionUsuario;
import model.service.LibreriaFactory;

/**
 * Servlet que captura los datos del formulario implementado en registro.html
 * para crear un Cliente y Usuario.
 * <p>
 * Hay que tener en cuenta que el Usuario depende del Cliente, por tanto,
 * primero guardará un Cliente y luego un Usuario, ya que este último tiene una
 * referencia hacia Cliente.
 * <p>
 * En el servicio correspondiente obtiene el ID auto-generado del Cliente
 * generado y se lo adjunta al mismo Cliente, así podremos usar ese ID y
 * adjuntárselo como referencia en Usuario.
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
@WebServlet("/RegistroAction")
public class RegistroAction extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtener servicios.
		GestionCliente gestionCliente = LibreriaFactory.getGestionCliente();
		GestionUsuario gestionUsuario = LibreriaFactory.getGestionUsuario();

		// Generar cliente a partir de formulario.
		Cliente cliente = new Cliente();
		cliente.setNombre(request.getParameter("nombre"));
		cliente.setApellido(request.getParameter("apellido"));
		cliente.setDni(request.getParameter("dni"));

		// Creamos registro CLIENTE para que se genere su ID.
		if (gestionCliente.create(cliente) == 1) {
			// Generar usuario a partir de formulario.
			Usuario usuario = new Usuario();
			usuario.setUsuario(request.getParameter("user"));
			usuario.setPass(request.getParameter("pass"));
			usuario.setIdCliente(cliente.getId());
			usuario.setUltimoAcceso(LocalDateTime.now());
			
			// Guardamos el USUARIO.
			if (gestionUsuario.create(usuario) == 1) {
				// Volvemos al menú principal una vez guardado el registro.
				request.getRequestDispatcher("Controller?op=toIndex").forward(request, response);
			}
		}
	}

}
