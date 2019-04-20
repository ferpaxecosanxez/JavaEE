package model.pojo.enums;

/**
 * Clase que define los tipos posibles de teléfono. Los tipos definidos son los
 * que puede aceptar la BD.
 * 
 * @author fips.
 *
 */
public enum TipoTelefono {
	MOVIL("Movil"),
	FIJO("Fijo"),
	TRABAJO("Trabajo");

	private String nombre;
	
	private TipoTelefono(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Obtiene el objeto enumerado a partir del nombre de tipo de teléfono.
	 * 
	 * @param nombre Nombre del tipo de teléfono a obtener.
	 * @return Un objeto enumerado (TipoTelefono) que representa al nombre de tipo de teléfono.
	 */
	public static TipoTelefono getTipo(String nombre) {
		TipoTelefono result = null;

		switch (nombre) {
		case "Movil":
			result = MOVIL;
			break;
		case "Fijo":
			result = FIJO;
			break;
		default:
			result = TRABAJO;
			break;
		}

		return result;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
