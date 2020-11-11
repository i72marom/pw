package anuncios;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;



public class TablonDeAnuncios {
	private ArrayList<Anuncio> tablon_ = new ArrayList();
	
	String[] tags_disponibles_;

	
	Scanner leerStrings = new Scanner(System.in);
	Scanner leerInts = new Scanner(System.in);
	
	Generador anuncio = new Generador();
	 
	public TablonDeAnuncios() {
		cargarConfiguracion();
		
	}

	public void cargarConfiguracion()
	{
		Properties prop = new Properties();
		InputStream input = null;
		

		try {
			//D:\\Users\\Javi\\eclipse-workspace\\practica1\\bin\\configuracion.properties
			String path = "src" + File.separator +  "configuracion.properties";
			String nombre_archivo_;
		    input = new FileInputStream(path);

		    // load a properties file
		    prop.load(input);

		    String valor = prop.getProperty("tags_disponibles");
		    this.tags_disponibles_ = valor.split(", ");
		    nombre_archivo_ = prop.getProperty("nombre_fichero");
		    
		    for(int i = 0;i<tags_disponibles_.length;i++)
		    {
		    	tags_disponibles_[i].trim();
		    }
		    
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
	
	////////////////////////////////////////////////FUNCIONES DE CARGA Y GUARDA DEL TABLON////////////////////////////////////////////////////////////
	public void guardarTablon() { 
		
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("files" + File.separator + "tablon.txt");
            pw = new PrintWriter(fichero);

            for (int i = 0; i<tablon_.size(); i++)
            {
            	  pw.print(i + "| " + tablon_.get(i).getTitulo() + "| " + tablon_.get(i).getAutor().getNombre() + "| " + tablon_.get(i).getAutor().getApellido1() + "| "+ tablon_.get(i).getAutor().getApellido2() + "| " +tablon_.get(i).getCuerpo()+ "| " + tablon_.get(i).getEstado() + "| " + tablon_.get(i).getTipo() + "| " + tablon_.get(i).getFecha());
            	  pw.println();
            }
              

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
		
		
	}
	public void cargarTablon(GestorContactos gestor) { 
	
	}

	
	
	
	
	
	//////////////////////////////////////////////FUNCIONES PUBLICAS////////////////////////////////////////////////////////////////////
	
	public void crearAnuncio(GestorContactos gestor)
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
			
			id = tablon_.size();
			
			
			
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
			tablon_.add(a);
			
		}
		else if(tipo == 2)
		{
			//pedir datos del anuncio +fechas
			AnuncioFlash a = (AnuncioFlash) anuncio.creaAnuncioFlash();
			String fechaInicio, fechaFin;
			int diaInicio, mesInicio, anyoInicio;
			int diaFin, mesFin, anyoFin;
			
			do {
				System.out.println("Elige la fecha de inicio del anuncio (xx/xx/xxxx) : ");
				
				fechaInicio = leerStrings.nextLine();
				
				System.out.println("Elige la fecha de fin del anuncio (xx/xx/xxxx) : ");
				
				fechaFin = leerStrings.nextLine();
				

				
				String[] fechaInicio_aux = fechaInicio.split("/");
				diaInicio = Integer.parseInt(fechaInicio_aux[0]);
				mesInicio = Integer.parseInt(fechaInicio_aux[1]);
				anyoInicio = Integer.parseInt(fechaInicio_aux[2]);
				
				String[] fechaFin_aux = fechaFin.split("/");
				diaFin = Integer.parseInt(fechaFin_aux[0]);
				mesFin = Integer.parseInt(fechaFin_aux[1]);
				anyoFin = Integer.parseInt(fechaFin_aux[2]);
				
				
				
			} while( !fechasValidas(diaInicio,mesInicio,anyoInicio, diaFin,mesFin,anyoFin) );
			
			a.setId(Long.valueOf(id));
			a.setCuerpo(contenido);
			a.setTitulo(titulo);
			a.setFecha(date);
			a.setAutor(autor);
			a.setTipo(Tipo.flash);
			a.setEstado(estadoAnuncio);
			
			a.setDiaInicio(diaInicio);
			a.setMesInicio(mesInicio);
			a.setAnyoInicio(anyoInicio);
			a.setDiaFin(diaFin);
			a.setMesFin(mesFin);
			a.setAnyoFin(anyoFin);
			
			tablon_.add(a);
			
		}
		else if(tipo == 3)
		{
			//pedir datos del anuncio +destinatarios
			
			AnuncioIndividualizado a = (AnuncioIndividualizado) anuncio.creaAnuncioIndividualizado();
			
			String inputString;
			
			System.out.println("Lista de usuarios : ");
			gestor.mostrarContactos();
			System.out.println("Elige los indices de los contactos a los que quieres enviarlo (separados sólo por comas) : ");
			inputString = leerStrings.nextLine();
			
			String[] indicesSeleccionados = inputString.split(",");
			ArrayList<Contacto> destinatarios = new ArrayList();
			for(int i = 0;i<indicesSeleccionados.length;i++)
			{
				
				//System.out.println(gestor.buscarContactoPorId());
				destinatarios.add(gestor.buscarContactoPorId(Integer.parseInt(indicesSeleccionados[i])));
			}
			
			a.setId(Long.valueOf(id));
			a.setCuerpo(contenido);
			a.setTitulo(titulo);
			a.setFecha(date);
			a.setAutor(autor);
			a.setTipo(Tipo.individualizado);
			a.setDestinatarios(destinatarios);
			a.setEstado(estadoAnuncio);
			
			System.out.println("Anuncio añadido");
			
			tablon_.add(a);
			
			
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
			
			ArrayList<String> temas = new ArrayList();
			
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
			
			System.out.println("Anuncio añadido");
			
			tablon_.add(a);
			
		}
		
	}
	
	
	public void modificarAnuncio(GestorContactos gestor)
	{
		System.out.println("MODIFICAR ANUNCIO\n");
		
		ArrayList<Anuncio>lista_anuncios = buscarPorPropietario(gestor.getUserLogeado()); 
		
		if(lista_anuncios.size() > 0)
		{
			System.out.println("Tus anuncios: ");
			for(int i = 0;i<lista_anuncios.size();i++)
			{
				System.out.println(lista_anuncios.get(i).toString());
			}
			System.out.print("Selecciona el id de un anuncio para modificarlo o -1  para salir : ");
			int id = leerInts.nextInt();
				
			
			
			
			if(id >= 0 && id <= tablon_.size())
			{
				if(tablon_.get(id).getAutor().equals(gestor.getUserLogeado()))
				{
					editarAnuncio(id, gestor.getUserLogeado(), gestor);
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
	

	
	public void listarAnuncios(GestorContactos gestor)
	{
		Contacto usuarioLogeado = gestor.getUserLogeado();
		int numA = 0;
		System.out.println("Lista de anuncios : ");
		for(int i = 0;i<tablon_.size();i++)
		{
			Anuncio anuncio = tablon_.get(i);
			
			String formateoAnuncio = i + " | Titulo : " + anuncio.getTitulo() + "\t Autor:" + anuncio.getAutor().getNombre() + " " + anuncio.getAutor().getApellidos() + "\n" + anuncio.getCuerpo() + "\n" + anuncio.getFecha() + "\n----------------------------------------";
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
						
						ArrayList<Contacto> destinatarios = b.getDestinatarios();
						for(int o = 0;o<destinatarios.size();o++)
						{
							if(destinatarios.get(o).equals(usuarioLogeado))
							{
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
						
						if(anyoActual >= b.getAnyoInicio() && anyoActual <= b.getAnyoFin())
						{
							if(mesActual >= b.getMesInicio() && mesActual <= b.getMesFin())
							{
								if(diaActual >= b.getDiaInicio() && diaActual <= b.getDiaFin())
								{
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
	

	public void archivarAnuncio(GestorContactos gestor) {
		System.out.println("ARCHIVAR ANUNCIO\n");
		
		ArrayList<Anuncio>lista_anuncios = buscarPorPropietario(gestor.getUserLogeado()); 
		
		if(lista_anuncios.size() > 0)
		{
			System.out.println("Tus anuncios: ");
			for(int i = 0;i<lista_anuncios.size();i++)
			{
				if(lista_anuncios.get(i).getEstado() == Estado.publicado)
					System.out.println(lista_anuncios.get(i).toString());
			}	
			System.out.println("Selecciona el id de un anuncio para modificarlo o -1 para salir : ");
			int id = leerInts.nextInt();
			if(id >= 0 && id <= tablon_.size())
			{
				if(tablon_.get(id).getAutor().equals(gestor.getUserLogeado()))
				{
					tablon_.get(id).setEstado(Estado.archivado);
					System.out.println("Anuncio archivado con éxito");						
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
	
	
	
	public ArrayList<Anuncio> buscarPorPropietario(Contacto propietario) {
		ArrayList<Anuncio> list = new ArrayList();

		for (int i = 0;i<tablon_.size();i++) {
			if (tablon_.get(i).getAutor().equals(propietario)) list.add(tablon_.get(i));
		}

		return list;
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
	
	private void editarAnuncio(int id, Contacto autor, GestorContactos gestor) {
		
		String titulo, contenido, estado;
		Tipo tipo;
		Date date = new Date();
		
		System.out.println("ANUNCIO A MODIFICAR : ");
		System.out.println(tablon_.get(id).toString());
		
		
		Anuncio a = tablon_.get(id);
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
			
			ArrayList<Contacto> contactosTotales = gestor.getContactos();
			ArrayList<Contacto> contactosAnuncio = b.getDestinatarios();
			
			
			if(eleccion == 1)
			{
				int idUser;
				System.out.println("Lista de usuarios : ");
				gestor.mostrarContactos();
				
				System.out.print("Selecciona un id : ");
				idUser = leerInts.nextInt();
				
				
				boolean error = false;
				for(int i = 0;i<contactosAnuncio.size();i++)
				{
					if(contactosAnuncio.get(i).equals(contactosTotales.get(idUser)))
						error = true;
				}
				if(!error)
				{
					b.agregarDestinatario(contactosTotales.get(idUser));
					System.out.println("Destinatario añadido correctamente.");
				}
					
				
			}
			else if(eleccion == 2)
			{
				System.out.println("Lista de destinatarios del anuncio : ");
				
				for(int i = 0;i<contactosAnuncio.size();i++)
				{
					System.out.println("ID : " + contactosAnuncio.get(i).getId() + " | " + contactosAnuncio.get(i).toString());
				}
				
				int idDest;
				System.out.println("Selecciona el id del destinatario a quitar : ");
				idDest = leerInts.nextInt();
				
				boolean error = true;
				for(int i = 0;i<contactosAnuncio.size();i++)
				{
					if(contactosAnuncio.get(i).equals(contactosAnuncio.get(idDest)))
						error = false;
				}
				
				if(!error)
				{
					b.quitarDestinatario(contactosAnuncio.get(idDest));
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
			
			
			
		}
		
		
		a.setCuerpo(contenido);
		a.setTitulo(titulo);
		a.setFecha(date);
		a.setAutor(autor);
		tablon_.set(id, a);
	}
	
	private void publicarAnuncio(int id) {
		tablon_.get(id).setEstado(Estado.publicado);
	}

	private void guardarAnuncio(Anuncio nuevo_anuncio) {
		tablon_.add(nuevo_anuncio);
	}
	
	private ArrayList<Anuncio> buscarPorFecha(Date fecha) {
		ArrayList<Anuncio> list = new ArrayList();

		for (Anuncio a :tablon_) {
			if (a.getFecha() == fecha) list.add(a);
		}

		return list;
	}
	
	
}
