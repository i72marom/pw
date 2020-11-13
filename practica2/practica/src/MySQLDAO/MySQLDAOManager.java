package MySQLDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.AnuncioDAO;
import dao.ContactoDAO;
import dao.DAOManager;
import dao.DAO;

public class MySQLDAOManager implements DAOManager{

	private Connection conn;
	
	private AnuncioDAO anuncio = null;
	private ContactoDAO contacto = null;
	
	public MySQLDAOManager(String host, String username, String passwd, String dataBase) throws SQLException {
		conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + dataBase , username, passwd);
		
		
	}

	@Override
	public AnuncioDAO getAnuncioDAO() {
		if(anuncio == null)
		{
		//	anuncio = new MySQLAnuncioDAO(conn);
		}
		return anuncio;
	}

	@Override
	public ContactoDAO getContactoDAO() {
		if(contacto == null)
		{
			contacto = new MySQLContactoDAO(conn);
			
		}
		return contacto;
	}
	

	
	
}
