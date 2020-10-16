/**
 * Clase Anuncio flash. muestra en el tablón restringido entre una fecha/hora de
 * inicio y una fecha/hora final.
 * @author Javier Luna Carmona
 * @author Nanuel Jesus Mariscal Romero
*/

package anuncios;
import java.util.Date;

public class AnuncioFlash extends Anuncio {
	private Date fecha_inicio_;
	private Date fecha_fin_;

	// constructores
	public AnuncioFlash() {
		super();
	}

	// Ovservadores
	public Date getFechaInicio() { return this.fecha_inicio_; }
	public Date getFechaFin() { return this.fecha_fin_; }

	// Modificadores
	public void setFechaInicio(Date fecha_inicio) { this.fecha_inicio_ = fecha_inicio; }
	public void setFechaFin(Date fecha_fin) { this.fecha_fin_ = fecha_fin; }

	@Override
	public String toString() {
		return "Anuncio [titulo_=" + super.getTitulo() + ", propietario_=" +super.getAutor().getNombre() + " " + super.getAutor().getApellidos() + ", cuerpo_="
				+ super.getCuerpo() + ", fecha_=" + super.getFecha() + ", estado_=" + super.getEstado() + ", fecha_inicio_=" + fecha_inicio_.toString() + 
				", fecha_fin_" + fecha_fin_.toString() + "]";
	}
	
	
	
}