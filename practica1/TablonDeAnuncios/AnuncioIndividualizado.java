public class AnuncioIndividualizado extends Anuncio {
	private ArrayList<Contacto> destinatarios_;

	// constructor
	public AnuncioIndividualizado(ArrayList<Contacto> destinatarios) {
		super(
			int id, 
			String titulo, 
			String propietario, 
			String cuerpo, 
			Date fecha
		)
		this.destinatarios_ = destinatarios;
	}

	// gets y sets
	public AraryList<Contacto> getDestinatarios() { return destinatarios_; }
	public void setDestinatarios(AraryList<Contacto> destinatarios) { this.destinatarios_ = destinatarios; }

	// otras funciones????
	// public addDestinatario(Contacto destinatario) {}
	// public delDestinatario(Contacto destinatario) {}
}
