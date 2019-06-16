package model.bean;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Bean simple que contiene información básica de un usuario.
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
public class Usuario implements Serializable {
	private Integer id;
	private String usuario;
	private String pass;
	private Integer idCliente;
	private LocalDateTime ultimoAcceso;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public LocalDateTime getUltimoAcceso() {
		return ultimoAcceso;
	}

	public void setUltimoAcceso(LocalDateTime ultimoAcceso) {
		this.ultimoAcceso = ultimoAcceso;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Usuario [id=");
		builder.append(id);
		builder.append(", usuario=");
		builder.append(usuario);
		builder.append(", pass=");
		builder.append(pass);
		builder.append(", idCliente=");
		builder.append(idCliente);
		builder.append(", ultimoAcceso=");
		builder.append(ultimoAcceso);
		builder.append("]");
		return builder.toString();
	}

}
