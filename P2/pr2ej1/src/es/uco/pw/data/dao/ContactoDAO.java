package es.uco.pw.data.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import es.uco.pw.business.contacto.Contacto;

public interface ContactoDAO extends DAO<Contacto, Long>{
	
	Contacto obtenerPorEmail(String email) throws DAOException;
	
	
}
