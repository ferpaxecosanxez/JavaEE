package model.service;

import model.bean.Cliente;

/**
 * Interfaz que define los métodos a implementar por la clase que actúa como
 * Servicio y DAO de la entidad Cliente.
 * 
 * @author fips
 *
 */
public interface GestionCliente {
	/**
	 * Crea un registro en la tabla a partir de un Bean sin ID.
	 * 
	 * @param cliente
	 *            Bean que contiene la información a almacenar.
	 * @return 1, si el registro se ha creado correctamente.<br>
	 *         -1, en caso contrario.
	 */
	Integer create(Cliente cliente);

	/**
	 * Obtiene un registro de Base de Datos a partir de su idenficador.
	 * 
	 * @param id
	 *            PK que identifica un único registro de tabla.
	 * @return Un Cliente .<br>
	 *         null, en caso contrario.
	 */
	Cliente get(Integer id);

}
