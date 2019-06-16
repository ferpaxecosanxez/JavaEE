package model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.bean.Libro;

class GestionLibroImpl implements GestionLibro {
	private StringBuffer url = new StringBuffer();
	private String user = "root";
	private String pwd = "admin";
	private String driver = "com.mysql.cj.jdbc.Driver";

	public GestionLibroImpl() {
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
	public List<Libro> getAll() {
		List<Libro> result = new ArrayList<>();
		String sql = "SELECT * FROM libro";

		try (Connection cn = DriverManager.getConnection(url.toString(), user, pwd);) {
			// No tenemos que preparar query.
			Statement st = cn.createStatement();

			// Ejecutar query y obtener resultados.
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Libro libro = new Libro();
				libro.setIsbn(new Integer(rs.getInt("isbn")));
				libro.setTitulo(rs.getString("titulo"));
				libro.setAutor(rs.getString("autor"));
				libro.setPrecio(rs.getBigDecimal("precio"));
				libro.setPaginas(new Integer(rs.getInt("paginas")));
				libro.setIdTema(new Integer(rs.getInt("idTema")));

				result.add(libro);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return result;
	}

	@Override
	public List<Libro> getAll(Integer idTema) {
		List<Libro> result = new ArrayList<>();
		String sql = "SELECT * FROM libro WHERE idTema = ?";
		
		try (Connection cn = DriverManager.getConnection(url.toString(), user, pwd);) {
			// Preparar query.
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setInt(1, idTema.intValue());

			// Ejecutar query y obtener resultados.
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Libro libro = new Libro();
				libro.setIsbn(new Integer(rs.getInt("isbn")));
				libro.setTitulo(rs.getString("titulo"));
				libro.setAutor(rs.getString("autor"));
				libro.setPrecio(rs.getBigDecimal("precio"));
				libro.setPaginas(new Integer(rs.getInt("paginas")));
				libro.setIdTema(new Integer(rs.getInt("idTema")));

				result.add(libro);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return result;
	}
}
