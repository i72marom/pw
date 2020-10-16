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

	// otras funciones????
	public addTema(String tema) { this.temas_.add(tema); }
	public delTema(String tema) { this.temas_.remove(tema); }

	public String toString() {
		return "Anuncio [id_=" + super.getId() + ", titulo_=" + super.getTitulo() + ", propietario_=" +super.getAutor().getNombre() + " " + super.getAutor().getApellidos() + ", cuerpo_="
				+ super.getCuerpo() + ", fecha_=" + super.getFecha() + ", estado_=" + super.getEstado() + ", temas=" + temas_.toString() +  "]";
	}
}