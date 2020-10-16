/**
 * Anuncio tematico. Tiene asociado uno o mas temas de interes. 
 * @author Javier Luna Carmona
 * @author Nanuel Jesus Mariscal Romero
*/

package anuncios;

import java.util.ArrayList;

public class AnuncioTematico extends Anuncio {
	private ArrayList<String> temas_;

	// Constructores
	public AnuncioTematico() {
		super();
	}

	// gets y sets
	public ArrayList<String> getTemas() { 
		return this.temas_; 
	}
	
	public void setTemas(ArrayList<String> temas) { 
		this.temas_ = temas; 
	}
<<<<<<< HEAD
=======

	// otras funciones
	public addTema(String tema) { this.temas_.add(tema); }
	public delTema(String tema) { this.temas_.remove(tema); }

>>>>>>> rama_mmr
	public String toString() {
		return "Anuncio [titulo_=" + super.getTitulo() + ", propietario_=" +super.getAutor().getNombre() + " " + super.getAutor().getApellidos() + ", cuerpo_="
				+ super.getCuerpo() + ", fecha_=" + super.getFecha() + ", estado_=" + super.getEstado() + ", temas=" + temas_.toString() +  "]";
	}
	// otras funciones????
	// public addTema(String tema) {}
	// public delTema(String tema) {}
}