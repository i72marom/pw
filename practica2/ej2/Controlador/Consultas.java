package Controlador;

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
}