package anuncios;

import java.util.ArrayList;
import java.util.Scanner;

public class main {

	static Scanner leerCadenas = new Scanner(System.in);
	static Scanner leerInts = new Scanner(System.in);
	static GestorContactos gestorContactos = GestorContactos.getInstance();
	static TablonDeAnuncios tablon = new TablonDeAnuncios();	
	
	public static void main(String[] args) {
		
		
		

		menu();
		

		
	}
	public static void menu()
	{
		
		
		int opcion = 0;
		System.out.println("---------------------------------------");
		System.out.println("1- Crear cuenta");
		System.out.println("2- Identificarte");
		System.out.println("3- Cerrar");
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
		if(opcion == 3)
			gestorContactos.guardarContactos();
		
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
		System.out.println("6- Listar usuarios");
		System.out.println("7- Visualizar mis datos");
		System.out.println("8- Cerrar sesión");
		System.out.println("9- Salir de la aplicación");
		
		System.out.print("Elige una opcion : ");
		
		opcion = leerInts.nextInt();
		
		if(opcion == 1)
		{
			tablon.listarAnuncios(gestorContactos);
			menuLogeado();
		}
		else if(opcion == 2)
		{
			tablon.crearAnuncio(gestorContactos);
			menuLogeado();
		}
		else if(opcion == 3)
		{
			tablon.modificarAnuncio(gestorContactos);
			menuLogeado();	
		}
		else if(opcion == 4)
		{
			tablon.archivarAnuncio(gestorContactos);
			menuLogeado();
			
		}
		else if(opcion == 5)
		{
			
			gestorContactos.modificarMisDatos();	
			menuLogeado();
			
		}
		else if(opcion == 6)
		{
			gestorContactos.mostrarContactos();
			menuLogeado();
		}
		else if(opcion == 7)
		{
			gestorContactos.visualizarMisDatos(gestorContactos.getUserLogeado());
			menuLogeado();
		}
		else if(opcion == 8)
		{
			gestorContactos.cerrarSesion();
			tablon.guardarTablon();
			menu();
		}
		else if(opcion == 9)
		{
			gestorContactos.cerrarSesion();
			gestorContactos.guardarContactos();
			tablon.guardarTablon();
			
		}
	}

}
