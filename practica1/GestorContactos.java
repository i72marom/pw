package practica1;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class GestorContactos {

	private static GestorContactos instance = null;
	
	
	private int numContactos;
	private Contacto contactos[];
	
	
	private GestorContactos() {
		numContactos = 0;
		
		
		
	}
	
	public static GestorContactos getInstance() {
		if(instance == null) {
			instance = new GestorContactos();
		}
		return instance;
	}
	
	
	public void cargarContactos()
	{
		
	}
	public void guardarContactos()
	{
		
	}
	public void darAltaContacto()
	{
		
		Scanner leer = new Scanner(System.in);
		
		String nombre, apellido1, apellido2, email;
		int edad;
		
		System.out.println("Ingresa el nombre del nuevo contacto: ");
		nombre = leer.nextLine();
		System.out.println("Ingresa el primer apellido del nuevo contacto: ");
		apellido1 = leer.nextLine();
		System.out.println("Ingresa el segundo apellido del nuevo contacto: ");
		apellido2 = leer.nextLine();
		System.out.println("Ingresa la edad del nuevo contacto: ");
		edad = leer.nextInt();
		System.out.println("Ingresa el email del nuevo contacto: ");
		email = leer.nextLine();
		
		Date fechaActual = new Date();
		
		Contacto nuevoContacto = new Contacto(nombre, apellido1, apellido2, edad, email, fechaActual);
		
		if(!existeContacto(nuevoContacto))
		{
			contactos[numContactos] = nuevoContacto;
		}
		
		
	}
	public void darBajaContacto(Contacto contacto)
	{
		
	}
	public void consultarDatosContacto(Contacto contacto)
	{
		
	}
	public void actualizarDatosContacto(Contacto contacto)
	{
		
	}
	public boolean buscarContactoPorEmail(String email)
	{
		
		
		return false;
	}
	public boolean buscarContactoPorNombre(String nombre)
	{
		
		
		return false;
	}
	public boolean buscarContactoPorApellidos(String apellido1, String apellido2)
	{
		
		
		return false;
	}
	public boolean buscarContactoPorEdad(int edad)
	{
		
		
		return false;
	}
	
	public void mostrarContactos()
	{
		System.out.println( this.contactos );
	}
	
	public boolean existeContacto(Contacto contacto)
	{
		return false;
	}
	
}
