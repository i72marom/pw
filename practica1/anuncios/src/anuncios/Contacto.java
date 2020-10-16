package anuncios;

import java.util.ArrayList;
import java.util.Date;


/**
 * Clase contacto
 * @author Javi | Manu
 *
 */
public class Contacto {
	
	private String nombre, apellido1, apellido2, email;
	private int edad;
	private Date d;
	private ArrayList<String> tags_;
	
	
	/**
	 * Constructor de la clase Contacto
	 * @param nombre
	 * @param apellido1
	 * @param apellido2
	 * @param edad
	 * @param email
	 * @param d
	 * @param tags
	 */
	public Contacto(String nombre, String apellido1, String apellido2, int edad, String email, Date d, ArrayList<String> tags) {
		
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.email = email;
		this.edad = edad;
		this.d = d;
		this.tags_ = tags;
		
	}
	
	/**
	 * Constructor sobrecargado de la clase contacto
	 * @param nombre
	 * @param apellido1
	 * @param apellido2
	 */

	public Contacto(String nombre, String apellido1, String apellido2) {
		
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.email = "";
		this.edad = 0;
		this.d = d;
		tags_ = null;
	}
	
	/**
	 * Getter del nombre
	 * @return retorna el nombre del Contacto
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setter del nombre
	 * @param nombre del contacto
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Getter del primer apellido
	 * @return retorna el primer apellido del Contacto
	 */
	public String getApellido1() {
		return apellido1;
	}

	
	/**
	 * Setter del primer apellido
	 * @param apellido1
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	
	/**
	 * Getter del segundo apellido
	 * @return retorna el segundo apellido del Contacto
	 */	
	public String getApellido2() {
		return apellido2;
	}

	/**
	 * Setter del segundo apellido
	 * @param apellido2
	 */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	
	/**
	 * Getter de los dos apellidos
	 * @return retorna los dos apellidos
	 */
	public String getApellidos() {
		return apellido1 + " " + apellido2;
	}
	
	/**
	 * Setter de los dos apellidos
	 * @param apellido1
	 * @param apellido2
	 */
	public void setApellidos(String apellido1, String apellido2) {
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
	}
	
	/**
	 * Getter del email
	 * @return retorna el email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter del email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Getter de la fecha
	 * @return retorna la fecha
	 */
	public Date getD() {
		return d;
	}

	/**
	 * Setter de la fecha
	 * @param d
	 */
	public void setD(Date d) {
		this.d = d;
	}
	
	/**
	 * Getter de la edad
	 * @return retorna la edad 
	 */
	public int getEdad() {
		return edad;
	}

	/**
	 * Setter de la edad
	 * @param edad
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}
	/**
	 * Getter de los tags
	 * @return retorna los tags
	 */
	public ArrayList<String> getTags() {
		return tags_;
	}

	/**
	 * Setter de los tags
	 * @param tags
	 */
	public void setTags(ArrayList<String> tags) {
		this.tags_ = tags;
	}
	
	/**
	 * Agrega un nuevo tag a la lista de tags
	 * @param tag
	 */
	public void agregarTag(String tag)
	{
		this.tags_.add(tag);
	}
	/**
	 * Quita un tag a la lista de tags
	 * @param tag
	 */
	public void quitarTag(String tag)
	{
		this.tags_.remove(tag);
	}

	/**
	 * Metodo ToString
	 */
	public String toString() {
		return "Contacto [Nombre=" + nombre + " | apellido1=" + apellido1 + " | apellido2=" + apellido2 + " | email="
				+ email + " | edad=" + edad + " | d=" + d + " | tags= " + tags_.toString() + "]\n";
	}
	
	/**
	 * Compara un objeto de tipo Contacto con otro
	 * @param contactoAComparar
	 * @return retorna 0 si son el mismo objeto
	 */
	public int compareTo(Contacto contactoAComparar) {
		
		//System.out.println("Comparando " + this.getEmail());
		//System.out.println("con " + contactoAComparar.getEmail());
		
		if((this.getNombre().equals(contactoAComparar.getNombre()) && this.getApellidos().equals(contactoAComparar.getApellidos())) || this.getEmail().equals(contactoAComparar.getEmail()))
		{
			
			return 0;
		}
		else
		{
			return 1;
		}
		
		
	}
	

	
	

	
}
