package model.bean;

import java.io.Serializable;

/**
 * Bean que contiene información básica de un tema.
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
public class Tema implements Serializable {
	private Integer id;
	private String tema;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tema [id=");
		builder.append(id);
		builder.append(", tema=");
		builder.append(tema);
		builder.append("]");
		return builder.toString();
	}

}
