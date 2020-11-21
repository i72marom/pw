

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import es.uco.pw.business.anuncio.GestorAnuncios;
import es.uco.pw.business.contacto.GestorContactos;
import es.uco.pw.data.dao.DAOException;
import es.uco.pw.data.mysqldao.MySQLDAOManager;

public class main {

	static Scanner leerCadenas = new Scanner(System.in);
	static Scanner leerInts = new Scanner(System.in);
	static GestorContactos gestorContactos = GestorContactos.getInstance();
	static GestorAnuncios tablon = new GestorAnuncios();	
	
	
	
	public static void main(String[] args) throws SQLException, DAOException{
		
		MySQLDAOManager manager = new MySQLDAOManager();		
		menu(manager);
	}

	
	public static void menu(MySQLDAOManager manager) throws DAOException
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
			crearCuenta(manager);
		}
		if(opcion == 2)
		{
			identificarte(manager);
			
		}
		
	}
	
	public static void crearCuenta(MySQLDAOManager manager) throws DAOException
	{
		if(!gestorContactos.crearCuenta(manager))
			identificarte(manager);
		else
			menu(manager);
			
			
		
		
	}
	
	public static void identificarte(MySQLDAOManager manager) throws DAOException
	{
		if(!gestorContactos.identificarte(manager))
			menuLogeado(manager);
		else
			menu(manager);
	}
	
	private static void menuLogeado(MySQLDAOManager manager) throws DAOException
	{
		int opcion;
		
		
		System.out.println("------------------------------------");
		System.out.println("USER: " + gestorContactos.getUserLogeado().getNombre() + " " +gestorContactos.getUserLogeado().getApellidos());
		
		System.out.println("1- Leer tablon");
		System.out.println("2- Crear anuncio");
		System.out.println("3- Publicar anuncio");
		System.out.println("4- Modificar anuncio");
		System.out.println("5- Archivar anuncio");
		System.out.println("6- Modificar mis datos");
		System.out.println("7- Listar usuarios");
		System.out.println("8- Visualizar mis datos");
		System.out.println("9- Cerrar sesión");
		System.out.println("0- Salir de la aplicación");
		
		System.out.print("Elige una opcion : ");
		
		opcion = leerInts.nextInt();
		
		if(opcion == 1)
		{
			tablon.listarAnuncios(manager, gestorContactos);
			menuLogeado(manager);
		}
		else if(opcion == 2)
		{
			tablon.crearAnuncio(manager, gestorContactos);
			menuLogeado(manager);
		}
		else if(opcion == 3)
		{
			tablon.publicarAnuncio(manager, gestorContactos);
			menuLogeado(manager);
		}
		else if(opcion == 4)
		{
			tablon.modificarAnuncio(manager, gestorContactos);
			menuLogeado(manager);	
		}
		else if(opcion == 5)
		{
			tablon.archivarAnuncio(manager, gestorContactos);
			menuLogeado(manager);
			
		}
		else if(opcion == 6)
		{
			
			gestorContactos.modificarMisDatos(manager);	
			menuLogeado(manager);
			
		}
		else if(opcion == 7)
		{
			gestorContactos.mostrarContactos(manager);
			menuLogeado(manager);
		}
		else if(opcion == 8)
		{
			gestorContactos.visualizarMisDatos(gestorContactos.getUserLogeado());
			menuLogeado(manager);
		}
		else if(opcion == 9)
		{
			gestorContactos.cerrarSesion();
			menu(manager);
		}
		else if(opcion == 0)
		{
			gestorContactos.cerrarSesion();
			
		}
	}

}
