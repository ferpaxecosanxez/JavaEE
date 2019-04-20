package model.pojo;

import model.pojo.enums.TipoTelefono;

/**
 * Bean simple que contiene información básica de un teléfono.
 * 
 * @author fips
 *
 */
public class Telefono {
	private int id;
	private String telefono;
	private TipoTelefono tipo;
	private int idContacto;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public TipoTelefono getTipo() {
		return tipo;
	}

	public void setTipo(TipoTelefono tipo) {
		this.tipo = tipo;
	}

	public int getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(int idContacto) {
		this.idContacto = idContacto;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Telefono [id=");
		builder.append(id);
		builder.append(", telefono=");
		builder.append(telefono);
		builder.append(", tipo=");
		builder.append(tipo.getNombre());
		builder.append(", idContacto=");
		builder.append(idContacto);
		builder.append("]");
		return builder.toString();
	}

}
