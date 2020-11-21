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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;



public class GestorAnuncios {
	//private ArrayList<Anuncio> tablon_ = new ArrayList();
	
	String[] tags_disponibles_;

	
	Scanner leerStrings = new Scanner(System.in);
	Scanner leerInts = new Scanner(System.in);
	
	Generador anuncio = new Generador();
	 
	public GestorAnuncios() {
		
		
	}
	
	////////////////////////////////////////////////FUNCIONES DE CARGA Y GUARDA DEL TABLON////////////////////////////////////////////////////////////

	
	
	
	
	
	//////////////////////////////////////////////FUNCIONES PUBLICAS////////////////////////////////////////////////////////////////////
	
	public void crearAnuncio(MySQLDAOManager manager, GestorContactos gestor) throws DAOException
	{
		Contacto autor = gestor.getUserLogeado();
		
		String titulo = "", contenido = "";
		String publicar = "";
		Estado estadoAnuncio = Estado.editado;
		Date date = new Date();
		System.out.println("Que tipo de anuncio quieres crear : ");
		System.out.println("1- General");
		System.out.println("2- Flash");
		System.out.println("3- Individualizado");
		System.out.println("4- Tematico");
		System.out.print("Elige uno : ");
		int tipo = leerInts.nextInt();
		int id = 0;
		
		if(tipo < 5 && tipo > 0)
		{
			System.out.print("Titulo del anuncio : ");
			titulo = leerStrings.nextLine();
			System.out.print("Contenido : ");
			contenido = leerStrings.nextLine();
			System.out.print("¿Desea publicarlo ya? (si|no) : ");
			publicar = leerStrings.nextLine();
			if(publicar.equals("si"))
			{
				estadoAnuncio = Estado.publicado;
			}
			else if(publicar.equals("no"))
			{
				estadoAnuncio = Estado.editado;
			}
			
			
			
			
		}
		
		
		if(tipo == 1)
		{
			//pedir datos del anuncio
			AnuncioGeneral a = (AnuncioGeneral) anuncio.creaAnuncioGeneral();
			a.setCuerpo(contenido);
			a.setTitulo(titulo);
			a.setFecha(date);
			a.setAutor(autor);
			a.setTipo(Tipo.general);
			a.setEstado(estadoAnuncio);
			a.setId(Long.valueOf(id));
			System.out.println("Anuncio añadido");
			manager.getAnuncioDAO().insertar(a);
			
		}
		else if(tipo == 2)
		{
			//pedir datos del anuncio +fechas
			AnuncioFlash a = (AnuncioFlash) anuncio.creaAnuncioFlash();
			
			do {
				
				
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				System.out.println("Elige la fecha de inicio del anuncio (xx/xx/xxxx) : ");
				LocalDate startDate = LocalDate.parse(leerStrings.nextLine(), formatter);

				System.out.println("Elige la fecha de fin del anuncio (xx/xx/xxxx) : ");
				LocalDate endDate = LocalDate.parse(leerStrings.nextLine(), formatter);
				a.setFechaInicio(startDate);
				a.setFechaFin(endDate);
				
				
				
			} while( !fechasValidas(a.getDiaInicio(),a.getMesInicio(),a.getAnyoInicio(),a.getDiaFin(),a.getMesFin(),a.getAnyoFin()));
			
			a.setId(Long.valueOf(id));
			a.setCuerpo(contenido);
			a.setTitulo(titulo);
			a.setFecha(date);
			a.setAutor(autor);
			a.setTipo(Tipo.flash);
			a.setEstado(estadoAnuncio);
			
			
			manager.getAnuncioDAO().insertar(a);
			
			
		}
		else if(tipo == 3)
		{
			//pedir datos del anuncio +destinatarios
			
			AnuncioIndividualizado a = (AnuncioIndividualizado) anuncio.creaAnuncioIndividualizado();
			
			String inputString;
			
			System.out.println("Lista de usuarios : ");
			gestor.mostrarContactos(manager);
			System.out.println("Elige los indices de los contactos a los que quieres enviarlo (separados sólo por comas) : ");
			inputString = leerStrings.nextLine();
			
			String[] indicesSeleccionados = inputString.split(",");
			ArrayList<Long> destinatarios = new ArrayList<Long>();
			for(int i = 0;i<indicesSeleccionados.length;i++)
			{
				
				//System.out.println(gestor.buscarContactoPorId());
				destinatarios.add(Long.parseLong(indicesSeleccionados[i]));
			}
			
			a.setId(Long.valueOf(id));
			a.setCuerpo(contenido);
			a.setTitulo(titulo);
			a.setFecha(date);
			a.setAutor(autor);
			a.setTipo(Tipo.individualizado);
			a.setDestinatarios(destinatarios);
			a.setEstado(estadoAnuncio);
			
			
			manager.getAnuncioDAO().insertar(a);
			System.out.println("Anuncio añadido");
			
			
			
		}
		else if(tipo == 4)
		{
			
			AnuncioTematico a = (AnuncioTematico) anuncio.creaAnuncioTematico();
			
			//pedir datos del anuncio +tema
			String tema;
			
		    System.out.print("Tags disponibles : ");
		    for(int i = 0;i<gestor.get_tag_disponibles().length;i++)
		    {
		    	System.out.print(gestor.get_tag_disponibles()[i] + " ");
		    }
			
			System.out.println("\nEscriba los tags a los que desea pertenecer separados sólo por coma (tag1,tag2...) : ");
			tema = leerStrings.nextLine();
			
			String[] tags_leidos = tema.split(",");
			
			ArrayList<String> temas = new ArrayList<String>();
			
			for(int i = 0;i<tags_leidos.length;i++)
			{
				temas.add(tags_leidos[i]);
			}
			
			System.out.println(temas);
			
			
			a.setId(Long.valueOf(id));
			a.setCuerpo(contenido);
			a.setTitulo(titulo);
			a.setFecha(date);
			a.setAutor(autor);
			a.setTipo(Tipo.tematico);
			a.setEstado(estadoAnuncio);
			a.setTemas(temas);
			
			manager.getAnuncioDAO().insertar(a);
			
			System.out.println("Anuncio añadido");
			
			
		}
		
	}
	
	
	public void modificarAnuncio(MySQLDAOManager manager, GestorContactos gestor) throws DAOException
	{
		System.out.println("MODIFICAR ANUNCIO\n");
		
		ArrayList<Anuncio>lista_anuncios = manager.getAnuncioDAO().obtenerPorIdAutor(gestor.getUserLogeado().getId());
		
		if(lista_anuncios.size() > 0)
		{
			System.out.println("Tus anuncios: ");
			for(int i = 0;i<lista_anuncios.size();i++)
			{
				System.out.println(lista_anuncios.get(i).toString());
			}
			System.out.print("Selecciona el id de un anuncio para modificarlo o -1  para salir : ");
			Long id = leerInts.nextLong();
				
			
			
			
			if(id >= 0)
			{
				if(manager.getAnuncioDAO().obtener(id).getIdAutor().equals(gestor.getUserLogeado().getId()))
				{
					editarAnuncio(manager, id, gestor.getUserLogeado(), gestor);
					System.out.println("Anuncio editado con éxito");						
				}
				else
				{
					System.out.println("Selecciona un id válido");
				}
			
			}
			else
			{
				if(id != -1)
					System.out.println("Selecciona un id válido");
			}
			
			
		}
		
		
	}
	

	
	public void listarAnuncios(MySQLDAOManager manager, GestorContactos gestor) throws DAOException
	{
		Contacto usuarioLogeado = gestor.getUserLogeado();
		Long idUsuarioLogeado = usuarioLogeado.getId();
		int numA = 0;
		
		ArrayList<Anuncio> anuncios = manager.getAnuncioDAO().obtenerTodos();
		
		
		for(int i = 0;i<anuncios.size();i++)
		{
			Anuncio anuncio = anuncios.get(i);
			
			Contacto autor =  manager.getContactoDAO().obtener(anuncio.getIdAutor());
			//System.out.println("El id del autor del anuncio es : " + autor.getId());
			
			String formateoAnuncio = "Titulo : " + anuncio.getTitulo() + "\nAutor:" + autor.getNombre()+ " " + autor.getApellidos() + "\n\n" + anuncio.getCuerpo() + "\n\n" + anuncio.getFecha() + "\n----------------------------------------\n";
				if(anuncio.getEstado().equals(Estado.publicado))
				{
					if(anuncio.getTipo() == Tipo.general)
					{
						System.out.println(formateoAnuncio);
						numA++;						
					}
					if(anuncio.getTipo() == Tipo.individualizado)
					{
						AnuncioIndividualizado b = (AnuncioIndividualizado) anuncio;
						
						ArrayList<Long> destinatarios = b.getDestinatarios();
						for(int o = 0;o<destinatarios.size();o++)
						{
							if(destinatarios.get(o) == idUsuarioLogeado)
							{
								System.out.println("Para ti y " + (destinatarios.size()-1) + " persona(s) más...\n");
								System.out.println(formateoAnuncio);
								numA++;		
							}
						}
					}
					if(anuncio.getTipo() == Tipo.flash)
					{
						AnuncioFlash b = (AnuncioFlash) anuncio;
						int diaActual, mesActual, anyoActual;
						LocalDate currentdate = LocalDate.now();
						diaActual = currentdate.getDayOfMonth();
						mesActual = currentdate.getMonthValue();
						anyoActual = currentdate.getYear();
						
						if(anyoActual >= b.getFechaInicio().getYear() && anyoActual <= b.getFechaFin().getYear())
						{
							if(mesActual >= b.getFechaInicio().getMonthValue() && mesActual <= b.getFechaFin().getMonthValue())
							{
								if(diaActual >= b.getFechaInicio().getDayOfMonth() && diaActual <= b.getFechaFin().getDayOfMonth())
								{
									DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
									System.out.println("[Expira el " + b.getFechaFin().format(formatter)+"]");
									System.out.println(formateoAnuncio);
									numA++;
								}
							}
						}
						
					}
					if(anuncio.getTipo() == Tipo.tematico)
					{
						boolean mostrado = false;
						AnuncioTematico b = (AnuncioTematico) anuncio;
						
						
						
						ArrayList<String> temasAnuncio = b.getTemas();
						ArrayList<String> temasUserLogged = usuarioLogeado.getTags();
						
						for(int o=0;o<temasUserLogged.size();o++)
						{
							for(int u = 0;u<temasAnuncio.size();u++)
							{
								if(temasAnuncio.get(u).equals(temasUserLogged.get(o)) && !mostrado)
								{
									System.out.println("Temas del anuncio : " + temasAnuncio);
									System.out.println(formateoAnuncio);
									numA++;	
									mostrado = true;
								}
							}
						}
						
					}

				}				
		}
						
		if(numA == 0)
			System.out.println("No hay anuncios para mostrar.\n");
	}
	

