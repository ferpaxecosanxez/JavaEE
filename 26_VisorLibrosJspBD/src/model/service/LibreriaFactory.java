package model.service;

/**
 * Clase que centraliza todos los servicios de modelo y da acceso a los métodos
 * de los mismos.
 * <p>
 * Los nombres de los métodos son muy descriptivos y con leerlos se sabe a que
 * entidad vamos a atacar.
 * 
 * @author fips
 *
 */
public class LibreriaFactory {
	
	public static GestionUsuario getGestionUsuario() {
		return new GestionUsuarioImpl();
	}

	public static GestionCliente getGestionCliente() {
		return new GestionClienteImpl();
	}

	public static GestionLibro getGestionLibro() {
		return new GestionLibroImpl();
	}
	
	public static GestionTema getGestionTema() {
		return new GestionTemaImpl();
	}
	
}
