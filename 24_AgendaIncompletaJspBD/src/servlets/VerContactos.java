package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.domain.pojo.ContactoAgenda;
import model.pojo.Contacto;
import model.pojo.Telefono;
import model.service.GestionContacto;
import model.service.GestionTelefono;

/**
 * Servlet que obtiene todos los registros de CONTACTO y TELEFONO. Con la
 * información obtenida genera Bean de dominio (ContactoAgenda) para mostrarlo
 * al cliente.
 * <p>
 * En teoría cada CONTACTO puede tener varios TELEFONO, pero por no complicar
 * este ejercicio damos por hecho que un contacto tiene un único teléfono.
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
@WebServlet("/VerContactos")
public class VerContactos extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<ContactoAgenda> contactosAgenda = new ArrayList<>();

		// Obtener servicios.
		GestionContacto serviceContacto = new GestionContacto();
		GestionTelefono serviceTelefono = new GestionTelefono();

		// Obtener listas de registros.
		List<Contacto> contactos = serviceContacto.getAll();
		List<Telefono> telefonos = serviceTelefono.getAll();

		// Recorremos contatos.
		for (Contacto contacto : contactos) {
			// Uso de expresión lambda para buscar registro teléfono mediante FK.
			Optional<Telefono> optional = telefonos.stream().filter(t -> t.getIdContacto() == contacto.getId()).findFirst();

			if (optional.isPresent()) {
				Telefono telefono = optional.get();
				// Creamos bean de dominio.
				ContactoAgenda contactoAgenda = new ContactoAgenda();
				contactoAgenda.setNombre(contacto.getNombre());
				contactoAgenda.setTelefono(telefono.getTelefono());
				contactoAgenda.setDireccion(contacto.getDireccion());
	
				contactosAgenda.add(contactoAgenda);
			}
		}

		// Guardar objteo Lista como parámetro de petición.
		request.setAttribute("contactos_agenda", contactosAgenda);

		// Sin enviar respuesta a cliente, invocar JSP encargado de pintar la lista de
		// resultados.
		request.getRequestDispatcher("visualizaContactos.jsp").forward(request, response);
	}
}
