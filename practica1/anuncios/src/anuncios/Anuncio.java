package anuncios;
import java.util.Date;
import java.util.ArrayList;

public abstract class Anuncio {
	private int id_;
	private String titulo_, propietario_, cuerpo_; 
	private Date fecha_;
	private Estado estado_;

	// Constructores
	public Anuncio() {
		// generar un id a partir del ultimo anuncio
	}

	// observadores
	public int getId() { return id_; }
	public String getTitulo() { return titulo_; }
	public String getPropietario() { return propietario_; }
	public String getCuerpo() { return cuerpo_; }
	public Date getFecha() { return fecha_; }
	public Estado getEstado() { return estado_; }

	// modificadores
	public void setId(int id) { this.id_ = id; }
	public void setTitulo(String titulo) { this.titulo_ = titulo; }
	public void set(String propietario) { this.propietario_ = propietario; }
	public void setCuerpo(String cuerpo) { this.cuerpo_ = cuerpo; }
	public void setFecha(Date fecha) { this.fecha_ = fecha; }
	public void setEstado(Estado estado) { estado_ = estado; }
}
