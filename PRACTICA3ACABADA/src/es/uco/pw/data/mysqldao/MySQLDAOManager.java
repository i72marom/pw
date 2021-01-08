package es.uco.pw.data.mysqldao;


import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


import es.uco.pw.data.dao.AnuncioDAO;
import es.uco.pw.data.dao.ContactoDAO;

import es.uco.pw.data.dao.DAOManager;
/**
 * Clase que sirve de Manager de los daos e implementa un singleton para obtener sólo una vez el anuncioDAO y el contactoDAO
 * @author Javi y Manu
 *
 */
public class MySQLDAOManager implements DAOManager{

	private Connection conn;
	
	private static MySQLDAOManager instance = null;
	
	private AnuncioDAO anuncio = null;
	private ContactoDAO contacto = null;
	
	
	Properties prop = new Properties();
	private String host, database, username, password;
	
	
	private MySQLDAOManager() throws SQLException, ClassNotFoundException {
		
		cargarConfiguracionBBDD();
		
		Class.forName("com.mysql.jdbc.Driver");
		
		conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database , username, password);
		
		
	}

	public static MySQLDAOManager getInstance() throws ClassNotFoundException, SQLException {
		if(instance == null) {
			instance = new MySQLDAOManager();
		}
		return instance;
	}
	
	@Override
	/**
	 * Devuelve el anuncioDAO
	 */
	public AnuncioDAO getAnuncioDAO() {
		if(anuncio == null)
		{
			anuncio = new MySQLAnuncioDAO(conn);
		}
		return anuncio;
	}

	@Override
	/**
	 * Devuelve el contactoDAO
	 */
	public ContactoDAO getContactoDAO() {
		if(contacto == null)
		{
			contacto = new MySQLContactoDAO(conn);
			
		}
		return contacto;
	}
	/**
	 * Carga las propiedades de la BBDD
	 */
	private void cargarConfiguracionBBDD()
	{
		java.io.InputStream IO = null;

		try {
			IO = getClass().getClassLoader().getResourceAsStream("configuracion.properties");

		    // load a properties file
		    prop.load(IO);
		    
		    
		    host=prop.getProperty("host");
		    database=prop.getProperty("database");
		    username=prop.getProperty("username");
		    password=prop.getProperty("password");
		    
		    System.out.println(host);

		    
		    
		} catch (IOException ex) {
		    ex.printStackTrace();
		} finally {
		    if (IO != null) {
		        try {
		            IO.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		}
		
		
	}
	

	
	
}
