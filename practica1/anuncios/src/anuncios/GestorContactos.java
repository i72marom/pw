package anuncios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;


/**
 * Clase del gestor de Contactos
 * @author Javi | Manu
 *
 */
public class GestorContactos {

	private static GestorContactos instance = null;
	
	
	
	private	Scanner leerCadenas = new Scanner(System.in);
	private	Scanner leerNumeros = new Scanner(System.in);
	
	private static ArrayList<Contacto> contactos_ = new ArrayList();
	private String[] tags_disponibles_;
	private String ruta_archivo_;
	private String nombre_archivo_;
	
	
	private boolean logged_;
	private int id_contacto_logeado_;
	private Contacto contacto_logeado_;
	

	
	/**
	 * Constructor privado de la clase GestorContactos
	 */
	private GestorContactos() {
		cargarConfiguracion();
		cargarContactos();
		contacto_logeado_ = null;
		logged_ = false;
		
	}
	
	public static GestorContactos getInstance() {
		if(instance == null) {
			instance = new GestorContactos();
		}
		return instance;
	}
	
	public boolean getLogged()
	{
		return logged_;
	}
	
	public void setLogged(boolean logged)
	{
		this.logged_ = logged;
	}
	
	public void cargarContactos() {
		
		File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;

	      try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	         archivo = new File (ruta_archivo_+nombre_archivo_);
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);

