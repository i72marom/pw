package es.uco.pw.business.anuncio;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import es.uco.pw.business.contacto.Contacto;
import es.uco.pw.business.contacto.GestorContactos;
import es.uco.pw.business.tipos.Estado;
import es.uco.pw.business.tipos.Tipo;
import es.uco.pw.data.dao.DAOException;
import es.uco.pw.data.mysqldao.MySQLDAOManager;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Clase que gestiona los Anuncios
 * @author Javi y Manu
 *
 */
public class GestorAnuncios {
	//private ArrayList<Anuncio> tablon_ = new ArrayList();
	
	
	private static GestorAnuncios instance = null;
	
	String[] tags_disponibles_;

	
	Scanner leerStrings = new Scanner(System.in);
	Scanner leerInts = new Scanner(System.in);
	
	Generador anuncio = new Generador();
	 
	private GestorAnuncios() {
	}
	
	public static GestorAnuncios getInstance()
	{
		if(instance == null) {
			instance = new GestorAnuncios();
		}
		return instance;
			
	}
	
	////////////////////////////////////////////////FUNCIONES DE CARGA Y GUARDA DEL TABLON////////////////////////////////////////////////////////////

	
	public boolean coincidenTemas(ArrayList<String> temasAnuncio, ArrayList<String> temasUser)
	{
		
		boolean resultado = false;
		
		for(int i = 0;i<temasAnuncio.size() && !resultado;i++)
		{
			for(int o = 0;o<temasUser.size() && !resultado;o++)
			{
				if(temasAnuncio.get(i).equals(temasUser.get(o)))
				{
					resultado = true;
				}
			}
		}
		
		return resultado;
		
	}
	
