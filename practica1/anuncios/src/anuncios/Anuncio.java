/**
 * Clase Anuncio. compuesto de un identificador, título, usuario propietario, 
 * (0+) usuarios destinatarios y el cuerpo del anuncio. 
 * En el tablón se podrán generar 4 tipos de anuncios:
 * - Anuncio general.
 * - Anuncio temático.
 * - Anuncio individualizado.
 * - Anuncio flash.
 * @author Javier Luna Carmona
 * @author Nanuel Jesus Mariscal Romero
*/

package anuncios;
import java.util.Date;
import java.util.ArrayList;

public abstract class Anuncio {
	private int id_;
	private String titulo_, cuerpo_; 
	private Contacto autor_;
	private Date fecha_;
	private Tipo tipo_;
	private Estado estado_;

	// Constructores
	public Anuncio() {}

	// observadores
	public int getId() { return id_; }
	public String getTitulo() { return titulo_; }
	public Contacto getAutor() { return autor_; }
	public String getCuerpo() { return cuerpo_; }
	public Date getFecha() { return fecha_; }
	public Estado getEstado() { return estado_; }
	public Tipo getTipo() { return tipo_; }

	// modificadores
	public void setId(int id) { this.id_ = id; }
	public void setTitulo(String titulo) { this.titulo_ = titulo; }
	public void setAutor(Contacto autor) { this.autor_ = autor; }
	public void setCuerpo(String cuerpo) { this.cuerpo_ = cuerpo; }
	public void setFecha(Date fecha) { this.fecha_ = fecha; }
	public void setEstado(Estado estado) { this.estado_ = estado; }
	public void setTipo(Tipo tipo) { this.tipo_ = tipo; }

	@Override
	public String toString() {
		return "Anuncio [id_=" + id_ + ", titulo_=" + titulo_ + ", cuerpo_=" + cuerpo_ + ", autor_=" + autor_.getNombre()+" "+autor_.getApellidos()
				+ ", fecha_=" + fecha_ + ", tipo_=" + tipo_ + ", estado_=" + estado_ + "]";
	}
}
