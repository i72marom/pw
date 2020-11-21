package controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Consultas extends Conexion {
	public boolean auth(String mail, String pass) {
		PreparedStatement p = null;
		ResultSet r = null;

		try {
			String consulta = "select * from contactos where email = ? and pass = ?";
			p = getConexion().prepareStatement(consulta);
			p.setString(1, mail);
			p.setString(2, pass);
			r = p.executeQuery();

			if (r.absolute(1)) return true;
		} catch (Exception e) {
			System.err.println("SQL CONSULT ERROR: " + e);
		} finally {
			try {
				if (getConexion() != null) getConexion().close();
				if (p != null) p.close();
				if (r != null) r.close();
			} catch (Exception e) {
				System.err.println("NULL ERROR: " + e);
			}
		}

		return false;
	}

	public boolean registrar(
		String nombre,
		String primer_apellido,
		String segundo_apellido,
		String mail,
		String f_nacimiento,
		String pass
	) {
		PreparedStatement p = null;

		try {
			String consulta = "insert into contactos (nombre, primer_apellido, segundo_apellido, email, fecha_nacimiento, pass) values (?, ?, ?, ?, ?, ?)";
			p = getConexion().prepareStatement(consulta);
			
			p.setString(1, nombre);
			p.setString(2, primer_apellido);
			p.setString(3, segundo_apellido);
			p.setString(4, mail);
			p.setString(5, f_nacimiento);
			p.setString(6, pass);
			
			if (p.executeUpdate() == 1) return true;
		} catch (Exception e) {
			System.err.println("SQL CONSULT ERROR: " + e);
		} finally {
			try {
				if (getConexion() != null) getConexion().close();
				if (p != null) p.close();
			} catch (Exception e) {
				System.err.println("NULL ERROR: " + e);
			}
		}

		return false;
	}
}