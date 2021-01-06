package es.uco.pw.business.anuncio;
import java.util.ArrayList;
/**
 * Anuncio Individualizado extiende de Anuncio y es un tipo de anuncio que se envía solo a ciertos destinatarios
 * @author Javi y Manu
 *
 */
public class AnuncioIndividualizado extends Anuncio {
	private ArrayList<Long> destinatarios_;

	// constructor
	public AnuncioIndividualizado() {
		super();
	}

	// gets y sets
	public ArrayList<Long> getDestinatarios() { 
		return destinatarios_; 
		}
	
	public void setDestinatarios(ArrayList<Long> destinatarios) { 
		this.destinatarios_ = destinatarios; 
		}
	public void agregarDestinatario(Long destinatario)
	{
		this.destinatarios_.add(destinatario);
	}
	public void quitarDestinatario(Long destinatario)
	{
		this.destinatarios_.remove(destinatario);
	}
	
	
	public String toString() {
		return "Anuncio [id_=" + super.getId() + ", titulo_=" + super.getTitulo() + ", propietario_=" +super.getAutor().getNombre() + " " + super.getAutor().getApellidos() + ", cuerpo_="
				+ super.getCuerpo() + ", fecha_=" + super.getFecha() + ", estado_=" + super.getEstado() + ", destinatarios=" + destinatarios_.toString()+ "]";
	}

	// otras funciones????
	// public addDestinatario(Contacto destinatario) {}
	// public delDestinatario(Contacto destinatario) {}
}
