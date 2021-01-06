package es.uco.pw.data.dao;


/**
 * Interfaz que a�ade funcionalidad a un manager de DAOs
 * @author Javi y Manu
 *
 */
public interface DAOManager {

	AnuncioDAO getAnuncioDAO();
	
	ContactoDAO getContactoDAO();
	
}
