package model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.bean.Tema;

/**
 * Clase que actúa como Servicio y DAO de la entidad Tema.
 * <p>
 * Actúa como servicio porque realiza la conexción a la BD y con dicho objeto
 * realiza acciones CRUD sobre la entidad de BD para gestionar registros.
 * 
 * @author fips
 *
 */
class GestionTemaImpl implements GestionTema {
	private StringBuffer url = new StringBuffer();
	private String user = "root";
	private String pwd = "admin";
	private String driver = "com.mysql.cj.jdbc.Driver";

	public GestionTemaImpl() {
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
	public List<Tema> getAll() {
		List<Tema> result = new ArrayList<>();
		String sql = "SELECT * FROM tema";

		try (Connection cn = DriverManager.getConnection(url.toString(), user, pwd);) {
			// No tenemos que preparar query.
			Statement st = cn.createStatement();

			// Ejecutar query y obtener resultados.
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Tema tema = new Tema();
				tema.setId(new Integer(rs.getInt("id")));
				tema.setTema(rs.getString("tema"));

				result.add(tema);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return result;
	}
}
