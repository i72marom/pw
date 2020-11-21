package es.uco.pw.business.anuncio;
import java.util.Date;

import es.uco.pw.business.contacto.Contacto;
import es.uco.pw.business.tipos.Estado;
import es.uco.pw.business.tipos.Tipo;


public abstract class Anuncio {
	private Long id_, id_autor_;
	private String titulo_, cuerpo_; 
	private Contacto autor_;
	private Date fecha_;
	private Tipo tipo_;
	private Estado estado_;

	// Constructores
	public Anuncio() {}

	// observadores
	public Long getId() { return id_; }
	public Long getIdAutor() { return id_autor_; }
	public String getTitulo() { return titulo_; }
	public Contacto getAutor() { return autor_; }
	public String getCuerpo() { return cuerpo_; }
	public Date getFecha() { return fecha_; }
	public Estado getEstado() { return estado_; }
	public Tipo getTipo() { return tipo_; }

	// modificadores
	public void setId(Long id) { this.id_ = id; }
	public void setIdAutor(Long id) { this.id_autor_ = id; } 
	public void setTitulo(String titulo) { this.titulo_ = titulo; }
	public void setAutor(Contacto autor) { this.autor_ = autor; }
	public void setCuerpo(String cuerpo) { this.cuerpo_ = cuerpo; }
	public void setFecha(Date fecha) { this.fecha_ = fecha; }
	public void setEstado(Estado estado) { this.estado_ = estado; }
	public void setTipo(Tipo tipo) { this.tipo_ = tipo; }

	@Override
	public String toString() {
		return "Anuncio [id_=" + id_ +  ", titulo_=" + titulo_ + ", cuerpo_=" + cuerpo_ + ", autor_=" + autor_.getNombre()+" "+autor_.getApellidos()
				+ ", fecha_=" + fecha_ + ", tipo_=" + tipo_ + ", estado_=" + estado_ + "]";
	}
	
	
	
	
}
