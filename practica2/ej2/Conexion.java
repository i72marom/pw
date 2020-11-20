package ej2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion() {
	private String user = "pw";
	private String pass = "pw";
	private String host = "192.168.1.238";
	private String port = "3306";
	private String database = "tablon";
	private String classname = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://" + host + ":" + port + "/" + dataBase;
	private Connection conn;

	public Conexion() {
		try {
			class.forname(classname);
			conn = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			System.err.println("ERROR: " + e);
		} catch (SQLException e) {
			System.err.println("ERROR: " + e);
		}
	}

	public Connection getConexion() { return conn; }
}
