package es.uco.pw.data.dao;
/**
 * Clase que extiende de la clase Exception y crea una Excepci�n espec�fica para los DAOs
 * @author Javi y Manu
 *
 */
public class DAOException extends Exception{

	public DAOException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DAOException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DAOException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
