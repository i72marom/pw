import java.util.Date;
import java.util.ArrayList;

public class TablonDeAnuncios {
	private int id_;
	private String titulo_, propietario_, cuerpo_; 
	private Date fecha_publicacion;
	private ArrayList<String> destinatarios_;
	 

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
	public AraryList<String> getDestinatarios() { return destinatarios_; }

	// modificadores
	public void setId(int id) { this.id_ = id; }
	public void setTitulo(String titulo) { this.titulo_ = titulo; }
	public void set(String propietario) { this.propietario_ = propietario; }
	public void setCuerpo(String cuerpo) { this.cuerpo_ = cuerpo; }
	public void setFecha(Date fecha) { this.fecha_ = fecha; }
	public void setDestinatarios(AraryList<String> destinatarios) { this.destinatarios_ = destinatarios; }
}
