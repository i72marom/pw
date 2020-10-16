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
	// public addTema(String tema) {}
	// public delTema(String tema) {}
}