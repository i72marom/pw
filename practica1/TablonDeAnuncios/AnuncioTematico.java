public class AnuncioTematico extends Anuncio {
	private ArrayList<String> temas_;

	// Constructores
	public AnuncioTematico(ArrayList<String> temas) {
		super(
			int id, 
			String titulo, 
			String propietario, 
			String cuerpo, 
			Date fecha
		)
		this.temas_ = temas;
	}

	// gets y sets
	public ArrayList<String> getTemas() { return this.temas_; }
	public setTemas(ArrayList<String> temas) { this.temas_ = temas; }

	// otras funciones????
	// public addTema(String tema) {}
	// public delTema(String tema) {}
}