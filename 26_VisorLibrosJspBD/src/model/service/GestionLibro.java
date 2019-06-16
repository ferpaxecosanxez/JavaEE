package model.service;

import java.util.List;

import model.bean.Libro;

/**
 * Interfaz que define los métodos a implementar por la clase que actúa como
 * Servicio y DAO de la entidad Cliente.
 * 
 * @author fips
 *
 */
public interface GestionLibro {
	/**
	 * Obtiene todos los registros de la tabla Libro.
	 * 
	 * @return Lista completa de libros.
	 */
	List<Libro> getAll();

	/**
	 * Obtiene todos los registros de la tabla Libro filtrados por tema.
	 * 
	 * @param idTema
	 *            Identificador de tema.
	 * @return Lista completa de libros.
	 */
	List<Libro> getAll(Integer idTema);

}
