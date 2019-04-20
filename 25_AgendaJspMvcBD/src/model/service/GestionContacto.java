package model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.pojo.Contacto;

/**
 * Clase que actúa como Servicio y DAO de la entidad CONTACTO.
 * <p>
 * Actúa como servicio porque realiza la conexción a la BD y con dicho objeto
 * realiza acciones CRUD sobre la entidad de BD para gestionar registros.
 * 
 * @author fips
 *
 */
public class GestionContacto {
	private StringBuffer url = new StringBuffer();
	private String user = "root";
	private String pwd = "admin";
	private String driver = "com.mysql.cj.jdbc.Driver";

	public GestionContacto() {
		// Verifica que el driver existe, no es el mejor sitio para el código pero lo
		// hacemos así por facilidad.
		try {
			// Las versiones actuales de JDBC necesitan N parámetros.
			url.append("jdbc:mysql://localhost:3306/agenda");
			url.append("?autoReconnect=true");
			url.append("&useSSL=false");
			url.append("&useUnicode=true");
			url.append("&useJDBCCompliantTimezoneShift=true");
			url.append("&useLegacyDatetimeCode=false");
			url.append("&serverTimezone=UTC");
			url.append("&allowPublicKeyRetrieval=true");

			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crea un registro en la tabla CONTACTO a partir de un bean sin ID.
	 * <p>
	 * En este caso solo usaremos los campos "nombre" y "direccion" ya que el ID es
	 * autogenerado y la descripción tiene valor por defecto.
	 * 
	 * @param contacto Bean que contiene la información a almacenar.
	 * @return 1, si el registro se ha creado correctamente.<br>
	 *         -1, si existe algún error.
	 */
	public int create(Contacto contacto) {
		String sql = "INSERT INTO contacto (nombre, direccion) VALUES (?, ?)";

		// try/cath con recursos. 
		try (Connection cn = DriverManager.getConnection(url.toString(), user, pwd)) {
			// Preparar query.
			PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, contacto.getNombre());
			ps.setString(2, contacto.getDireccion());

			// Ejecutar query (Leer documentación de método).
			int result = ps.executeUpdate();
			if (result > 0) {
				// Obtener el ID generado y adjuntarselo al objeto.
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					contacto.setId(rs.getInt(1));
				}
				rs.close();
				ps.close();
			}
			return result;
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * Elimina un resgistro de la tabla CONTACTO a partir del ID dado.
	 * 
	 * @param id Identificador del registro a borrar.
	 * @return 1, si el borrado se ha realizado correctamente.<br>
	 *         -1, si existe algún error.
	 */
	public int delete(int id) {
		String sql = "DELETE FROM contacto WHERE id = ?";

		// try/cath con recursos.
		try (Connection cn = DriverManager.getConnection(url.toString(), user, pwd);) {
			// Preparar query.
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setInt(1, id);

			// Ejecutar query.
			return ps.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	/**
	 * Obtiene todos los registros de la tabla CONTACTO.
	 * 
	 * @return Lista completa de contactos.
	 */
	public List<Contacto> getAll() {
		List<Contacto> lista = new ArrayList<>();
		String sql = "SELECT * FROM contacto";

		try (Connection cn = DriverManager.getConnection(url.toString(), user, pwd);) {
			// No tenemos que preparar query.
			Statement st = cn.createStatement();

			// Ejecutar query y obtener resultados.
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Contacto contacto = new Contacto();
				contacto.setId(rs.getInt("id"));
				contacto.setNombre(rs.getString("nombre"));
				contacto.setDireccion(rs.getString("direccion"));
				contacto.setDescripcion(rs.getString("descripcion"));
				
				lista.add(contacto);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return lista;
	}

}
