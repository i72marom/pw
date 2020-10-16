package anuncios;
import java.util.ArrayList;

public class AnuncioIndividualizado extends Anuncio {
	private ArrayList<Contacto> destinatarios_;

	// constructor
	public AnuncioIndividualizado() {
		super();
	}

	// gets y sets
	public ArrayList<Contacto> getDestinatarios() { 
		return destinatarios_; 
		}
	
	public void setDestinatarios(ArrayList<Contacto> destinatarios) { 
		this.destinatarios_ = destinatarios; 
		}

	// otras funciones????
	// public addDestinatario(Contacto destinatario) {}
	// public delDestinatario(Contacto destinatario) {}
}
