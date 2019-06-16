package model.service;

import model.bean.Usuario;

/**
 * Interfaz que define los métodos a implementar por la clase que actúa como
 * Servicio y DAO de la entidad Usuario.
 * 
 * @author fips
 *
 */
public interface GestionUsuario {

	/**
	 * Crea un registro en la tabla a partir de un Bean sin ID.
	 * 
	 * @param usuario
	 *            Bean que contiene la información a almacenar.
	 * @return 1, si el registro se ha creado correctamente.<br>
	 *         -1, en caso contrario.
	 */
	Integer create(Usuario usuario);

	/**
	 * Obtiene un registro de Base de Datos a partir de su idenficador.
	 * 
	 * @param id
	 *            PK que identifica un único registro de tabla.
	 * @return Un Usuario .<br>
	 *         null, en caso contrario.
	 */
	Usuario get(Integer id);
	
	/**
	 * Obtiene un registro de Base de Datos a partir de su usuario.
	 * 
	 * @param usuario
	 *            Nombre de usuario con el cual se identifica al hacer login.
	 * @return Un Usuario.<br>
	 *         null, en caso contrario.
	 */
	Usuario get(String usuario);

	/**
	 * Obtiene un registro de Base de Datos a partir de su usuario y contraseña.
	 * 
	 * @param usuario
	 *            Nombre de usuario con el cual se identifica al hacer login.
	 * @param pass
	 *            Contraseña asociada al usuario.
	 * @return Un Usuario.<br>
	 *         null, en caso contrario.
	 */
	Usuario get(String usuario, String pass);

}
