package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.mariadb.jdbc.MariaDbDataSource;

public class Conexion {
	private String user = "pw2";
	private String pass = "root";
	private String host = "192.168.1.238";
	private String port = "3306";
	private String database = "tablon";
	private String url = "jdbc:mariadb://" + host + ":" + port + "/" + database;
	private Connection conn;

	public Conexion() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			System.err.println("CLASS ERROR: " + e);
		} catch (SQLException e) {
			System.err.println("SQL ERROR: " + e);
		}
	}

	public Connection getConexion() { return conn; }
}
