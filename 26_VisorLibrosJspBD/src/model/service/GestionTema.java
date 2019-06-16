package model.service;

import java.util.List;

import model.bean.Tema;

/**
 * Interfaz que define los métodos a implementar por la clase que actúa como
 * Servicio y DAO de la entidad Cliente.
 * 
 * @author fips
 *
 */
public interface GestionTema {
	/**
	 * Obtiene todos los registros de la tabla Tema.
	 * 
	 * @return Lista completa de temas.
	 */
    List<Tema> getAll();
    
}
