package es.uco.pw.business.contacto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


/**
 * Clase contacto
 * @author Javi | Manu
 *
 */
public class Contacto {
	
	private Long id_;
	private String nombre, apellido1, apellido2, email, password;
	private LocalDate fechaCumpleanos;
	private int edad;
	private int diaCumpleanos, mesCumpleanos, anyoCumpleanos;
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
	public Contacto(String nombre, String apellido1, String apellido2, int edad, String email, LocalDate fechaCumpleanos, ArrayList<String> tags, String password) {
		
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.email = email;
		this.edad = edad;
		this.fechaCumpleanos = fechaCumpleanos;
		this.tags_ = tags;
		this.password = password;
		
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
		tags_ = null;
	}
	
	/**
	 * Getter del nombre
	 * @return retorna el nombre del Contacto
	 */
	
	public Long getId() {
		return id_;
	}
	public void setId(Long id)
	{
		this.id_ = id;
	}
	
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
	
	public int getDiaCumpleanos() {
		return fechaCumpleanos.getDayOfMonth();
	}
	public int getMesCumpleanos() {
		return fechaCumpleanos.getMonthValue();
	}
	public int getAnyoCumpleanos() {
		return fechaCumpleanos.getYear();
	}
	
	public void setDiaCumpleanos(int dia)
	{
		this.diaCumpleanos = dia;
	}
	public void setMesCumpleanos(int mes)
	{
		this.mesCumpleanos = mes;
	}
	public void setAnyoCumpleanos(int anyo)
	{
		this.anyoCumpleanos = anyo;
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
	public LocalDate getFechaCumpleanos()
	{
		return fechaCumpleanos;
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

	public String getPassword()
	{
		return this.password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public void setFechaCumpleanos(LocalDate fechaCumpleanos)
	{
		this.fechaCumpleanos = fechaCumpleanos;
	}
	
	/**
	 * Metodo ToString
	 */
	public String toString() {
		return "Contacto [Nombre=" + nombre + " | apellido1=" + apellido1 + " | apellido2=" + apellido2 + " | email="
				+ email + " | edad=" + edad + " | Fecha_cumpleaños=" + fechaCumpleanos.getDayOfMonth()+"/"+fechaCumpleanos.getMonthValue()+"/"+fechaCumpleanos.getYear()+ " | tags= " + tags_.toString() + "]\n";
	}
	
	/**
	 * Compara un objeto de tipo Contacto con otro
	 * @param contactoAComparar
	 * @return retorna 0 si son el mismo objeto
	 */
	public int compareTo(Contacto contactoAComparar) {
		
		
		if(this.getEmail().equals(contactoAComparar.getEmail()))
		{
			
			return 0;
		}
		else
		{
			return 1;
		}
		
		
	}
	

	
	

	
}
