import java.util.Date;
import java.util.ArrayList;

public class TablonDeAnuncios {
	private ArrayList<Anuncio> tablon_;
	 
	public TablonDeAnuncios() {}

	// observadores y modificadores
	public void getTablon() { return tablon_; }
	public void setTablon(ArrayList<Anuncio> tablon) { this.tablon_ = tablon; }

	// otras funciones
	public void editarAnuncio() {}
	
	public void guardarAnuncio() {}
	
	public void publicarAnuncio() {}
	
	public void archivarAnuncio(int id) {
		tablon_.get(id).setEstado(archivado);
	}
	
	public void buscarPorFecha() {}
	
	public void buscarPorTema() {}
	
	public void buscarPorPropietario() {}
	
	public void buscarPorDestinatario() {}
}
