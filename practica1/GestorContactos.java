package practica1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

public class GestorContactos {

	private static GestorContactos instance = null;
	
	
	private	Scanner leer = new Scanner(System.in);
	
	private static ArrayList<Contacto> contactos_ = new ArrayList();
	private String[] tags_disponibles_;
	
	

	
	
	private GestorContactos() {
		cargarTagsDisponibles();
		
		
		
	}
	
	public static GestorContactos getInstance() {
		if(instance == null) {
			instance = new GestorContactos();
		}
		return instance;
	}
	
	
	public void cargarContactos() {
		
		
		
	}
	
	public void guardarContactos() {
		
	}
	
	public void darAltaContacto() {
		

		
		String nombre, apellido1, apellido2, email;
		ArrayList<String> tags_user = new ArrayList();
		int edad;
		
		String linea;
		
		System.out.println("Ingresa el nombre del nuevo contacto: ");
		nombre = leer.next();
		System.out.println("Ingresa el primer apellido del nuevo contacto: ");
		apellido1 = leer.next();
		System.out.println("Ingresa el segundo apellido del nuevo contacto: ");
		apellido2 = leer.next();
		System.out.println("Ingresa la edad del nuevo contacto: ");
		edad = leer.nextInt();
		System.out.println("Ingresa el email del nuevo contacto: ");
		email = leer.next();
		
	    System.out.println("Tags disponibles : ");
	    for(int i = 0;i<tags_disponibles_.length;i++)
	    {
	    	System.out.print(tags_disponibles_[i] + " ");
	    }
		
		System.out.println("\nEscriba los tags a los que desea pertenecer separados sólo por coma (tag1,tag2...) : ");
		linea = leer.next();
		
		String[] tags_leidos = linea.split(",");
		
		for(int i = 0;i<tags_leidos.length;i++)
		{
			System.out.println("Checkeando " + tags_leidos[i]);
			if(perteneceTagsDisponibles(tags_leidos[i]) && !tieneEseTagYa(tags_leidos[i]))
			{
				tags_user.add(tags_leidos[i]);
			}
		}


		
		Date fechaActual = new Date();
		
		Contacto nuevoContacto = new Contacto(nombre, apellido1, apellido2, edad, email, fechaActual, tags_user);
		
		
		
		if(existeContacto(nuevoContacto) == -1)
		{
			System.out.println("Contacto añadido.");
			contactos_.add(nuevoContacto);
		}
		else
		{
			System.out.println("Ya hay un contacto registrado con ese nombre o ese email");
		}
		
		
	}
	
	
	public void darBajaContacto() {
		
		String nombre, apellido1, apellido2;
		
		System.out.println("Ingresa el nombre del contacto a borrar: ");
		nombre = leer.next();
		System.out.println("Ingresa el primer apellido del contacto a borrar: ");
		apellido1 = leer.next();
		System.out.println("Ingresa el segundo apellido del contacto a borrar: ");
		apellido2 = leer.next();
		
		Contacto contactoABorrar = new Contacto(nombre, apellido1, apellido2);
		
		System.out.println("Contacto a borrar: " + contactoABorrar);
		
		if(existeContacto(contactoABorrar) != -1)
		{
			contactos_.remove(existeContacto(contactoABorrar));
			System.out.println("Contacto borrado.");
		}
		else
		{
			System.out.println("Ese contacto no existe.");
		}
		
	}
	
	
	public void consultarDatosContacto() {
		
		
		
	}
	
	private void actualizarDatosContacto(int index) {
		
		
		
		
		
	}
	
	public void buscarContactoPorEmail() {
		String email;
		
		System.out.println("Ingresa el email del contacto: ");
		email = leer.next();
		
		
		for(int i = 0;i<contactos_.size();i++)
		{
			
			if(contactos_.get(i).getEmail().equals(email))
			{
				System.out.println("ID: " + i + " | " +contactos_.get(i));
			}
		}
		
		
		
	}
	public void buscarContactoPorNombre() {
		
		String nombre, apellido1, apellido2;
		
		System.out.println("Ingresa el nombre del contacto: ");
		nombre = leer.next();
		System.out.println("Ingresa el primer apellido del contacto: ");
		apellido1 = leer.next();
		System.out.println("Ingresa el segundo apellido del contacto: ");
		apellido2 = leer.next();
		
		Contacto contacto = new Contacto(nombre, apellido1, apellido2);
		
		int id = existeContacto(contacto);
		if(id != -1)
		{
			System.out.println("ID: " + id + " | " + contactos_.get(id));
		}
		else
		{
			System.out.println("Ese contacto no existe.");
		}
		
	}
	
	
	public void buscarContactoPorEdad() {
		
		int edad;
		
		System.out.println("Ingresa la edad del contacto: ");
		edad = leer.nextInt();
		
		
		for(int i = 0;i<contactos_.size();i++)
		{
			if(contactos_.get(i).getEdad() == edad)
			{
				System.out.println(contactos_.get(i));
			}
		}
		
	}
	
	public void mostrarContactos() {
		for(int i = 0;i<contactos_.size();i++)
		{
			System.out.println("ID: " + i + " | " + contactos_.get(i));
		}
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
	
	private void cargarTagsDisponibles() {
		
		
		Properties prop = new Properties();
		InputStream input = null;
		

		try {

		    input = new FileInputStream("D:\\Users\\Javi\\eclipse-workspace\\practica1\\bin\\configuracion.properties");

		    // load a properties file
		    prop.load(input);

		    String valor = prop.getProperty("tags_disponibles");
		    this.tags_disponibles_ = valor.split(", ");
		    
		    
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
			if(tags_disponibles_[i].equals(tag))
			{
				resultado = true;
			}
		}
		
		return resultado;
	}
	
	private boolean tieneEseTagAnadido(String tag, ) {
		
		
		
	}
	
}
