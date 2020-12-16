package es.uco.pw.data.mysqldao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Properties;

import com.mysql.jdbc.Statement;

import es.uco.pw.business.contacto.Contacto;
import es.uco.pw.data.dao.ContactoDAO;
import es.uco.pw.data.dao.DAOException;
/**
 * Clase que implementa la interfaz ContactoDAO para hacer las consultas a la BBDD relacionadas con la clase Anuncio en MySQL.
 * @author Javi y Manu
 *
 */
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
		java.io.InputStream IO = null;

		try {
		    
			IO = getClass().getClassLoader().getResourceAsStream("sql.properties");
		    // load a properties file
		    prop.load(IO);
		    
			

		    // load a properties file

		    
		    
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
	/**
	 * Función para insertar un contacto en la BBDD
	 */
	public void insertar(Contacto a) throws DAOException{
		PreparedStatement stat = null;
		ResultSet rs = null;
	
		final String insertQuery = prop.getProperty("insertarUser");
		
		try {
			
			
			stat = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
			
					
			stat.setString(1, a.getNombre());
			stat.setString(2, a.getApellido1());
			stat.setString(3, a.getApellido2());
			stat.setString(4, a.getEmail());
			stat.setInt(5, a.getEdad());
			stat.setDate(6, java.sql.Date.valueOf(a.getFechaCumpleanos()));
			stat.setString(7, a.getPassword());
			stat.setString(8, "amor,cultura");
			
			
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
	/**
	 * Función para borrar un contacto de la BBDD
	 */
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

	private Contacto convertir(ResultSet a) throws SQLException
	{
		String nombre = a.getString("nombre");
		String apellido1 = a.getString("primer_apellido");
		String apellido2 = a.getString("segundo_apellido");
		String email = a.getString("email");
		int edad = a.getInt("edad");
		LocalDate fechaCumpleanos = a.getDate("fecha_nacimiento").toLocalDate();
		String password = a.getString("pass");
		String tagsleidos = a.getString("tags");
		
		String[] tags_ = tagsleidos.split(",");
		
		ArrayList<String> tags = new ArrayList<String>();
		
		for(int i = 0;i<tags_.length;i++)
		{
			tags.add(tags_[i]);
		}
		
		
		Contacto contacto = new Contacto(nombre, apellido1, apellido2, edad, email, fechaCumpleanos, tags, password);
		contacto.setId(a.getLong("id_contacto"));
		return contacto;
	}
	
	@Override
	/**
	 * Función para modificarun contacto de la BBDD
	 */
	public void Modificar(Contacto a) throws DAOException {
		PreparedStatement stat = null;
		final String modificarQuery = prop.getProperty("modificarUser");
		try {
			
			
			stat = conn.prepareStatement(modificarQuery);
			stat.setString(1, a.getNombre());
			stat.setString(2, a.getApellido1());
			stat.setString(3, a.getApellido2());
			stat.setString(4, a.getEmail());
			stat.setInt(5, a.getEdad());
			stat.setDate(6, new Date(a.getDiaCumpleanos(), a.getMesCumpleanos(), a.getAnyoCumpleanos()));
			stat.setString(7, a.getPassword());
			
			
			
			ArrayList<String> tags = a.getTags();
			String tags_ = "";
			for(int i = 0;i<tags.size();i++)
			{
				tags_+=tags.get(i);
				if(i!=tags.size()-1)
				{
					tags_+=",";
				}
			}
			
			
			System.out.println(tags_);
			
			stat.setString(8, tags_);
			stat.setLong(9, a.getId());
			
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
	/**
	 * Función para obtener un contacto a través de su ID
	 */
	public Contacto obtener(Long id) throws DAOException {
		PreparedStatement stat = null;
		ResultSet rs = null;
		
		final String obtenerUserString = prop.getProperty("obtenerUser");
		Contacto a = null;
		try {
			stat = conn.prepareStatement(obtenerUserString);
			stat.setLong(1, id);
			
			rs = stat.executeQuery();
			
			if(rs.next())
			{
				a = convertir(rs);
			}
			else
			{
				throw new DAOException("No se ha encontrado ese registro");
			}
		}catch(SQLException ex)
		{
			throw new DAOException("Error en SQL", ex);
		}
		finally {
			if(rs != null)
			{
				try {
					rs.close();
				}catch(SQLException ex)
				{
					throw new DAOException("Error en SQL", ex);
				}
			}
			if(stat != null)
			{
				try {
					stat.close();
				}catch (SQLException ex)
				{
					throw new DAOException("Error en SQL", ex);
				}
			}
		}
		return a;
	}
	
	/**
	 * Función para obtener un contacto a través de su email
	 */
	public Contacto obtenerPorEmail(String email) throws DAOException
	{
		PreparedStatement stat = null;
		ResultSet rs = null;
		
		final String obtenerUserPorEmailString = prop.getProperty("obtenerUserPorEmail");
		Contacto a = null;
		try {
			stat = conn.prepareStatement(obtenerUserPorEmailString);
			stat.setString(1, email);
			
			rs = stat.executeQuery();
			
			if(rs.next())
			{
				a = convertir(rs);
			}
			else
			{
				return a;
			}
		}catch(SQLException ex)
		{
			throw new DAOException("Error en SQL", ex);
		}
		finally {
			if(rs != null)
			{
				try {
					rs.close();
				}catch(SQLException ex)
				{
					throw new DAOException("Error en SQL", ex);
				}
			}
			if(stat != null)
			{
				try {
					stat.close();
				}catch (SQLException ex)
				{
					throw new DAOException("Error en SQL", ex);
				}
			}
		}
		return a;
	}
	
	

	@Override
	/**
	 * Función para obtener todos los contactos de la BBDD
	 */
	public ArrayList<Contacto> obtenerTodos() throws DAOException{
		PreparedStatement stat = null;
		ResultSet rs = null;
		
		final String obtenerAllUserString = prop.getProperty("obtenerAllUsers");
		ArrayList<Contacto> a = new ArrayList<Contacto>();
		try {
			stat = conn.prepareStatement(obtenerAllUserString);
			
			
			rs = stat.executeQuery();
			
			while(rs.next())
			{
				a.add(convertir(rs));
			}
		}catch(SQLException ex)
		{
			throw new DAOException("Error en SQL", ex);
		}
		finally {
			if(rs != null)
			{
				try {
					rs.close();
				}catch(SQLException ex)
				{
					throw new DAOException("Error en SQL", ex);
				}
			}
			if(stat != null)
			{
				try {
					stat.close();
				}catch (SQLException ex)
				{
					throw new DAOException("Error en SQL", ex);
				}
			}
		}
		return a;
	}

	public Contacto validarLogin(String mail, String password) throws DAOException {
		PreparedStatement stat = null;
		ResultSet rs = null;
		
		final String validarString = prop.getProperty("validar");
		Contacto a = null;
		try {
			stat = conn.prepareStatement(validarString);
			stat.setString(1, mail);
			stat.setString(2, password);
			
			
			
			rs = stat.executeQuery();
			
			
			if(rs.next())
			{
				a = convertir(rs);
			}
		}catch(SQLException ex)
		{
			throw new DAOException("Error en SQL", ex);
		}
		finally {
			if(rs != null)
			{
				try {
					rs.close();
				}catch(SQLException ex)
				{
					throw new DAOException("Error en SQL", ex);
				}
			}
			if(stat != null)
			{
				try {
					stat.close();
				}catch (SQLException ex)
				{
					throw new DAOException("Error en SQL", ex);
				}
			}
		}
		return a;
	}
	
	
}
