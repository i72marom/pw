package es.uco.pw.data.dao;



import es.uco.pw.business.contacto.Contacto;


/**
 * Interfaz que extiende de la interfaz DAO y añade funcionalidad a un DAO sobre la clase Contacto
 * @author Javi y Manu
 *
 */
public interface ContactoDAO extends DAO<Contacto, Long>{
	
	Contacto obtenerPorEmail(String email) throws DAOException;
	public Contacto validarLogin(String mail, String password) throws DAOException;
	
}
