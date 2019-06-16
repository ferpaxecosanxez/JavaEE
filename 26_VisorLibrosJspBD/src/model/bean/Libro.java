package model.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Bean que contiene información básica de un libro.
 * 
 * @author fips
 *
 */
@SuppressWarnings("serial")
public class Libro implements Serializable {
	private Integer isbn;
	private String titulo;
	private String autor;
	private BigDecimal precio;
	private Integer paginas;
	private Integer idTema;

	public Integer getIsbn() {
		return isbn;
	}

	public void setIsbn(Integer isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Integer getPaginas() {
		return paginas;
	}

	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}

	public Integer getIdTema() {
		return idTema;
	}

	public void setIdTema(Integer idTema) {
		this.idTema = idTema;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Libro [isbn=");
		builder.append(isbn);
		builder.append(", titulo=");
		builder.append(titulo);
		builder.append(", autor=");
		builder.append(autor);
		builder.append(", precio=");
		builder.append(precio);
		builder.append(", paginas=");
		builder.append(paginas);
		builder.append(", idTema=");
		builder.append(idTema);
		builder.append("]");
		return builder.toString();
	}

}
