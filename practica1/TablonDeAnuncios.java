/*
Se desea implementar un tablón de anuncios. Para ello, un Anuncio estará compuesto
de un identificador, título, usuario propietario, (0+) usuarios destinatarios y el cuerpo
del anuncio. El Tablón se encargará de mostrar todos los anuncios según el criterio de
ordenación indicado por el usuario: fecha de publicación o usuario propietario.
 */
import java.util.Date;


public class TablonDeAnuncios {
	private int id_;
	private String titulo_, propietario_, cuerpo_; 
	private Date fecha_publicacion;
	private List<String> destinatarios_; 

	public TablonDeAnuncios(int id, String titulo, String propietario, 
	String cuerpo, Date fecha) {
		this.id_ = id;
		this.titulo_ = titulo;
		this.cuerpo_ = cuerpo;
		this.propietario_ = propietario;
		this.fecha_ = fecha;
	}

	// observadores
	public int getId() { return id_; }
	public String getTitulo() { return titulo_; }
	public String getPropietario() { return propietario_; }
	public String getCuerpo() { return cuerpo_; }
	public Date getFecha() { return fecha_; }
	public List<String> getDestinatarios() { return destinatarios_; }

	// modificadores
	public void setId(int id) { id_ = id; }
	public void setTitulo(String titulo) { titulo_ = titulo; }
	public void set(String propietario) { propietario_ = propietario; }
	public void setCuerpo(String cuerpo) { cuerpo_ = cuerpo; }
	public void setFecha(Date fecha) { fecha_ = fecha; }
	public void setDestinatarios(List<String> destinatarios) { destinatarios_ = destinatarios; }

	
}