	public void archivarAnuncio(MySQLDAOManager manager, GestorContactos gestor) throws DAOException{
		System.out.println("ARCHIVAR ANUNCIO\n");
		
		ArrayList<Anuncio>lista_anuncios = manager.getAnuncioDAO().obtenerPorIdAutor(gestor.getUserLogeado().getId());
		
		if(lista_anuncios.size() > 0)
		{
			System.out.println("Tus anuncios: ");
			for(int i = 0;i<lista_anuncios.size();i++)
			{
				if(lista_anuncios.get(i).getEstado() == Estado.publicado)
					System.out.println(lista_anuncios.get(i).toString());
			}	
			System.out.println("Selecciona el id de un anuncio para modificarlo o -1 para salir : ");
			Long id = leerInts.nextLong();
			if(id >= 0)
			{
				Anuncio anuncio = manager.getAnuncioDAO().obtener(id);
				if(anuncio.getIdAutor().equals(gestor.getUserLogeado().getId()))
				{
					anuncio.setEstado(Estado.archivado);
					System.out.println("Anuncio archivado con éxito");		
					manager.getAnuncioDAO().Modificar(anuncio);
				}
				else
				{
					System.out.println("Selecciona un id válido");
				}
			
			}
			else
			{
				if(id != -1)
					System.out.println("Selecciona un id válido");
			}

			
		}
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
	
	
	
	
	public void buscarPorDestinatario() {}
	

		
	private boolean fechasValidas(int diaInicio, int mesInicio, int anyoInicio, int diaFin, int mesFin, int anyoFin)
	{
		boolean resultado = true;
		
		if(diaInicio > 31 || diaInicio < 1 || diaFin > 31 || diaFin < 1 || mesInicio < 1 || mesInicio > 12 || mesFin < 1 || mesFin > 12)
			resultado = false;
		
		if(anyoInicio<=anyoFin)
		{
			if(mesInicio<=mesFin)
			{
				if(diaInicio<=diaFin)// 12/3/2020 13/3/2020
					resultado = true;
				else
					resultado = false;
			}
			else
				resultado = false;
		}
		else
			resultado = false;
		
		
		if(resultado)
		{
			System.out.println("Datos introducidos correctamente.");
		}
		else
			System.out.println("Datos erroneos");
		
		
		return resultado;
	}
	
	private void editarAnuncio(MySQLDAOManager manager, Long id, Contacto autor, GestorContactos gestor) throws DAOException{
		
		String titulo, contenido;
		Date date = new Date();
		
		System.out.println("ANUNCIO A MODIFICAR : ");
		System.out.println(manager.getAnuncioDAO().obtener(id).toString());
		
		
		Anuncio a = manager.getAnuncioDAO().obtener(id);
		System.out.print("Titulo del anuncio : ");
		titulo = leerStrings.nextLine();
		System.out.print("Contenido : ");		
		contenido = leerStrings.nextLine();
		
		
		if(a.getTipo() == Tipo.individualizado)
		{
			AnuncioIndividualizado b = (AnuncioIndividualizado) a;
			int eleccion;
			System.out.println("¿Desea añadir o eliminar destinatarios?");
			System.out.println("1-Añadir");
			System.out.println("2-Eliminar");
			System.out.println("3-Nada");
			eleccion = leerInts.nextInt();
			
			ArrayList<Long> contactosAnuncio = b.getDestinatarios();
			
			
			if(eleccion == 1)
			{
				int idUser;
				System.out.println("Lista de usuarios : ");
				gestor.mostrarContactos(manager);
				
				System.out.print("Selecciona un id : ");
				idUser = leerInts.nextInt();
				
				
				boolean error = false;
				for(int i = 0;i<contactosAnuncio.size();i++)
				{
					if(contactosAnuncio.get(i) == idUser)
						error = true;
				}
				if(!error)
				{
					b.agregarDestinatario((long)idUser);
					manager.getAnuncioDAO().agregarDestinatario((long)idUser, b.getId());
					System.out.println("Destinatario añadido correctamente.");
				}
					
				
			}
			else if(eleccion == 2)
			{
				System.out.println("Lista de destinatarios del anuncio : ");
				
				for(int i = 0;i<contactosAnuncio.size();i++)
				{
					System.out.println("ID : " + contactosAnuncio.get(i) + " | " + manager.getContactoDAO().obtener(contactosAnuncio.get(i)));
				}
				
				int idDest;
				System.out.println("Selecciona el id del destinatario a quitar : ");
				idDest = leerInts.nextInt();
				
				boolean error = true;
				for(int i = 0;i<contactosAnuncio.size();i++)
				{
					if(contactosAnuncio.get(i) == idDest)
						error = false;
				}
				
				if(!error)
				{
					b.quitarDestinatario((long)idDest);
					manager.getAnuncioDAO().quitarDestinatario((long)idDest,b.getId());
					System.out.println("Destinatario borrado correctamente.");
				}
					
				
			}
			
			
		}
		if(a.getTipo() == Tipo.tematico)
		{
			AnuncioTematico b = (AnuncioTematico) a;
			int eleccion;
			System.out.println("¿Desea añadir o eliminar temas?");
			System.out.println("1-Añadir");
			System.out.println("2-Eliminar");
			System.out.println("3-Nada");
			eleccion = leerInts.nextInt();
			
			ArrayList<String> temasAnuncio = b.getTemas();
			
			if(eleccion == 1)
			{
				System.out.print("Lista de temas del anuncio : " + b.getTemas());

				System.out.println("Lista de temas totales : ");
				for(int i = 0;i<gestor.get_tag_disponibles().length;i++)
					System.out.print(gestor.get_tag_disponibles()[i] + " ");
				System.out.print("Elige el tema que quieres añadir : ");
				String tema = leerStrings.nextLine();
				
				if(tagValido(tema))
				{
					b.agregarTema(tema);
				}
				
				
			}
			else if(eleccion == 2)
			{
				System.out.println("Lista de temas del anuncio : " + b.getTemas());
				System.out.print("Elige el tema que quieres quitar : ");
				String tema = leerStrings.nextLine();
				if(tagValido(tema))
				{
					boolean valido=false;
					for(int i = 0;i<temasAnuncio.size();i++)
					{
						if(temasAnuncio.get(i).equals(tema))
							valido = true;
					}
					if(valido)
						b.quitarTema(tema);
				}
			}
		}
		if(a.getTipo() == Tipo.flash)
		{
			AnuncioFlash b = (AnuncioFlash) a;
			
			System.out.println("Desea cambiar las fechas? : ");
			System.out.println("1- Si");
			System.out.println("2- No");
			System.out.print("Elige : ");
			int eleccion = leerInts.nextInt();
			
			if(eleccion == 1)
			{	
				do {
					
					
					
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					System.out.println("Elige la fecha de inicio del anuncio (xx/xx/xxxx) : ");
					LocalDate startDate = LocalDate.parse(leerStrings.nextLine(), formatter);

					System.out.println("Elige la fecha de fin del anuncio (xx/xx/xxxx) : ");
					LocalDate endDate = LocalDate.parse(leerStrings.nextLine(), formatter);
					b.setFechaInicio(startDate);
					b.setFechaFin(endDate);
					
					
					
				} while( !fechasValidas(b.getDiaInicio(),b.getMesInicio(),b.getAnyoInicio(),b.getDiaFin(),b.getMesFin(),b.getAnyoFin()));
				
				manager.getAnuncioDAO().updateFechas(b.getFechaInicio(), b.getFechaFin(), b.getId());
				
			}
			else if(eleccion != 2 && eleccion != 1)
			{
				System.out.println("No has seleccionado una opcion válida, así que no se cambiarán las fechas.");
			}
			
			
			
		}
		
		
		a.setCuerpo(contenido);
		a.setTitulo(titulo);
		a.setFecha(date);
		a.setAutor(autor);
		
		manager.getAnuncioDAO().Modificar(a);
		
	}
	
	public void publicarAnuncio(MySQLDAOManager manager, GestorContactos gestor) throws DAOException{
		
		ArrayList<Anuncio> anuncios = manager.getAnuncioDAO().obtenerPorIdAutor(gestor.getUserLogeado().getId());
		
		System.out.println("Anuncios editados sin publicar : ");
		
		int numA = 0;
		for(int i = 0;i<anuncios.size();i++)
		{
			if(anuncios.get(i).getEstado() == Estado.editado)
			{
				System.out.println(anuncios.get(i));
				numA++;
			}
		}
		if(numA>0)
		{
			System.out.print("Selecciona el id del anuncio a publicar : ");
			Long id = leerInts.nextLong();
			Anuncio a = manager.getAnuncioDAO().obtener(id);
			if(a.getEstado() == Estado.editado)
			{
				
				a.setEstado(Estado.publicado);
				manager.getAnuncioDAO().Modificar(a);
				System.out.println("Anuncio publicado correctamente.");
			}
			else
			{
				System.out.println("Selecciona un id válido.");
			}			
		}
		else
		{
			System.out.println("No tienes anuncios sin publicar.");
		}

		
	}

	
	private ArrayList<Anuncio> buscarPorFecha(MySQLDAOManager manager, Date fecha) throws DAOException {
		ArrayList<Anuncio> list = new ArrayList();
		ArrayList<Anuncio> anuncios = manager.getAnuncioDAO().obtenerTodos();
		for (Anuncio a : anuncios) {
			if (a.getFecha() == fecha) list.add(a);
		}

		return list;
	}
	
	
}
