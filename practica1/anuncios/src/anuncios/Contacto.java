/**
 * Cada Contacto mantiene información acerca de su nombre, apellidos, fecha 
 * de nacimiento, email (único) y un conjunto de intereses que identifican al 
 * usuario. Estos intereses son asignados mediante sistema de tagging. 
 * @author Javier Luna Carmona
 * @author Nanuel Jesus Mariscal Romero
*/

package anuncios;

import java.util.ArrayList;
import java.util.Date;



public class Contacto {
	
	private String nombre, apellido1, apellido2, email;
	private int edad;
	private Date d;
	private ArrayList<String> tags_;
	
	
	/**
	 * Constructor de la clase contacto.
	 * @param  nombre    Nombre del contacto.
	 * @param  apellido1 Primer apellido.
	 * @param  apellido2 Segundo apellido.
	 * @param  edad      Edad del contacto.
	 * @param  email     Correo electronico.
	 * @param  d         Fecha de nacimiento.
	 * @param  tags      Intereses del usuario.
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
	 * Constructor de la clase contacto.
	 * @param  nombre    Nombre del contacto.
	 * @param  apellido1 Primer apellido
	 * @param  apellido2 Segundo apellido.
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
	
	// Observadores
	public String getNombre() { return nombre; }
	public String getApellido1() { return apellido1; }
	public String getApellido2() { return apellido2; }
	public String getApellidos() { return apellido1 + " " + apellido2; }
	public String getEmail() { return email; }
	public Date getD() { return d; }
	public int getEdad() { return edad; }

	// Modificadores
	public void setNombre(String nombre) { this.nombre = nombre; }
	public void setApellido1(String apellido1) { this.apellido1 = apellido1; }
	public void setApellido2(String apellido2) { this.apellido2 = apellido2; }
	public void setD(Date d) { this.d = d; }
	public void setEmail(String email) { this.email = email; }
	public void setEdad(int edad) { this.edad = edad; }
	public ArrayList<String> getTags() { return tags_; }
	public void setTags(ArrayList<String> tags) { this.tags_ = tags; }
	public void setApellidos(String apellido1, String apellido2) {
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
	}

	/**
	 * Añade un tema a los intereses del usuario.
	 * @param tag Tema.
	 */
	public void agregarTag(String tag) { this.tags_.add(tag); }
	
	/**
	 * Elimina un tema de los intereses del usuario.
	 * @param tag Tema.
	 */
	public void quitarTag(String tag) { this.tags_.remove(tag); }

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
		if ((this.getNombre().equals(contactoAComparar.getNombre()) 
			&& this.getApellidos().equals(contactoAComparar.getApellidos())) 
			|| this.getEmail().equals(contactoAComparar.getEmail())
			) {
			return 0;
		} else {
			return 1;
		}
	}
}
