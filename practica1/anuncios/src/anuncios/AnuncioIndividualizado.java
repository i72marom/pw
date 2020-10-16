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
	
	
	public String toString() {
		return "Anuncio [id_=" + super.getId() + ", titulo_=" + super.getTitulo() + ", propietario_=" +super.getAutor().getNombre() + " " + super.getAutor().getApellidos() + ", cuerpo_="
				+ super.getCuerpo() + ", fecha_=" + super.getFecha() + ", estado_=" + super.getEstado() + ", destinatarios=" + destinatarios_.toString()+ "]";
	}

	// otras funciones????
	// public addDestinatario(Contacto destinatario) {}
	// public delDestinatario(Contacto destinatario) {}
}
