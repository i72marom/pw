package practica1;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class GestorContactos {

	private static GestorContactos instance = null;
	
	
	private	Scanner leer = new Scanner(System.in);
	
	private int num_contactos_;
	private static ArrayList<Contacto> contactos_ = new ArrayList();
	
	
	private GestorContactos() {
		num_contactos_ = 0;
		
		
		
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
		int edad;
		
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
		
		Date fechaActual = new Date();
		
		Contacto nuevoContacto = new Contacto(nombre, apellido1, apellido2, edad, email, fechaActual);
		
		
		
		if(existeContacto(nuevoContacto) == -1)
		{
			System.out.println("Contacto añadido.");
			contactos_.add(nuevoContacto);
			num_contactos_++;
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
	
	public void actualizarDatosContacto() {
		
		
		
		
		
	}
	
	public void buscarContactoPorEmail() {
		String email;
		
		System.out.println("Ingresa el email del contacto: ");
		email = leer.next();
		
		
		for(int i = 0;i<contactos_.size();i++)
		{
			
			if(contactos_.get(i).getEmail().equals(email))
			{
				System.out.println(contactos_.get(i));
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
		
		if(existeContacto(contacto) != -1)
		{
			System.out.println(contactos_.get(existeContacto(contacto)));
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
		System.out.println( this.contactos_ );
	}
	
	public int existeContacto(Contacto contacto)
	{
		
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
	
}
