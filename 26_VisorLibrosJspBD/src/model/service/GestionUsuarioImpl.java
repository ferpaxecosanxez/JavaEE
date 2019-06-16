package model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import model.bean.Usuario;


/**
 * Clase que actúa como Servicio y DAO de la entidad Usuario.
 * <p>
 * Actúa como servicio porque realiza la conexción a la BD y con dicho objeto
 * realiza acciones CRUD sobre la entidad de BD para gestionar registros.
 * 
 * @author fips
 *
 */
class GestionUsuarioImpl implements GestionUsuario {
	private StringBuffer url = new StringBuffer();
	private String user = "root";
	private String pwd = "admin";
	private String driver = "com.mysql.cj.jdbc.Driver";

	public GestionUsuarioImpl() {
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
	public Integer create(Usuario usuario) {
		String sql = "INSERT INTO usuario (usuario, pass, idCliente, ultimoAcceso) VALUES (?, ?, ?, ?)";

		// try/cath con recursos.
		try (Connection cn = DriverManager.getConnection(url.toString(), user, pwd)) {
			// Preparar query.
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setString(1, usuario.getUsuario());
			ps.setString(2, usuario.getPass());
			ps.setInt(3, usuario.getIdCliente().intValue());
			ps.setTimestamp(4, Timestamp.valueOf(usuario.getUltimoAcceso()));

			// Ejecutar query (Leer documentación de método).
			return ps.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	@Override
	public Usuario get(Integer id) {
		Usuario result = null;
		String sql = "SELECT * FROM usuario WHERE id = ?";

		// try/cath con recursos.
		try (Connection cn = DriverManager.getConnection(url.toString(), user, pwd);) {
			// Preparar query.
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setInt(1, id.intValue());

			// Ejecutar query y obtener resultados.
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(new Integer(rs.getInt("id")));
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setPass(rs.getString("pass"));
				usuario.setIdCliente(new Integer(rs.getInt("idCliente")));
				usuario.setUltimoAcceso(rs.getTimestamp("ultimoAcceso").toLocalDateTime());

				result = usuario;
			}
			rs.close();
			ps.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public Usuario get(String usuario) {
		Usuario result = null;
		String sql = "SELECT * FROM usuario WHERE usuario = ?";

		// try/cath con recursos.
		try (Connection cn = DriverManager.getConnection(url.toString(), user, pwd);) {
			// Preparar query.
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setString(1, usuario);

			// Ejecutar query y obtener resultados.
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = new Usuario();
				result.setId(new Integer(rs.getInt("id")));
				result.setUsuario(rs.getString("usuario"));
				result.setPass(rs.getString("pass"));
				result.setIdCliente(new Integer(rs.getInt("idCliente")));
				result.setUltimoAcceso(rs.getTimestamp("ultimoAcceso").toLocalDateTime());
			}
			rs.close();
			ps.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return result;
	}

	@Override
	public Usuario get(String usuario, String pass) {
		Usuario result = null;
		String sql = "SELECT * FROM usuario WHERE usuario = ? AND pass = ?";

		// try/cath con recursos.
		try (Connection cn = DriverManager.getConnection(url.toString(), user, pwd);) {
			// Preparar query.
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setString(1, usuario);
			ps.setString(2, pass);

			// Ejecutar query y obtener resultados.
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = new Usuario();
				result.setId(new Integer(rs.getInt("id")));
				result.setUsuario(rs.getString("usuario"));
				result.setPass(rs.getString("pass"));
				result.setIdCliente(new Integer(rs.getInt("idCliente")));
				result.setUltimoAcceso(rs.getTimestamp("ultimoAcceso").toLocalDateTime());
			}
			rs.close();
			ps.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return result;
	}

}
