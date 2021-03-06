package es.uco.pw.business.anuncio;
import java.time.LocalDate;

public class AnuncioFlash extends Anuncio {
	private LocalDate fechaInicio_, fechaFin_;

	// constructores
	public AnuncioFlash() {
		super();
	}

	// Ovservadores
	
	public LocalDate getFechaInicio() { return this.fechaInicio_; }
	public LocalDate getFechaFin() { return this.fechaFin_; }
	public int getDiaInicio() { return this.fechaInicio_.getDayOfMonth(); }
	public int getMesInicio() { return this.fechaInicio_.getMonthValue(); }
	public int getAnyoInicio() { return this.fechaInicio_.getYear(); }
	public int getDiaFin() { return this.fechaFin_.getDayOfMonth(); }
	public int getMesFin() { return this.fechaFin_.getMonthValue(); }
	public int getAnyoFin() { return this.fechaFin_.getYear(); }

	// Modificadores
	
	
	public void setFechaInicio(LocalDate fechaInicio)
	{
		this.fechaInicio_ = fechaInicio;
	}
	public void setFechaFin(LocalDate fechaFin)
	{
		this.fechaFin_ = fechaFin;
	}
	
	

	@Override
	public String toString() {
		return "Anuncio [id_=" + super.getId() + ", titulo_=" + super.getTitulo() + ", propietario_=" +super.getAutor().getNombre() + " " + super.getAutor().getApellidos() + ", cuerpo_="
				+ super.getCuerpo() + ", fecha_=" + super.getFecha() + ", estado_=" + super.getEstado() + ", fecha_inicio_=" + fechaInicio_.getDayOfMonth()+"/"+fechaInicio_.getMonthValue()+"/"+fechaInicio_.getYear() + 
				", fecha_fin_" + fechaFin_.getDayOfMonth()+"/"+fechaFin_.getMonthValue()+"/"+fechaFin_.getYear()+ "]";
	}
	
	
	
}