	public String fechaActual()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.now().format(formatter);
	}
	
	public int getDiaActual()
	{
		return 0;
	}
	public int getMesActual()
	{
		return LocalDate.now().getMonthValue();
	}
	public int getAnyoActual()
	{
		return LocalDate.now().getYear();
	}
	
	public boolean esDestinatario(ArrayList<Long> destinatarios, Long idUser)
	{
		boolean resultado = false;
		
		for(int i = 0;i<destinatarios.size() && !resultado;i++)
		{
			if(destinatarios.get(i) == idUser)
				resultado = true;
		}
		
		return resultado;
	}
	
	public ArrayList<Anuncio> anadirAnunciosAMostrar(ArrayList<Anuncio> anuncios, Long idUser, String buscar, String by) throws ClassNotFoundException, SQLException, DAOException
	{
		ArrayList<Anuncio> anunciosAMostrar = new ArrayList<Anuncio>();
		
		for(int i =  0;i<anuncios.size();i++)
		{
			
			Anuncio a = anuncios.get(i);
			
			if(a.getEstado() == Estado.publicado)
			{
				if(anuncios.get(i).getTipo() == Tipo.flash)
				{
					AnuncioFlash b = (AnuncioFlash) a;
					
					LocalDate fechaActual = LocalDate.now();
					LocalDate fechaInicioAnuncio = b.getFechaInicio();
					LocalDate fechaFinAnuncio = b.getFechaFin();
					
					
					
					if(!fechaFinAnuncio.isBefore(fechaActual) && !fechaInicioAnuncio.isAfter(fechaActual))
					{
						
						if(buscar != null && by != null)
						{
							if(by.equals("autor"))
							{
								String nombreAutor = a.getAutor().getNombre() + " " + a.getAutor().getApellidos();
								if(nombreAutor.toLowerCase().contains(buscar.toLowerCase()))
								{
									anunciosAMostrar.add(a);
								}
							}
							else if(by.equals("titulo"))
							{
								if(a.getTitulo().toLowerCase().contains(buscar.toLowerCase()))
								{
									anunciosAMostrar.add(a);
								}
							}
							else if(by.equals("fecha"))
							{
								if(a.getFecha().toString().contains(buscar))
								{
									anunciosAMostrar.add(a);
								}
							}
						}
						
						
					}
					
					
					
					
				}
				else if(anuncios.get(i).getTipo() == Tipo.tematico)
				{
					AnuncioTematico b = (AnuncioTematico) a;
					
					MySQLDAOManager manager = MySQLDAOManager.getInstance();
					
					
					
					
					ArrayList<String> temasAnuncio = b.getTemas();
					
					Contacto userLogged = manager.getContactoDAO().obtener(idUser);
					
					ArrayList<String> temasUserLogged = userLogged.getTags();
					
					
					
					if(coincidenTemas(temasAnuncio, temasUserLogged))
					{
						if(buscar != null && by != null)
						{
							if(by.equals("autor"))
							{
								String nombreAutor = a.getAutor().getNombre() + " " + a.getAutor().getApellidos();
								if(nombreAutor.toLowerCase().contains(buscar.toLowerCase()))
								{
									anunciosAMostrar.add(a);
								}
							}
							else if(by.equals("titulo"))
							{
								if(a.getTitulo().toLowerCase().contains(buscar.toLowerCase()))
								{
									anunciosAMostrar.add(a);
								}
							}
							else if(by.equals("fecha"))
							{
								if(a.getFecha().toString().contains(buscar))
								{
									anunciosAMostrar.add(a);
								}
							}
						}
					}
					
					
					
				}
				else if(anuncios.get(i).getTipo() == Tipo.individualizado)
				{
					AnuncioIndividualizado b = (AnuncioIndividualizado) a;
					ArrayList<Long> destinatarios = b.getDestinatarios();
					
					
					if(esDestinatario(destinatarios, idUser))
					{
						if(buscar != null && by != null)
						{
							
							if(by.equals("autor"))
							{
								String nombreAutor = a.getAutor().getNombre() + " " + a.getAutor().getApellidos();
								if(nombreAutor.toLowerCase().contains(buscar.toLowerCase()))
								{
									anunciosAMostrar.add(a);
								}
							}
							else if(by.equals("titulo"))
							{
								if(a.getTitulo().toLowerCase().contains(buscar.toLowerCase()))
								{
									anunciosAMostrar.add(a);
								}
							}
							else if(by.equals("fecha"))
							{
								if(a.getFecha().toString().contains(buscar))
								{
									anunciosAMostrar.add(a);
								}
							}
						}
					}
					
					
				}
				else
				{
					if(buscar != null && by != null)
					{
						String nombreAutor = a.getAutor().getNombre() + " " + a.getAutor().getApellidos();
						if(by.equals("autor"))
						{
							if(nombreAutor.toLowerCase().contains(buscar.toLowerCase()))
							{
								anunciosAMostrar.add(a);
							}
						}
						else if(by.equals("titulo"))
						{
							if(a.getTitulo().toLowerCase().contains(buscar.toLowerCase()))
							{
								anunciosAMostrar.add(a);
							}
						}
						else if(by.equals("fecha"))
						{
							if(a.getFecha().toString().contains(buscar))
							{
								anunciosAMostrar.add(a);
							}
						}
					}
				}
			}
			
			
		}
		
		
		return anunciosAMostrar;

	}
	
	public ArrayList<Anuncio> filtrar(ArrayList<Anuncio> anuncios, String buscar, String by)
	{
		ArrayList<Anuncio> filtrados = new ArrayList<Anuncio>();
		for(int i = 0;i<anuncios.size();i++)
		{
			if(by.equals("titulo"))
			{
				if(anuncios.get(i).getTitulo().toLowerCase().contains(buscar.toLowerCase()))
					filtrados.add(anuncios.get(i));
			}
			else if(by.equals("fecha"))
			{
				if(anuncios.get(i).getFecha().toString().contains(buscar))
					filtrados.add(anuncios.get(i));
			}
		}
		
		return filtrados;
	}
	
	public ArrayList<Anuncio> ordenarPorFecha(ArrayList<Anuncio> anunciosAMostrar)
	{
		for(int i = 0;i<anunciosAMostrar.size();i++)
		{
			for(int o = 0;o<anunciosAMostrar.size()-i-1;o++)
			{
				if(anunciosAMostrar.get(o).getFecha().isBefore(anunciosAMostrar.get(o+1).getFecha()))
				{
					Anuncio temporal = anunciosAMostrar.get(o+1);
					anunciosAMostrar.set(o+1, anunciosAMostrar.get(o));
					anunciosAMostrar.set(o, temporal);
					
					
				}
			}
		}
		return anunciosAMostrar;
	}
	public ArrayList<Anuncio> ordenarPorAutor(ArrayList<Anuncio> anunciosAMostrar)
	{
		for(int i = 0;i<anunciosAMostrar.size();i++)
		{
			for(int o = 0;o<anunciosAMostrar.size()-i-1;o++)
			{
				if(anunciosAMostrar.get(o).getAutor().getNombre().compareToIgnoreCase(anunciosAMostrar.get(o+1).getAutor().getNombre()) < 0)
				{
					Anuncio temporal = anunciosAMostrar.get(o+1);
					anunciosAMostrar.set(o+1, anunciosAMostrar.get(o));
					anunciosAMostrar.set(o, temporal);
					
					
				}
			}
		}
		return anunciosAMostrar;
	}
	
	public ArrayList<Anuncio> ordenarPorTitulo(ArrayList<Anuncio> anunciosAMostrar)
	{
		for(int i = 0;i<anunciosAMostrar.size();i++)
		{
			for(int o = 0;o<anunciosAMostrar.size()-i-1;o++)
			{
				if(anunciosAMostrar.get(o).getTitulo().compareToIgnoreCase(anunciosAMostrar.get(o+1).getTitulo()) < 0)
				{
					Anuncio temporal = anunciosAMostrar.get(o+1);
					anunciosAMostrar.set(o+1, anunciosAMostrar.get(o));
					anunciosAMostrar.set(o, temporal);
					
					
				}
			}
		}
		return anunciosAMostrar;		
	}
	public ArrayList<Anuncio> revertir(ArrayList<Anuncio> anunciosAMostrar)
	{
		Collections.reverse(anunciosAMostrar);
		return anunciosAMostrar;
	}
	//////////////////////////////////FUNCIONES PRIVADAS///////////////////////////////////////////////////////
	
	private boolean tagValido(String tag)
	{
		boolean resultado = false;
		for(int i = 0;i<tags_disponibles_.length && !resultado;i++)
		{
			if(tag.equals(tags_disponibles_[i]))
				resultado = true;
		}
		
		return resultado;
		
	}
	
	

	

		
	
	


	
	private ArrayList<Anuncio> buscarPorFecha(MySQLDAOManager manager, LocalDate fecha) throws DAOException {
		ArrayList<Anuncio> list = new ArrayList();
		ArrayList<Anuncio> anuncios = manager.getAnuncioDAO().obtenerTodos();
		for (Anuncio a : anuncios) {
			if (a.getFecha() == fecha) list.add(a);
		}

		return list;
	}
	
	
}
