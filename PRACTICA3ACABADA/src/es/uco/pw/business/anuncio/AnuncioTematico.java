package es.uco.pw.business.anuncio;

import java.util.ArrayList;

/**
 * Anuncio Temático extiende de Anuncio y es un tipo de anuncio que tendrá una serie de temas y sólo aparecerá en el tablón a los usuarios que pertenezcan a esos tags.
 * @author Javi y Manu
 *
 */
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
	public void agregarTema(String tema)
	{
		this.temas_.add(tema);
	}
	public void quitarTema(String tema)
	{
		this.temas_.remove(tema);
	}
	public String toString() {
		return "Anuncio [id_=" + super.getId() + ", titulo_=" + super.getTitulo() + ", propietario_=" +super.getAutor().getNombre() + " " + super.getAutor().getApellidos() + ", cuerpo_="
				+ super.getCuerpo() + ", fecha_=" + super.getFecha() + ", estado_=" + super.getEstado() + ", temas=" + temas_.toString() +  "]";
	}
	// otras funciones????
	// public addTema(String tema) {}
	// public delTema(String tema) {}
}