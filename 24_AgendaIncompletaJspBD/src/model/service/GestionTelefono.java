package model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.bean.Telefono;
import model.bean.enums.TipoTelefono;

/**
 * Clase que actúa como Servicio y DAO de la entidad TELEFONO.
 * <p>
 * Actúa como servicio porque realiza la conexción a la BD y con dicho objeto
 * realiza acciones CRUD sobre la entidad de BD para gestionar registros.
 * 
 * @author fips
 *
 */
public class GestionTelefono {
	private StringBuffer url = new StringBuffer();
	private String user = "root";
	private String pwd = "admin";
	private String driver = "com.mysql.cj.jdbc.Driver";

	public GestionTelefono() {
		// Varifica que el driver existe, no es el mejor sitio para por el código pero
		// lo hacemos así por facilidad.
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
	 * Crea un registro en la tabla TELEFONO a partir de un bean sin ID.
	 * 
	 * @param telefono Bean que contiene la información a almacenar.
	 * @return 1, si el registro se ha creado correctamente.<br>
	 *         -1, si existe algún error.
	 */
	public int create(Telefono telefono) {
		String sql = "INSERT INTO telefono (telefono, tipo, idContacto) VALUES (?, ?, ?)";

		// try/cath con recursos.
		try (Connection cn = DriverManager.getConnection(url.toString(), user, pwd)) {
			// Preparar query.
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setString(1, telefono.getTelefono());
			ps.setString(2, telefono.getTipo().getNombre());
			ps.setInt(3, telefono.getIdContacto());

			// Ejecutar query (Leer documentación de método).
			return ps.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	/**
	 * Obtiene todos los registros de la tabla TELEFONO.
	 * 
	 * @return Lista completa de telefonos.
	 */
	public List<Telefono> getAll() {
		List<Telefono> lista = new ArrayList<>();
		String sql = "SELECT * FROM telefono";

		try (Connection cn = DriverManager.getConnection(url.toString(), user, pwd);) {
			// No tenemos que preparar query.
			Statement st = cn.createStatement();

			// Ejecutar query y obtener resultados.
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Telefono telefono = new Telefono();
				telefono.setId(rs.getInt("id"));
				telefono.setTelefono(rs.getString("telefono"));
				telefono.setTipo(TipoTelefono.getTipo(rs.getString("tipo")));
				telefono.setIdContacto(rs.getInt("idContacto"));
				
				lista.add(telefono);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return lista;
	}

}
