package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que actúa como Servicio y DAO de la entidad CLIENTE.
 * <p>
 * Actúa como servicio porque realiza la conexción a la BD y con dicho objeto
 * realiza acciones CRUD sobre una entidad de la BD realizando consultas para
 * obtener registros.
 * 
 * @author fips
 *
 */
public class GestionCliente {
	private StringBuffer url = new StringBuffer();
	private String user = "root";
	private String pwd = "admin";
	private String driver = "com.mysql.cj.jdbc.Driver";

	public GestionCliente() {
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
			
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Este método se encarga de abrir la conexión, preparar la consulta y
	 * ejecutarla, luego cerrar la conexión y devolver si las credenciales son
	 * correctar o no.
	 * 
	 * @param usuario Usuario registrado para realizar login.
	 * @param pass    Contraseña correspondiente al usuario.
	 * @return true, si las credenciales con correctas.<br>
	 *         false, en caso contrario.
	 */
	public boolean autenticar(String usuario, String pass) {
		boolean result = false;
		String sql = "SELECT * FROM usuario WHERE usuario = ? and pass = ?";

		// try/cath con recursos.
		try (Connection cn = DriverManager.getConnection(url.toString(), user, pwd)) {
			// Preparar query.
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setString(1, usuario);
			ps.setString(2, pass);

			// Ejecutar y ver resultados.
			ResultSet rs = ps.executeQuery();
			// Movemos el cursor 1 vez, si se ha realizado el movimiento del cursor,
			// significa que existe registro.
			result = rs.next();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
}
