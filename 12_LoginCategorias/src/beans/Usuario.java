package beans;

import java.io.Serializable;

/**
 * Bean simple que contiene información básica de un usuario.
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
public class Usuario implements Serializable {

	private String nombre;
	private String email;
	private int telefono;

	// Getter y Setters.
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Usuario [nombre=");
		builder.append(nombre);
		builder.append(", email=");
		builder.append(email);
		builder.append(", telefono=");
		builder.append(telefono);
		builder.append("]");
		return builder.toString();
	}
	
}
