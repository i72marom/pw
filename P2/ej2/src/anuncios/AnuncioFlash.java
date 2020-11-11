package anuncios;
import java.util.Date;
import java.util.Calendar;

public class AnuncioFlash extends Anuncio {
	private int diaInicio_, mesInicio_, anyoInicio_;
	private int diaFin_, mesFin_, anyoFin_;

	// constructores
	public AnuncioFlash() {
		super();
	}

	// Ovservadores
	
	
	public int getDiaInicio() { return this.diaInicio_; }
	public int getMesInicio() { return this.mesInicio_; }
	public int getAnyoInicio() { return this.anyoInicio_; }
	public int getDiaFin() { return this.diaFin_; }
	public int getMesFin() { return this.mesFin_; }
	public int getAnyoFin() { return this.anyoFin_; }

	// Modificadores
	
	public void setDiaInicio(int dia) {
		this.diaInicio_ = dia;
	}
	public void setMesInicio(int mes) {
		this.mesInicio_ = mes;
	}
	public void setAnyoInicio(int anyo) {
		this.anyoInicio_ = anyo;
	}
	
	public void setDiaFin(int dia) {
		this.diaFin_ = dia;
	}
	public void setMesFin(int mes) {
		this.mesFin_ = mes;
	}
	public void setAnyoFin(int anyo) {
		this.anyoFin_ = anyo;
	}
	
	

	@Override
	public String toString() {
		return "Anuncio [id_=" + super.getId() + ", titulo_=" + super.getTitulo() + ", propietario_=" +super.getAutor().getNombre() + " " + super.getAutor().getApellidos() + ", cuerpo_="
				+ super.getCuerpo() + ", fecha_=" + super.getFecha() + ", estado_=" + super.getEstado() + ", fecha_inicio_=" + diaInicio_+"/"+mesInicio_+"/"+anyoInicio_ + 
				", fecha_fin_" + diaFin_+"/"+mesFin_+"/"+anyoFin_ + "]";
	}
	
	
	
}