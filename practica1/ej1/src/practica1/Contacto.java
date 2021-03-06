package practica1;

import java.util.ArrayList;
import java.util.Date;

public class Contacto {
	
	private String nombre, apellido1, apellido2, email;
	private int edad;
	private Date d;
	private ArrayList<String> tags_;
	
	
	
	public Contacto(String nombre, String apellido1, String apellido2, int edad, String email, Date d, ArrayList<String> tags) {
		
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.email = email;
		this.edad = edad;
		this.d = d;
		this.tags_ = tags;
		
	}
	
	

	public Contacto(String nombre, String apellido1, String apellido2) {
		
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.email = "";
		this.edad = 0;
		this.d = d;
		tags_ = null;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	
	public String getApellidos() {
		return apellido1 + " " + apellido2;
	}
	
	public void setApellidos(String apellido1, String apellido2) {
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getD() {
		return d;
	}

	public void setD(Date d) {
		this.d = d;
	}
	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public ArrayList<String> getTags() {
		return tags_;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags_ = tags;
	}
	public void agregarTag(String tag)
	{
		this.tags_.add(tag);
	}
	public void quitarTag(String tag)
	{
		this.tags_.remove(tag);
	}

	@Override
	public String toString() {
		return "Contacto [Nombre=" + nombre + " | apellido1=" + apellido1 + " | apellido2=" + apellido2 + " | email="
				+ email + " | edad=" + edad + " | d=" + d + " | tags= " + tags_.toString() + "]\n";
	}
	
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
