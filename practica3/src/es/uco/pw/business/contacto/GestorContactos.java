package es.uco.pw.business.contacto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import es.uco.pw.data.dao.DAOException;
import es.uco.pw.data.mysqldao.MySQLDAOManager;


/**
 * Clase que gestiona todo lo relacionado con los Contactos a través de un MySQLDAOManager ( que es un manager de los DAOS de la aplicación )
 * @author Javi | Manu
 *
 */
public class GestorContactos {

	private static GestorContactos instance = null;
	
	
	
	private	Scanner leerCadenas = new Scanner(System.in);
	private	Scanner leerNumeros = new Scanner(System.in);
	
	//private static ArrayList<Contacto> contactos_ = new ArrayList();
	private String[] tags_disponibles_;
	
	
	private boolean logged_;
	private Long id_contacto_logeado_;
	private Contacto contacto_logeado_;
	

	
	/**
	 * Constructor privado de la clase GestorContactos
	 */
	private GestorContactos() {
		cargarConfiguracion();
		contacto_logeado_ = null;
		logged_ = false;
		
	}
	/**
	 * Constructor publico de la clase GestorContactos implementado mediante el patrón de diseño Singleton.
	 * @return GestorContactos
	 */
	public static GestorContactos getInstance() {
		if(instance == null) {
			instance = new GestorContactos();
		}
		return instance;
	}
	/**
	 * Getter de la variable booleana logged_
	 * @return logged_
	 */
	public boolean getLogged()
	{
		return logged_;
	}
	/**
	 * Setter de la variable booleana logged_
	 * @param logged
	 */
	public void setLogged(boolean logged)
	{
		this.logged_ = logged;
	}
	
	
	/**
	 * Función del menú para crear un nuevo usuario en la base de datos.
	 * @param manager
	 * @return
	 * @throws DAOException
	 */
	public boolean darAltaContacto(MySQLDAOManager manager) throws DAOException {
		boolean error=false;

		
		String nombre, apellido1, apellido2, email, password;
		ArrayList<String> tags_user = new ArrayList<String>();
		int edad;
		
		String linea;
		
		System.out.print("Ingresa tu nombre : ");
		nombre = leerCadenas.nextLine();
		System.out.print("Ingresa tu primer apellido : ");
		apellido1 = leerCadenas.nextLine();
		System.out.print("Ingresa tu segundo apellido : ");
		apellido2 = leerCadenas.nextLine();
		System.out.print("Ingresa tu edad : ");
		edad = leerNumeros.nextInt();
		System.out.print("Ingresa tu email : ");
		email = leerCadenas.nextLine();
		System.out.println("Ingresa tu contraseña : ");
		password = leerCadenas.nextLine();
		
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("Ingresa tu fecha de cumpleanos (xx/xx/xxxx): ");
		LocalDate fechaCumpleanos = LocalDate.parse(leerCadenas.nextLine(), formatter);
		
		
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

		//int id = contactos_.size();
		//Long id_ = Long.valueOf(id);
		
		Contacto nuevoContacto = new Contacto(nombre, apellido1, apellido2, edad, email, fechaCumpleanos, tags_user, password);
		
		
		
		if(existeContacto(nuevoContacto, manager) == -1)
		{
			System.out.println("Cuenta creada.");
			//contactos_.add(nuevoContacto);
			manager.getContactoDAO().insertar(nuevoContacto);
		}
		else
		{
			System.out.println("Ya hay un contacto registrado con ese email");
			error = true;
		}
		
		return error;
	}
	
	
	/**
	 * Función del menú para mostrar todos los usuarios registrados en la aplicación.
	 * @param manager
	 * @throws DAOException
	 */
	public void mostrarContactos(MySQLDAOManager manager) throws DAOException {
		int numC = 0;
		
		ArrayList<Contacto> contactos = manager.getContactoDAO().obtenerTodos();
		
		for(int i = 0;i<contactos.size();i++)
		{
			System.out.println("ID: " + contactos.get(i).getId() + " | " + contactos.get(i));
			numC++;
		}
		if(numC==0)
			System.out.println("No hay ningún contacto guardado.");
	}
	
	
	/**
	 * Función intermedia para crear una cuenta nueva
	 * @param manager
	 * @return
	 * @throws DAOException
	 */
	public boolean crearCuenta(MySQLDAOManager manager) throws DAOException
	{
		boolean error = false;
		if(darAltaContacto(manager))
			error = true;
		return error;
	}
	/**
	 * Función para logearte en el sistema.
	 * @param manager
	 * @return error
	 * @throws DAOException
	 */
	public boolean identificarte(MySQLDAOManager manager) throws DAOException
	{
		boolean error = false;
		System.out.println("|----------- LOGIN -----------|");
		System.out.println("Introduce tu email : ");
		String email = leerCadenas.nextLine();
		System.out.println("Introduce tu contraseña : ");
		String contrasena = leerCadenas.nextLine();
		
		Contacto contacto = manager.getContactoDAO().obtenerPorEmail(email);
		
		if(contacto != null)
		{
			if(contrasena.equals(contacto.getPassword())) {
				logged_ = true;
				contacto_logeado_ = contacto;
				id_contacto_logeado_ = contacto.getId();
				System.out.println("Te has loggeado correctamente.");				
			}
			else
			{
				System.out.println("El email y la contraseña no coinciden.");
				error = true;
			}

		}
		else
		{
			System.out.println("No existe ningún usuario con ese email");
			error = true;
		}
		
		return error;
		
	}
	/**
	 * Obtiene el objeto Contacto que está logeado actualmente.
	 * @return Contacto
	 */
	public Contacto getUserLogeado()
	{
		return contacto_logeado_;
	}
	/**
	 * Función para cerrar sesión.
	 */
	public void cerrarSesion()
	{
		if(logged_ == true)
		{
			logged_ = false;
			contacto_logeado_ = null;
			System.out.println("Has cerrado sesión.");
		}

		
	}
	/**
	 * Función del menú para modificar los datos del usuario logeado.
	 * @param manager
	 * @throws DAOException
	 */
	public void modificarMisDatos(MySQLDAOManager manager) throws DAOException
	{
		int opcion, subopcion;
		Long id;
		
		id=id_contacto_logeado_;
		
		System.out.println(contacto_logeado_);
		System.out.println("1- Modificar nombre");
		System.out.println("2- Modificar edad");
		System.out.println("3- Modificar tags");
		System.out.println("4- Cambiar contraseña");
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
			
			contacto_logeado_.setNombre(nombre);
			contacto_logeado_.setApellidos(apellido1, apellido2);
			System.out.println("Nombre y apellidos cambiados a " + nombre + " " + apellido1 + " "+ apellido2 + ".\n");
			
		}
		else if(opcion == 2)
		{
			int edad;
			System.out.print("Introduzca la nueva edad :");
			edad = leerNumeros.nextInt();
			
			contacto_logeado_.setEdad(edad);
			System.out.println("Edad cambiada a " + edad + ".\n");
		}
		else if(opcion == 3)
		{
			System.out.println("1- Agregar tag");
			System.out.println("2- Quitar tag");
			System.out.print("Seleccione opcion :");
			
			subopcion = leerNumeros.nextInt();
			
			if(subopcion == 1)
			{
				System.out.println("Tags restantes por añadir : " + tagsRestantes(manager, id));
				System.out.print("Escriba un tag : ");
				
				String nuevoTag = leerCadenas.nextLine();
				System.out.println(nuevoTag);
				if(!tieneEseTagAnadido(nuevoTag, contacto_logeado_.getTags()) && perteneceTagsDisponibles(nuevoTag))
				{
					contacto_logeado_.agregarTag(nuevoTag);		
					System.out.println("Tag " + nuevoTag + " añadido.\n");
				}
				else
				{
					System.out.println("Tag erroneo");
				}

			}
			else if(subopcion == 2)
			{
				System.out.println("Tags del user : " + tagsUser(manager, id));
				System.out.print("Escriba el tag a quitar : ");
				String tagQuitar = leerCadenas.nextLine();
				if(tieneEseTagAnadido(tagQuitar, contacto_logeado_.getTags()))
				{
					contacto_logeado_.quitarTag(tagQuitar);
					System.out.println("Tag " + tagQuitar + " quitado.\n");
				}
				else
				{
					System.out.println("Tag erroneo.");
				}
			}
		}
		else if(opcion == 4)
		{
			String passwd;
			System.out.print("Introduce tu nueva password : ");
			passwd = leerCadenas.nextLine();
			contacto_logeado_.setPassword(passwd);
		}
		
