package es.uco.pw.data.mysqldao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

import com.mysql.jdbc.Statement;

import es.uco.pw.business.anuncio.Anuncio;
import es.uco.pw.business.anuncio.AnuncioFlash;
import es.uco.pw.business.anuncio.AnuncioGeneral;
import es.uco.pw.business.anuncio.AnuncioIndividualizado;
import es.uco.pw.business.anuncio.AnuncioTematico;
import es.uco.pw.business.anuncio.Generador;
import es.uco.pw.business.contacto.Contacto;
import es.uco.pw.business.tipos.Estado;
import es.uco.pw.business.tipos.Tipo;
import es.uco.pw.data.dao.AnuncioDAO;
import es.uco.pw.data.dao.DAOException;

public class MySQLAnuncioDAO implements AnuncioDAO{

	
	private Connection conn;
	
	Generador anuncio = new Generador();
	
	Properties prop = new Properties();
	
	public MySQLAnuncioDAO(Connection conn)
	{
		this.conn = conn;
		cargarStatements();
	}
	
	
	private void cargarStatements()
	{
		InputStream input = null;
		

		try {
			//D:\\Users\\Javi\\eclipse-workspace\\practica1\\bin\\configuracion.properties
			String path = "config" + File.separator +  "sql.properties";
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
	public void insertar(Anuncio a) throws DAOException {
		PreparedStatement stat = null;
		ResultSet rs = null;
	
		final String insertQuery = prop.getProperty("insertarAnuncio");
		final String insertTematicoQuery = prop.getProperty("insertarTematico");
		
		try {
			
			
			stat = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
			
			
			
			stat.setLong(1, a.getAutor().getId());
			stat.setString(2, a.getTitulo());
			stat.setString(3, a.getCuerpo());
			stat.setDate(4, new Date(a.getFecha().getTime()));
			stat.setString(5, a.getEstado().name());
			stat.setString(6, a.getTipo().name());
			
			
			
			//System.out.println(stat);
			
			if(stat.executeUpdate() == 0)
			{
				throw new DAOException("Error en SQL1.");
			}
			rs = stat.getGeneratedKeys();
			if(rs.next())
			{
				a.setId(rs.getLong(1));
				if(a.getTipo() == Tipo.flash)
				{
					AnuncioFlash b = (AnuncioFlash) a;
					
					stat = conn.prepareStatement(prop.getProperty("insertarFlash"), Statement.RETURN_GENERATED_KEYS);
					
					stat.setLong(1, a.getId());
					
					stat.setDate(2, java.sql.Date.valueOf(b.getFechaInicio()));
					
					stat.setDate(3, java.sql.Date.valueOf(b.getFechaFin()));
									
					
					if(stat.executeUpdate() == 0)
					{
						throw new DAOException("Error en SQL1.");
					}	
					
				}
				if(a.getTipo() == Tipo.general)
				{
					
				}
				if(a.getTipo() == Tipo.individualizado)
				{
					AnuncioIndividualizado b = (AnuncioIndividualizado) a;
					
					stat = conn.prepareStatement(prop.getProperty("insertarIndividualizado"), Statement.RETURN_GENERATED_KEYS);
					
					stat.setLong(1, a.getId());
					
					if(stat.executeUpdate() == 0)
					{
						throw new DAOException("Error en SQL1.");
					}	
					
					
					for(int i = 0;i<b.getDestinatarios().size();i++)
					{
						stat = conn.prepareStatement(prop.getProperty("insertarDestinatario"));
						stat.setLong(1, b.getDestinatarios().get(i));
						stat.setLong(2, a.getId());
						if(stat.executeUpdate() == 0)
						{
							throw new DAOException("Error en SQL1.");
						}	
						
						
					}
					
					

				}
				if(a.getTipo() == Tipo.tematico)
				{
					AnuncioTematico b = (AnuncioTematico) a;
					stat = conn.prepareStatement(insertTematicoQuery);
					String tags = "";
					ArrayList<String> temasAnuncio = new ArrayList<String>();
					temasAnuncio = b.getTemas();
					for(int i = 0;i<temasAnuncio.size();i++)
					{
						tags+=temasAnuncio.get(i);
						if(i != temasAnuncio.size()-1)
						{
							tags+=",";
						}
					}
					
					stat.setLong(1, a.getId());
					stat.setString(2, tags);
					
					if(stat.executeUpdate() == 0)
					{
						throw new DAOException("Error en SQL1.");
					}
					
				}
				
				
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
	public void borrar(Anuncio a) throws DAOException {
		PreparedStatement stat = null;
		final String borrarQuery = prop.getProperty("borrarAnuncio");
		final String borrarTematicoQuery = prop.getProperty("borrarTematico");
		try {
			
			Anuncio anuncioABorrar = obtener(a.getId());
			

			
			stat = conn.prepareStatement(borrarQuery);
			stat.setLong(1, a.getId());
			

			
			if(stat.executeUpdate() == 0)
			{
				throw new DAOException("Error en SQL.");
			}
			
			if(anuncioABorrar.getTipo() == Tipo.tematico)
			{
				stat = conn.prepareStatement(borrarTematicoQuery);
				stat.setLong(1, a.getId());
				if(stat.executeUpdate() == 0)
				{
					throw new DAOException("Error en SQL.");
				}
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
	public void Modificar(Anuncio a) throws DAOException{
		PreparedStatement stat = null;
		final String modificarQuery = prop.getProperty("modificarAnuncio");
		try {
			
			
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			
			stat = conn.prepareStatement(modificarQuery);
			stat.setString(1, a.getTitulo());
			stat.setString(2, a.getCuerpo());
			stat.setDate(3, sqlDate);
			stat.setString(4, a.getEstado().name());
			stat.setString(5, a.getTipo().name());
			
			stat.setLong(6, a.getId());
			
			if(stat.executeUpdate() == 0)
			{
				throw new DAOException("Error en SQL.");
			}
			
			if(a.getTipo() == Tipo.tematico)
			{
				AnuncioTematico b = (AnuncioTematico) a;
				ArrayList<String> tags = b.getTemas();
				modificarTags(a.getId(), tags);
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
	public Anuncio obtener(Long id) throws DAOException{
		PreparedStatement stat = null;
		ResultSet rs = null;
		
		final String obtenerAnuncioQuery = prop.getProperty("obtenerAnuncio");
		Anuncio a = null;
		try {
			stat = conn.prepareStatement(obtenerAnuncioQuery);
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

	public ArrayList<Anuncio> obtenerPorIdAutor(Long idAutor) throws DAOException{
		
		PreparedStatement stat = null;
		ResultSet rs = null;
		
		final String obtenerAnunciosPorIdAutorQuery = prop.getProperty("obtenerAnunciosPorIdAutor");
		ArrayList<Anuncio> a = new ArrayList<Anuncio>();
		try {
			stat = conn.prepareStatement(obtenerAnunciosPorIdAutorQuery);
			
			stat.setLong(1, idAutor);
			
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
	
	@Override
	public ArrayList<Anuncio> obtenerTodos() throws DAOException {
		PreparedStatement stat = null;
		ResultSet rs = null;
		
		final String obtenerAllAnunciosQuery = prop.getProperty("obtenerAllAnuncios");
		ArrayList<Anuncio> a = new ArrayList<Anuncio>();
		try {
			stat = conn.prepareStatement(obtenerAllAnunciosQuery);
			
			
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

	private Anuncio convertir(ResultSet a) throws SQLException, DAOException
	{
		
		
		Long idAutor = a.getLong("id_autor_fk");
		String titulo = a.getString("titulo");
		String cuerpo = a.getString("cuerpo");
		Date fecha = a.getDate("fecha");
		Estado estado_ = null;
		String estado = a.getString("estado");
		if(estado.equals("publicado"))
			estado_ = Estado.publicado;
		if(estado.equals("en_espera"))
			estado_ = Estado.en_espera;
		if(estado.equals("archivado"))
			estado_ = Estado.archivado;
		if(estado.equals("editado"))
			estado_ = Estado.editado;
		
		Tipo tipo_ = null;
		String tipo = a.getString("tipo");
		if(tipo.equals("general"))
			tipo_ = Tipo.general;
		if(tipo.equals("individualizado"))
			tipo_ = Tipo.individualizado;
		if(tipo.equals("tematico"))
			tipo_ = Tipo.tematico;
		if(tipo.equals("flash"))
			tipo_ = Tipo.flash;
		

		Long idAnuncio = a.getLong("id_anuncio");
		
		
		MySQLContactoDAO daoContacto = new MySQLContactoDAO(conn);
		Anuncio anuncioDevolver = null;
		
		Contacto autor = daoContacto.obtener(idAutor);
		
		
		if(tipo_ == Tipo.tematico)
		{
			anuncioDevolver = devolverTematico(idAutor, titulo, cuerpo, fecha, estado_, tipo_, idAnuncio, autor);
		}
		else if(tipo_ == Tipo.individualizado)
		{
			anuncioDevolver = devolverIndividualizado(idAutor, titulo, cuerpo, fecha, estado_, tipo_, idAnuncio, autor);
		}
		else if(tipo_ == Tipo.general)
		{
			anuncioDevolver = devolverGeneral(idAutor, titulo, cuerpo, fecha, estado_, tipo_, idAnuncio, autor);
		}
		else if(tipo_ == Tipo.flash)
		{
			anuncioDevolver = devolverFlash(idAutor, titulo, cuerpo, fecha, estado_, tipo_, idAnuncio, autor);
		}
		
		
		
		return anuncioDevolver;
	}
	
	private AnuncioTematico devolverTematico(Long idAutor, String titulo, String cuerpo, Date fecha, Estado estado, Tipo tipo, Long idAnuncio, Contacto autor) throws DAOException
	{
		AnuncioTematico a = (AnuncioTematico) anuncio.creaAnuncioTematico();
		
		a.setTitulo(titulo);
		a.setIdAutor(idAutor);
		a.setCuerpo(cuerpo);
		a.setEstado(estado);
		a.setAutor(autor);
		a.setFecha(fecha);
		a.setTipo(tipo);
		a.setId(idAnuncio);
		
		
		
		ArrayList<String> tags = obtenerTags(idAnuncio);
		a.setTemas(tags);
		
		
		
		return a;
	}
	private AnuncioGeneral devolverGeneral(Long idAutor, String titulo, String cuerpo, Date fecha, Estado estado, Tipo tipo, Long idAnuncio, Contacto autor) throws DAOException
	{
		AnuncioGeneral a = (AnuncioGeneral) anuncio.creaAnuncioGeneral();
		
		a.setTitulo(titulo);
		a.setIdAutor(idAutor);
		a.setCuerpo(cuerpo);
		a.setEstado(estado);
		a.setAutor(autor);
		a.setFecha(fecha);
		a.setTipo(tipo);
		a.setId(idAnuncio);
		
		return a;
	}
	private AnuncioIndividualizado devolverIndividualizado(Long idAutor, String titulo, String cuerpo, Date fecha, Estado estado, Tipo tipo, Long idAnuncio, Contacto autor) throws DAOException
	{
		AnuncioIndividualizado a = (AnuncioIndividualizado) anuncio.creaAnuncioIndividualizado();
		a.setTitulo(titulo);
		a.setIdAutor(idAutor);
		a.setCuerpo(cuerpo);
		a.setEstado(estado);
		a.setAutor(autor);
		a.setFecha(fecha);
		a.setTipo(tipo);
		a.setId(idAnuncio);
		
		
		ArrayList<Long> idDestinatarios = obtenerDestinatarios(idAnuncio);
		
		a.setDestinatarios(idDestinatarios);
		
		
		return a;
	}
	private AnuncioFlash devolverFlash(Long idAutor, String titulo, String cuerpo, Date fecha, Estado estado, Tipo tipo, Long idAnuncio, Contacto autor) throws DAOException
	{
		AnuncioFlash a = (AnuncioFlash) anuncio.creaAnuncioFlash();
		
		a.setTitulo(titulo);
		a.setIdAutor(idAutor);
		a.setCuerpo(cuerpo);
		a.setEstado(estado);
		a.setAutor(autor);
		a.setFecha(fecha);
		a.setTipo(tipo);
		a.setId(idAnuncio);
		
		
		LocalDate fechaFin = devolverFechaFin(idAnuncio);
		LocalDate fechaInicio = devolverFechaInicio(idAnuncio);
		
		a.setFechaInicio(fechaInicio);
		a.setFechaFin(fechaFin);
		
		
		return a;
	}
	
	private LocalDate devolverFechaInicio(Long idAnuncio) throws DAOException
	{
		PreparedStatement stat = null;
		ResultSet rs = null;
		LocalDate fecha;
		final String obtenerFechaFlashQuery = prop.getProperty("obtenerFechaFlash");
		
		try {
			stat = conn.prepareStatement(obtenerFechaFlashQuery);
			stat.setLong(1, idAnuncio);
			
			rs = stat.executeQuery();
			
			if(rs.next())
			{
				fecha = rs.getDate("fecha_inicio").toLocalDate();
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
		return fecha;
	}
	
	private LocalDate devolverFechaFin(Long idAnuncio) throws DAOException
	{
		PreparedStatement stat = null;
		ResultSet rs = null;
		LocalDate fecha;
		final String obtenerFechaFlashQuery = prop.getProperty("obtenerFechaFlash");
		
		try {
			stat = conn.prepareStatement(obtenerFechaFlashQuery);
			stat.setLong(1, idAnuncio);
			
			rs = stat.executeQuery();
			
			if(rs.next())
			{
				fecha = rs.getDate("fecha_fin").toLocalDate();
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
		return fecha;
	}
	
	private ArrayList<String> obtenerTags(Long idAnuncio) throws DAOException
	{
		PreparedStatement stat = null;
		ResultSet rs = null;
		ArrayList<String> listaTags = new ArrayList<String>();
		final String obtenerAnuncioQuery = prop.getProperty("obtenerTags");
		
		try {
			stat = conn.prepareStatement(obtenerAnuncioQuery);
			stat.setLong(1, idAnuncio);
			
			rs = stat.executeQuery();
			
			if(rs.next())
			{
				String tags = rs.getString("tags");
				
				
				
				String[] tags_ = tags.split(",");
				for(int i = 0;i<tags_.length;i++)
					listaTags.add(tags_[i]);
				
				
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
		return listaTags;
		
	}
	private ArrayList<Long> obtenerDestinatarios(Long idAnuncio) throws DAOException
	{
		PreparedStatement stat = null;
		ResultSet rs = null;
		ArrayList<Long> listaDestinatarios = new ArrayList<Long>();
		final String obtenerDestinatariosQuery = prop.getProperty("obtenerDestinatarios");
		
		try {
			stat = conn.prepareStatement(obtenerDestinatariosQuery);
			stat.setLong(1, idAnuncio);
			
			rs = stat.executeQuery();
			
			while(rs.next())
			{
				Long idDestinatario = rs.getLong("id_contacto");
				listaDestinatarios.add(idDestinatario);
				

				
				
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
		return listaDestinatarios;
	}
	public void agregarDestinatario(Long idDestinatario, Long idAnuncio) throws DAOException
	{
		PreparedStatement stat = null;
	
		final String insertNuevoDestinatarioQuery = prop.getProperty("insertarDestinatario");
		
		try {
			
			
			stat = conn.prepareStatement(insertNuevoDestinatarioQuery);
			
			stat.setLong(1, idDestinatario);
			stat.setLong(2, idAnuncio);
			
			if(stat.executeUpdate() == 0)
			{
				throw new DAOException("Error en SQL1.");
			}
		}catch(SQLException ex)
		{
			throw new DAOException(ex.getMessage());
		}finally{
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
	public void quitarDestinatario(Long idDestinatario, Long idAnuncio) throws DAOException
	{
		PreparedStatement stat = null;
		final String borrarDestinatarioQuery = prop.getProperty("borrarDestinatario");
		try {
			

			
			stat = conn.prepareStatement(borrarDestinatarioQuery);
			stat.setLong(1, idDestinatario);
			stat.setLong(2, idAnuncio);
			

			
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
	
	private void modificarTags(Long idAnuncio, ArrayList<String> tags_) throws DAOException
	{
		PreparedStatement stat = null;
		final String modificarTagsQuery = prop.getProperty("modificarTematico");
		try {
			
			String tags= "";
			for(int i = 0;i<tags_.size();i++)
			{
				tags+=tags_.get(i);
				if(i != tags_.size()-1)
				{
					tags+=",";
				}
			}
			
			
			stat = conn.prepareStatement(modificarTagsQuery);
			stat.setString(1, tags);
			stat.setLong(2, idAnuncio);
			

			
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

	public void updateFechas(LocalDate fechaInicio, LocalDate fechaFin, Long idAnuncio) throws DAOException
	{
		PreparedStatement stat = null;
		
		final String modificarFechasQuery = prop.getProperty("modificarFechas");
		
		try {
			
			
			stat = conn.prepareStatement(modificarFechasQuery);
			
			stat.setDate(1, java.sql.Date.valueOf(fechaInicio));
			stat.setDate(2, java.sql.Date.valueOf(fechaFin));
			stat.setLong(3, idAnuncio);
			
			if(stat.executeUpdate() == 0)
			{
				throw new DAOException("Error en SQL1.");
			}
		}catch(SQLException ex)
		{
			throw new DAOException(ex.getMessage());
		}finally{
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

	
}
