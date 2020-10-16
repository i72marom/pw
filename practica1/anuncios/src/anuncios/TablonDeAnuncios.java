package anuncios;
import java.util.Date;
import java.util.ArrayList;

public class TablonDeAnuncios {
	private ArrayList<Anuncio> tablon_;
	 
	public TablonDeAnuncios() {}

	// observadores y modificadores
	public ArrayList<Anuncio> getTablon() { 
		return tablon_; 
	}
	public void setTablon(ArrayList<Anuncio> tablon) { 
		this.tablon_ = tablon; 
	}

	// otras funciones
	public void editarAnuncio(Anuncio nuevo_anuncio, Anuncio viejo_anuncio) {
		nuevo_anuncio.setId(viejo_anuncio.getId());
		tablon_.set(nuevo_anuncio.getId(), nuevo_anuncio);
	}
	
	public void guardarAnuncio() {}

	public void publicarAnuncio(Anuncio nuevo_anuncio) {
		tablon_.add(nuevo_anuncio);
	}
	
	public void archivarAnuncio(int id) {
		tablon_.get(id).setEstado(Estado.archivado);
	}
	
	public ArrayList<Anuncio> buscarPorFecha(Date fecha) {
		ArrayList<Anuncio> list = new ArrayList<Anuncio>;

		for (Anuncio a :tablon_) {
			if (a.getFecha() == fecha) list.add(a);
		}

		return list;
	}
	
	public void buscarPorTema() {}
	
	public ArrayList<Anuncio> buscarPorPropietario(Contacto propietario) {
		ArrayList<Anuncio> list = new ArrayList<Anuncio>;

		for (Anuncio a :tablon_) {
			if (a.getPropietario().equals(propietario) == propietario) list.add(a);
		}

		return list;
	}
	
	public void buscarPorDestinatario() {}
}