		manager.getContactoDAO().Modificar(contacto_logeado_);
		
		
	}
	/**
	 * Devuelve la lista de tags disponibles en el sistema.
	 * @return
	 */
	public String[] get_tag_disponibles()
	{
		return tags_disponibles_;
	}
	/**
	 * Función del menú para visualizar los datos del usuario logeado.
	 * @param contacto
	 */
	public void visualizarMisDatos(Contacto contacto)
	{
		System.out.println("TUS DATOS: ");
		System.out.println("Nombre completo : " + contacto.getNombre() + " " + contacto.getApellidos());
		System.out.println("Edad : " + contacto.getEdad());
		System.out.println("Email : " + contacto.getEmail());
		System.out.println("Fecha cumpleaños : " + contacto.getDiaCumpleanos()+"/"+contacto.getMesCumpleanos()+"/"+contacto.getAnyoCumpleanos());
		System.out.println("Temas : " + contacto.getTags());
	}
	
	public int existeContacto(Contacto contacto, MySQLDAOManager manager) throws DAOException {
		
		int resultado = -1;
		
		ArrayList<Contacto> contactos = manager.getContactoDAO().obtenerTodos();
		
		
		for(int i = 0;i<contactos.size() && resultado == -1 ;i++)
		{
			if(contacto.compareTo(contactos.get(i)) == 0)
			{
				resultado = i;
			}
		}
		return resultado;
	}
	
	private void cargarConfiguracion() {
		
		
		Properties prop = new Properties();
		InputStream input = null;
		

		try {
			//D:\\Users\\Javi\\eclipse-workspace\\practica1\\bin\\configuracion.properties
			String path = "configuracion.properties";
		    input = new FileInputStream(path);

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
	
	
	private String tagsRestantes(MySQLDAOManager manager, Long id) throws DAOException
	{
		String resultado = "";
		
		ArrayList<String> tags_user = manager.getContactoDAO().obtener(id).getTags();
		
		for(int i = 0;i<tags_disponibles_.length;i++)
		{
			if(!tieneEseTagAnadido(tags_disponibles_[i], tags_user))
			{
				resultado += tags_disponibles_[i] += " ";
			}
		}
		
		return resultado;
		
	}
	private String tagsUser(MySQLDAOManager manager, Long id) throws DAOException
	{
		ArrayList<String> tags_user = manager.getContactoDAO().obtener(id).getTags();
		return tags_user.toString();
	}
	

}
