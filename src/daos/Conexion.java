package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.mysql.cj.jdbc.Driver;

public class Conexion {
	private static final String HOST = "jdbc:mysql://localhost:3306/proyectofinal";
	private static final String CONFIG = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String USER = "admin";
	private static final String PASS = "Nij2991159.";

	private static Connection connection = null;

	public static PreparedStatement createQuery(String sql) throws SQLException {
		try {
			return getConnection().prepareStatement(sql);
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
			throw new SQLException(e);
		}
	}

	private static Connection getConnection() throws ClassNotFoundException, SQLException {
		if (connection == null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(HOST + CONFIG, USER, PASS);
		}

		return connection;
	}
	
}