package anuncios;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;



public class TablonDeAnuncios {
	private ArrayList<Anuncio> tablon_;
	

	
	Scanner leerStrings = new Scanner(System.in);
	Scanner leerInts = new Scanner(System.in);
	
	Generador anuncio = new Generador();
	 
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
	
<<<<<<< HEAD
	
	public void crearAnuncio()
	{
		String titulo, contenido;
		String publicar;
		Date date = new Date();
		System.out.println("Que tipo de anuncio quieres crear : ");
		System.out.println("1- General");
		System.out.println("2- Flash");
		System.out.println("3- Individualizado");
		System.out.println("4- Tematico");
		System.out.print("Elige uno : ");
		int tipo = leerInts.nextInt();
		
		if(tipo == 1)
		{
			//pedir datos del anuncio
			

			
			System.out.print("¿Desea publicarlo ya? (si|no) : ");
			publicar = leerStrings.nextLine();
			if(publicar.equals("si"))
			{
				Estado estado = Estado.publicado;
			}
			else if(publicar.equals("no"))
			{
				Estado estado = Estado.en_espera;
			}
			
			
			
			AnuncioGeneral a = (AnuncioGeneral) anuncio.creaAnuncioGeneral();
			
		}
		else if(tipo == 2)
		{
			//pedir datos del anuncio +fechas
		}
		else if(tipo == 3)
		{
			//pedir datos del anuncio +destinatarios
		}
		else if(tipo == 4)
		{
			//pedir datos del anuncio +tema
		}
		
	}
	
	
	public void guardarAnuncio() {}

=======
>>>>>>> rama_mmr
	public void publicarAnuncio(Anuncio nuevo_anuncio) {
		tablon_.get(id).setEstado(Estado.publicado);
	}

	public void guardarAnuncio(Anuncio nuevo_anuncio) {
		tablon_.add(nuevo_anuncio);
	}
	
	public void archivarAnuncio(int id) {
		tablon_.get(id).setEstado(Estado.archivado);
	}
	
	public ArrayList<Anuncio> buscarPorFecha(Date fecha) {
		ArrayList<Anuncio> list = new ArrayList();

		for (Anuncio a :tablon_) {
			if (a.getFecha() == fecha) list.add(a);
		}

		return list;
	}
	
	public void buscarPorTema(ArrayList<String> temas_usuairo) {
		
	}
	
	public ArrayList<Anuncio> buscarPorPropietario(Contacto propietario) {
		ArrayList<Anuncio> list = new ArrayList();

		for (Anuncio a :tablon_) {
			if (a.getPropietario().equals(propietario) == propietario) list.add(a);
		}

		return list;
	}
	
	public void buscarPorDestinatario() {}
}
