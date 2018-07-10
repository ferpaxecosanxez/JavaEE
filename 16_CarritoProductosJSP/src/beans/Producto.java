package beans;

import java.math.BigDecimal;

public class Producto {
	private String nombre;
	private BigDecimal precio;
	private String categoria;

	public Producto(String nombre, BigDecimal precio, String categoria) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.categoria = categoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Producto [nombre=");
		builder.append(nombre);
		builder.append(", precio=");
		builder.append(precio);
		builder.append(", categoria=");
		builder.append(categoria);
		builder.append("]");
		return builder.toString();
	}
}