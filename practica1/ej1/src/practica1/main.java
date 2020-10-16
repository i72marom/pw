package practica1;

import java.util.Date;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		
		Date fecha = new Date();
		
		
		menu();
		
		
	}
	
	public static void menu() {
		
		Scanner sc = new Scanner(System.in);
		
		GestorContactos gestorContactos = GestorContactos.getInstance();
		
		int opcion;
		System.out.println("----------------------------------------------");
		System.out.println("SELECCIONE OPCION DEL MENU : ");
		System.out.println("1- Dar de alta a un contacto");
		System.out.println("2- Dar de baja a un contacto");
		System.out.println("3- Buscar a un contacto");
		System.out.println("4- Modificar a un contacto");
		System.out.println("5- Mostrar todos los contactos");
		System.out.println("6- Salir");
		
		System.out.print("Elige una opcion : ");
		opcion = sc.nextInt();
		System.out.println("----------------------------------------------");
		
		if(opcion == 1)
		{
			gestorContactos.darAltaContacto();
			menu();
		}
		else if(opcion == 2)
		{
			gestorContactos.darBajaContacto();
			menu();
		}
		else if(opcion == 3)
		{
			int subopcion;
			System.out.println("Como desea buscar al contacto : ");
			System.out.println("1- Por nombre y apellidos");
			System.out.println("2- Por email");
			System.out.println("3- Por edad");
			System.out.println("4- Por tags");
			
			subopcion = sc.nextInt();
			
			if(subopcion == 1) 
			{
				gestorContactos.buscarContactoPorNombre();
				menu();
			}
			else if(subopcion == 2)
			{
				gestorContactos.buscarContactoPorEmail();
				menu();
			}
			else if(subopcion == 3)
			{
				gestorContactos.buscarContactoPorEdad();
				menu();
			}
			else if(subopcion == 4)
			{
				gestorContactos.buscarContactoPorTag();
				menu();
			}
		}
		else if(opcion == 4)
		{	
			gestorContactos.modificarContacto();
			menu();
		}
		else if(opcion == 5)
		{
			gestorContactos.mostrarContactos();
			menu();
		}
		else if(opcion == 6)
		{
			gestorContactos.guardarContactos();
		}
		else
		{
			System.out.println("Elige una opcion correcta.");
			menu();
		}
		
		
	}

}
