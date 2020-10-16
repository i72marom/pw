package anuncios;

import java.util.Scanner;

public class main {

	static Scanner leerCadenas = new Scanner(System.in);
	static Scanner leerInts = new Scanner(System.in);
	static GestorContactos gestorContactos = GestorContactos.getInstance();
	
	public static void main(String[] args) {
		
		
		

		menu();
		

		
	}
	public static void menu()
	{
		int opcion = 0;
		System.out.println("---------------------------------------");
		System.out.println("1- Crear cuenta");
		System.out.println("2- Identificarte");
		System.out.print("Elige una opcion : ");
		opcion = leerInts.nextInt();

		if(opcion == 1)
		{
			crearCuenta();
		}
		if(opcion == 2)
		{
			identificarte();
			
		}
		
	}
	
	public static void crearCuenta()
	{
		if(!gestorContactos.crearCuenta())
			identificarte();
		else
			menu();
			
			
		
		
	}
	
	public static void identificarte()
	{
		if(!gestorContactos.identificarte())
			menuLogeado();
		else
			menu();
	}
	
	private static void menuLogeado()
	{
		int opcion;
		
		
		System.out.println("------------------------------------");
		System.out.println("USER: " + gestorContactos.getUserLogeado().getNombre() + " " +gestorContactos.getUserLogeado().getApellidos());
		
		System.out.println("1- Leer tablon");
		System.out.println("2- Crear anuncio");
		System.out.println("3- Modificar anuncio");
		System.out.println("4- Archivar anuncio");
		System.out.println("5- Modificar mis datos");
		System.out.println("6- Cerrar sesión");
		
		System.out.print("Elige una opcion : ");
		
		opcion = leerInts.nextInt();
		
		if(opcion == 1)
		{
			
		}
		else if(opcion == 2)
		{
			
		}
		else if(opcion == 3)
		{
			
		}
		else if(opcion == 4)
		{
			
		}
		else if(opcion == 5)
		{
			
		}
		else if(opcion == 6)
		{
			gestorContactos.cerrarSesion();
			menu();
		}
	}

}
