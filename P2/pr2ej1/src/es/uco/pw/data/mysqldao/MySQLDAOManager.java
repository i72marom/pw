package es.uco.pw.data.mysqldao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import es.uco.pw.data.dao.AnuncioDAO;
import es.uco.pw.data.dao.ContactoDAO;
import es.uco.pw.data.dao.DAO;
import es.uco.pw.data.dao.DAOManager;

public class MySQLDAOManager implements DAOManager{

	private Connection conn;
	
	private AnuncioDAO anuncio = null;
	private ContactoDAO contacto = null;
	
	
	Properties prop = new Properties();
	private String host, database, username, password;
	
	
	public MySQLDAOManager() throws SQLException {
		
		cargarConfiguracionBBDD();
		
		conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database , username, password);
		
		
	}


	
	@Override
	public AnuncioDAO getAnuncioDAO() {
		if(anuncio == null)
		{
			anuncio = new MySQLAnuncioDAO(conn);
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
	
	private void cargarConfiguracionBBDD()
	{
		
		InputStream input = null;
		

		try {
			//D:\\Users\\Javi\\eclipse-workspace\\practica1\\bin\\configuracion.properties
			String path = "config" + File.separator +  "configuracion.properties";
		    input = new FileInputStream(path);

		    // load a properties file
		    prop.load(input);
		    
		    
		    host=prop.getProperty("host");
		    database=prop.getProperty("database");
		    username=prop.getProperty("username");
		    password=prop.getProperty("password");

		    
		    
		} catch (IOException ex) {
		    ex.printStackTrace();
		} finally {
		    if (input != null) {
		        try {
		            input.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		}
		
		
	}
	

	
	
}
