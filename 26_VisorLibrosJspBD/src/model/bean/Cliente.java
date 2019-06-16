package model.bean;

import java.io.Serializable;

/**
 * Bean que contiene información básica de un cliente que representa a una
 * persona física.
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
public class Cliente implements Serializable {
	private Integer id;
	private String nombre;
	private String apellido;
	private String dni;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cliente [id=");
		builder.append(id);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", apellido=");
		builder.append(apellido);
		builder.append(", dni=");
		builder.append(dni);
		builder.append("]");
		return builder.toString();
	}

}
