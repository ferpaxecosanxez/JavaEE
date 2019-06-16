package model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.bean.Cliente;


/**
 * Clase que actúa como Servicio y DAO de la entidad Cliente.
 * <p>
 * Actúa como servicio porque realiza la conexción a la BD y con dicho objeto
 * realiza acciones CRUD sobre la entidad de BD para gestionar registros.
 * 
 * @author fips
 *
 */
class GestionClienteImpl implements GestionCliente {
	private StringBuffer url = new StringBuffer();
	private String user = "root";
	private String pwd = "admin";
	private String driver = "com.mysql.cj.jdbc.Driver";

	public GestionClienteImpl() {
		// Varifica que el driver existe, no es el mejor sitio para por el código pero
		// lo hacemos así por facilidad.
		try {
			// Las versiones actuales de JDBC necesitan N parámetros.
			url.append("jdbc:mysql://localhost:3306/libros");
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

	@Override
	public Integer create(Cliente cliente) {
		String sql = "INSERT INTO cliente (nombre, apellido, dni) VALUES (?, ?, ?)";

		// try/cath con recursos.
		try (Connection cn = DriverManager.getConnection(url.toString(), user, pwd)) {
			// Preparar query.
			PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getApellido());
			ps.setString(3, cliente.getDni());

			// Ejecutar query (Leer documentación de método).
			Integer result = ps.executeUpdate();
			if (result > 0) {
				// Obtener el ID generado y adjuntarselo al objeto.
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					cliente.setId(rs.getInt(1));
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

	@Override
	public Cliente get(Integer id) {
		Cliente result = null;
		String sql = "SELECT * FROM cliente WHERE id = ?";

		// try/cath con recursos.
		try (Connection cn = DriverManager.getConnection(url.toString(), user, pwd);) {
			// Preparar query.
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setInt(1, id.intValue());

			// Ejecutar query y obtener resultados.
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(new Integer(rs.getInt("id")));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApellido(rs.getString("apellido"));
				cliente.setDni(rs.getString("dni"));

				result = cliente;
			}
			rs.close();
			ps.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return result;
	}

}
