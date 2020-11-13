package MySQLDAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Properties;

import com.mysql.jdbc.Statement;

import anuncios.Contacto;
import dao.ContactoDAO;
import dao.DAOException;

public class MySQLContactoDAO implements ContactoDAO{
	
	
	private Connection conn;
	Properties prop = new Properties();
	
	public MySQLContactoDAO(Connection conn)
	{
		this.conn = conn;
		cargarStatements();
	}
	
	
	private void cargarStatements()
	{
		prop = new Properties();
		InputStream input = null;
		

		try {
			//D:\\Users\\Javi\\eclipse-workspace\\practica1\\bin\\configuracion.properties
			String path = "src" + File.separator +  "sql.properties";
		    input = new FileInputStream(path);

		    // load a properties file
		    prop.load(input);

		    
		    
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
	
	@Override
	public void insertar(Contacto a) throws DAOException{
		PreparedStatement stat = null;
		ResultSet rs = null;
		
		final String insertar = "INSERT INTO contactos(nombre, primer_apellido, segundo_apellido, email, fecha_nacimiento, pass, tags) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			
			
			stat = conn.prepareStatement(insertar, Statement.RETURN_GENERATED_KEYS);
			
			
			
			stat.setString(1, a.getNombre());
			stat.setString(2, a.getApellido1());
			stat.setString(3, a.getApellido2());
			stat.setString(4, a.getEmail());
			stat.setDate(5, new Date(a.getDiaCumpleanos(), a.getMesCumpleanos(), a.getAnyoCumpleanos()));
			stat.setString(6, a.getPassword());
			stat.setString(7, "amor,cultura");
			
			
			System.out.println(stat);
			
			if(stat.executeUpdate() == 0)
			{
				throw new DAOException("Error en SQL1.");
			}
			rs = stat.getGeneratedKeys();
			if(rs.next())
			{
				a.setId(rs.getLong(1));
			}
			else
			{
				throw new DAOException("No puedo asignar ID");
			}
			
			
		}catch(SQLException ex)
		{
			throw new DAOException(ex.getMessage());
		}finally{
			if(rs != null)
			{
				try {
					rs.close();
				}catch(SQLException ex)
				{
					throw new DAOException("Error en SQL4");
				}
			}
			if(stat != null)
			{
				try {
					stat.close();
				}catch(SQLException ex)
				{
					throw new DAOException("Error en SQL5");
				}
				
			}
			
		}
		
		
	}

	@Override
	public void borrar(Contacto a) throws DAOException{
		PreparedStatement stat = null;
		final String insertQuery = prop.getProperty("borrarUser");
		try {
			
			
			stat = conn.prepareStatement(insertQuery);
			stat.setLong(1, a.getId());
			
			if(stat.executeUpdate() == 0)
			{
				throw new DAOException("Error en SQL.");
			}
			
			
		}catch(SQLException ex)
		{
			throw new DAOException("Error en SQL");
		}finally{
			if(stat != null)
			{
				try {
					stat.close();
				}catch(SQLException ex)
				{
					throw new DAOException("Error en SQL");
				}
				
			}
			
		}
		
	}

	@Override
	public void Modificar(Contacto a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Contacto obtener(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Contacto> obtenerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