	         // Lectura del fichero
	         String linea;
	         while((linea=br.readLine())!=null)
	         {
	        	 //System.out.println(linea);
	        	 
	        	 String[] lineaSpliteada = linea.split(", ");
	        	 
	        	 //System.out.println(lineaSpliteada.length);
	        	 int numTags = lineaSpliteada.length-5; //ESTO ES PORQUE ESTOY GUARDANDO EN FORMATO : NOMBRE APELLIDO1 APELLIDO2 EDAD EMAIL TAGS[], ENTONCES LOS 5 PRIMEROS VALORES LOS RESTO
	        	 String nombre, apellido1, apellido2, email;
	        	 int edad;
	        	 ArrayList<String> tags = new ArrayList();
	        	 
	        	 nombre = lineaSpliteada[0];
	        	 apellido1 = lineaSpliteada[1];
	        	 apellido2 = lineaSpliteada[2];
	        	 edad = Integer.parseInt(lineaSpliteada[3]);
	        	 email = lineaSpliteada[4];
	        	 
	        	 for(int i = 5;i<lineaSpliteada.length;i++)
	        	 {
	        		 tags.add(lineaSpliteada[i]);
	        	 }
	        	 
	        	 Date date = new Date();
	        	 
	        	 Contacto contacto = new Contacto(nombre, apellido1, apellido2, edad, email, date, tags);
	        	 
	        	 contactos_.add(contacto);
	        	 
	         }
	            
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         // En el finally cerramos el fichero, para asegurarnos
	         // que se cierra tanto si todo va bien como si salta 
	         // una excepcion.
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }

		
		
	}
	
	public void guardarContactos() {
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(ruta_archivo_+nombre_archivo_);
            pw = new PrintWriter(fichero);

            for (int i = 0; i<contactos_.size(); i++)
            {
            	  pw.print(contactos_.get(i).getNombre() + ", " + contactos_.get(i).getApellido1() + ", " + contactos_.get(i).getApellido2() + ", " + contactos_.get(i).getEdad() + ", " + contactos_.get(i).getEmail() + ", ");
            	  for(int o = 0;o< contactos_.get(i).getTags().size();o++)
            	  {
            		  if(o!=contactos_.get(i).getTags().size()-1)
            			  pw.print(contactos_.get(i).getTags().get(o) + ", ");
            		  else
            			  pw.print(contactos_.get(i).getTags().get(o));
            			  
            	  }
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
	
	public boolean darAltaContacto() {
		boolean error=false;

		
		String nombre, apellido1, apellido2, email;
		ArrayList<String> tags_user = new ArrayList();
		int edad;
		
		String linea;
		
		System.out.print("Ingresa el nombre del nuevo contacto: ");
		nombre = leerCadenas.nextLine();
		System.out.print("Ingresa el primer apellido del nuevo contacto: ");
		apellido1 = leerCadenas.nextLine();
		System.out.print("Ingresa el segundo apellido del nuevo contacto: ");
		apellido2 = leerCadenas.nextLine();
		System.out.print("Ingresa la edad del nuevo contacto: ");
		edad = leerNumeros.nextInt();
		System.out.print("Ingresa el email del nuevo contacto: ");
		email = leerCadenas.nextLine();
		
	    System.out.print("Tags disponibles : ");
	    for(int i = 0;i<tags_disponibles_.length;i++)
	    {
	    	System.out.print(tags_disponibles_[i] + " ");
	    }
		
		System.out.println("\nEscriba los tags a los que desea pertenecer separados sólo por coma (tag1,tag2...) : ");
		linea = leerCadenas.nextLine();
		
		String[] tags_leidos = linea.split(",");
		
		for(int i = 0;i<tags_leidos.length;i++)
		{
			if(perteneceTagsDisponibles(tags_leidos[i]) && !tieneEseTagAnadido(tags_leidos[i], tags_user))
			{
				tags_user.add(tags_leidos[i]);
			}
		}


		
		Date fechaActual = new Date();
		
		Contacto nuevoContacto = new Contacto(nombre, apellido1, apellido2, edad, email, fechaActual, tags_user);
		
		
		
		if(existeContacto(nuevoContacto) == -1)
		{
			System.out.println("Cuenta creada.");
			contactos_.add(nuevoContacto);
		}
		else
		{
			System.out.println("Ya hay un contacto registrado con ese nombre o ese email");
			error = true;
		}
		
		return error;
	}
	
	
	public void darBajaContacto() {
		
		mostrarContactos();
		
		System.out.print("Introduzca el ID del contacto a borrar : ");
		int id = leerNumeros.nextInt();
		
		//System.out.println(id + " | " + contactos_.size());
		
		if(id < 0 || id > contactos_.size())
		{
			System.out.println("Ese contacto no existe.");

		}
		else
		{
			contactos_.remove(id);
			System.out.println("Contacto borrado.");			
		}
		
	}
	
	
	public void modificarContacto() {
		
		
		mostrarContactos();
		
		
		if(contactos_.size() > 0)
		{
			System.out.print("Seleccione el id del contacto a modificar : ");
			int id = leerNumeros.nextInt();
			
			if(id<0||id>contactos_.size())
			{
				System.out.println("Ingresa un id válido.");
			}
			else
			{
				int opcion, subopcion;
				System.out.println("ID: " + id + " | " +contactos_.get(id));
				System.out.println("1- Modificar nombre");
				System.out.println("2- Modificar edad");
				System.out.println("3- Modificar email");
				System.out.println("4- Modificar tags");
				System.out.println("5- Salir");
				System.out.print("Elija una opcion : ");
				
				opcion = leerNumeros.nextInt();
				
				if(opcion == 1)
				{
					String nombre, apellido1, apellido2;
					System.out.print("Ingresa el nuevo nombre del contacto: ");
					nombre = leerCadenas.nextLine();
					System.out.print("Ingresa el nuevo primer apellido del contacto: ");
					apellido1 = leerCadenas.nextLine();
					System.out.print("Ingresa el nuevo segundo apellido del contacto: ");
					apellido2 = leerCadenas.nextLine();
					
					contactos_.get(id).setNombre(nombre);
					contactos_.get(id).setApellidos(apellido1, apellido2);
					System.out.println("Nombre y apellidos cambiados a " + nombre + " " + apellido1 + " "+ apellido2 + ".\n");
					
				}
				else if(opcion == 2)
				{
					int edad;
					System.out.print("Introduzca la nueva edad :");
					edad = leerNumeros.nextInt();
					
					contactos_.get(id).setEdad(edad);
					System.out.println("Edad cambiada a " + edad + ".\n");
				}
				else if(opcion == 3)
				{
					String email;
					System.out.print("Introduce el nuevo email : ");
					email = leerCadenas.nextLine();
					
					contactos_.get(id).setEmail(email);
					System.out.println("Email cambiado a " + email +".\n");
				}
				else if(opcion == 4)
				{
					System.out.println("1- Agregar tag");
					System.out.println("2- Quitar tag");
					System.out.print("Seleccione opcion :");
					
					subopcion = leerNumeros.nextInt();
					
					if(subopcion == 1)
					{
						System.out.println("Tags restantes por añadir : " + tagsRestantes(id));
						System.out.print("Escriba un tag : ");
						
						String nuevoTag = leerCadenas.nextLine();
						System.out.println(nuevoTag);
						if(!tieneEseTagAnadido(nuevoTag, contactos_.get(id).getTags()) && perteneceTagsDisponibles(nuevoTag))
						{
							contactos_.get(id).agregarTag(nuevoTag);		
							System.out.println("Tag " + nuevoTag + " añadido.\n");
						}
						else
						{
							System.out.println("Tag erroneo");
						}

					}
					else if(subopcion == 2)
					{
						System.out.println("Tags del user : " + tagsUser(id));
						System.out.print("Escriba el tag a quitar : ");
						String tagQuitar = leerCadenas.nextLine();
						if(tieneEseTagAnadido(tagQuitar, contactos_.get(id).getTags()))
						{
							contactos_.get(id).quitarTag(tagQuitar);
							System.out.println("Tag " + tagQuitar + " quitado.\n");
						}
						else
						{
							System.out.println("Tag erroneo.");
						}
					}
					
				}
			}
		}
		
		
		
		
		
	}
	
	
	public void buscarContactoPorEmail() {
		String email;
		int numC = 0;		
		System.out.print("Ingresa el email del contacto: ");
		email = leerCadenas.nextLine();

		
		
		for(int i = 0;i<contactos_.size();i++)
		{
			
			if(contactos_.get(i).getEmail().equals(email))
			{
				System.out.println("ID: " + i + " | " +contactos_.get(i));
				numC++;
			}
		}
		if(numC == 0)
			System.out.println("No existe ningún contacto con esos criterios de búsqueda");
		
		
		
	}
	public void buscarContactoPorNombre() {
		
		String nombre, apellido1, apellido2;
		
		System.out.print("Ingresa el nombre del contacto: ");
		nombre = leerCadenas.nextLine();
		System.out.print("Ingresa el primer apellido del contacto: ");
		apellido1 = leerCadenas.nextLine();
		System.out.print("Ingresa el segundo apellido del contacto: ");
		apellido2 = leerCadenas.nextLine();
		
		Contacto contacto = new Contacto(nombre, apellido1, apellido2);
		
		int id = existeContacto(contacto);
		if(id != -1)
		{
			System.out.println("ID: " + id + " | " + contactos_.get(id));
		}
		else
		{
			System.out.println("No existe ningún contacto con esos criterios de búsqueda");
		}
		
	}
	
	public void buscarContactoPorTag() {
		
		String tag;
		
		System.out.print("Ingresa el tag: ");
		tag = leerCadenas.nextLine();
		int numC = 0;
		
		
		for(int i = 0;i<contactos_.size();i++)
		{
			ArrayList<String> tags_user = contactos_.get(i).getTags();
			for(int o = 0;o<tags_user.size();o++)
			{
				if(tag.equals(tags_user.get(o)))
				{
					System.out.println("ID: " + i + " | " +contactos_.get(i));
					numC++;
				}				
			}

		}
		if(numC == 0)
			System.out.println("No existe ningún contacto con esos criterios de búsqueda");
		
	}
	
	
	public void buscarContactoPorEdad() {
		
		int edad, numC = 0;
		
		System.out.println("Ingresa la edad del contacto: ");
		edad = leerNumeros.nextInt();
		
		
		for(int i = 0;i<contactos_.size();i++)
		{
			if(contactos_.get(i).getEdad() == edad)
			{
				System.out.println(contactos_.get(i));
				numC++;
			}
		}
		if(numC==0)
			System.out.println("No existe ningún contacto con esos criterios de búsqueda");
		
	}
	
	public void mostrarContactos() {
		int numC = 0;
		for(int i = 0;i<contactos_.size();i++)
		{
			System.out.println("ID: " + i + " | " + contactos_.get(i));
			numC++;
		}
		if(numC==0)
			System.out.println("No hay ningún contacto guardado.");
	}
	
	public int existeContacto(Contacto contacto) {
		
		int resultado = -1;
		//System.out.println("Comparando el contacto : " + contacto);
		
		for(int i = 0;i<contactos_.size() && resultado == -1 ;i++)
		{
			if(contacto.compareTo(contactos_.get(i)) == 0)
			{
				resultado = i;
			}
		}
		//System.out.println("El id es " + resultado);
		return resultado;
	}
	
	private void cargarConfiguracion() {
		
		
		Properties prop = new Properties();
		InputStream input = null;
		

		try {
			//D:\\Users\\Javi\\eclipse-workspace\\practica1\\bin\\configuracion.properties
			String path = "src" + File.separator +  "configuracion.properties";
		    input = new FileInputStream(path);

		    // load a properties file
		    prop.load(input);

		    String valor = prop.getProperty("tags_disponibles");
		    this.tags_disponibles_ = valor.split(", ");
		    ruta_archivo_ = prop.getProperty("ruta_fichero");
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
	
	private boolean perteneceTagsDisponibles(String tag) {
		boolean resultado = false;
		
		for(int i = 0;i<tags_disponibles_.length && !resultado;i++)
		{
			//System.out.println("Checkeando |"+tag+"| con " + tags_disponibles_[i].replace(" ", ""));
			if(tags_disponibles_[i].replace(" ","").equals(tag))
			{
				//System.out.println("No entra aqui?");
				resultado = true;
			}
		}
		//System.out.println("Resultado : " + resultado);
		return resultado;
	}
	
	private boolean tieneEseTagAnadido(String tag, ArrayList<String> array) {
		
		boolean res = false;
		
		for(int i = 0;i<array.size() && !res;i++)
		{
			if(array.get(i).equals(tag))
				res = true;
		}
		
		
		return res;
	}
	
	
	private String tagsRestantes(int id)
	{
		String resultado = "";
		
		ArrayList<String> tags_user = contactos_.get(id).getTags();
		
		for(int i = 0;i<tags_disponibles_.length;i++)
		{
			if(!tieneEseTagAnadido(tags_disponibles_[i], tags_user))
			{
				resultado += tags_disponibles_[i] += " ";
			}
		}
		
		return resultado;
		
	}
	private String tagsUser(int id)
	{
		ArrayList<String> tags_user = contactos_.get(id).getTags();
		return tags_user.toString();
	}
	
	public boolean crearCuenta()
	{
		boolean error = false;
		if(darAltaContacto())
			error = true;
		return error;
	}
	public boolean identificarte()
	{
		boolean error = false;
		System.out.println("|----------- LOGIN -----------|");
		System.out.println("Introduce tu nombre : ");
		String nombre = leerCadenas.nextLine();
		System.out.println("Introduce tu primer apellido : ");
		String apellido1 = leerCadenas.nextLine();
		System.out.println("Introduce tu segundo apellido : ");
		String apellido2 = leerCadenas.nextLine();
		
		Contacto contacto = new Contacto(nombre, apellido1, apellido2);
		
		int id = existeContacto(contacto);
		if(id != -1)
		{
			logged_ = true;
			contacto_logeado_ = contactos_.get(id);
			id_contacto_logeado_ = id;
			System.out.println("Te has loggeado correctamente.");
		}
		else
		{
			System.out.println("No existe ningún usuario con ese nombre");
			error = true;
		}
		
		return error;
		
	}
	
	public Contacto getUserLogeado()
	{
		return contacto_logeado_;
	}
	public void cerrarSesion()
	{
		if(logged_ == true)
		{
			logged_ = false;
			contacto_logeado_ = null;
			System.out.println("Has cerrado sesión.");
		}

		
	}
	public void modificarMisDatos()
	{
		int opcion, subopcion, id;
		
		id=id_contacto_logeado_;
		
		System.out.println("ID: " + id + " | " +contactos_.get(id));
		System.out.println("1- Modificar nombre");
		System.out.println("2- Modificar edad");
		System.out.println("3- Modificar email");
		System.out.println("4- Modificar tags");
		System.out.println("5- Salir");
		System.out.print("Elija una opcion : ");
		
		opcion = leerNumeros.nextInt();
		
		if(opcion == 1)
		{
			String nombre, apellido1, apellido2;
			System.out.print("Ingresa el nuevo nombre del contacto: ");
			nombre = leerCadenas.nextLine();
			System.out.print("Ingresa el nuevo primer apellido del contacto: ");
			apellido1 = leerCadenas.nextLine();
			System.out.print("Ingresa el nuevo segundo apellido del contacto: ");
			apellido2 = leerCadenas.nextLine();
			
			contactos_.get(id).setNombre(nombre);
			contactos_.get(id).setApellidos(apellido1, apellido2);
			System.out.println("Nombre y apellidos cambiados a " + nombre + " " + apellido1 + " "+ apellido2 + ".\n");
			
		}
		else if(opcion == 2)
		{
			int edad;
			System.out.print("Introduzca la nueva edad :");
			edad = leerNumeros.nextInt();
			
			contactos_.get(id).setEdad(edad);
			System.out.println("Edad cambiada a " + edad + ".\n");
		}
		else if(opcion == 3)
		{
			String email;
			System.out.print("Introduce el nuevo email : ");
			email = leerCadenas.nextLine();
			
			contactos_.get(id).setEmail(email);
			System.out.println("Email cambiado a " + email +".\n");
		}
		else if(opcion == 4)
		{
			System.out.println("1- Agregar tag");
			System.out.println("2- Quitar tag");
			System.out.print("Seleccione opcion :");
			
			subopcion = leerNumeros.nextInt();
			
			if(subopcion == 1)
			{
				System.out.println("Tags restantes por añadir : " + tagsRestantes(id));
				System.out.print("Escriba un tag : ");
				
				String nuevoTag = leerCadenas.nextLine();
				System.out.println(nuevoTag);
				if(!tieneEseTagAnadido(nuevoTag, contactos_.get(id).getTags()) && perteneceTagsDisponibles(nuevoTag))
				{
					contactos_.get(id).agregarTag(nuevoTag);		
					System.out.println("Tag " + nuevoTag + " añadido.\n");
				}
				else
				{
					System.out.println("Tag erroneo");
				}

			}
			else if(subopcion == 2)
			{
				System.out.println("Tags del user : " + tagsUser(id));
				System.out.print("Escriba el tag a quitar : ");
				String tagQuitar = leerCadenas.nextLine();
				if(tieneEseTagAnadido(tagQuitar, contactos_.get(id).getTags()))
				{
					contactos_.get(id).quitarTag(tagQuitar);
					System.out.println("Tag " + tagQuitar + " quitado.\n");
				}
				else
				{
					System.out.println("Tag erroneo.");
				}
			}
		}
	}
}
