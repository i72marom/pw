package anuncios;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;



public class TablonDeAnuncios {
	private ArrayList<Anuncio> tablon_ = new ArrayList();
	
	

	
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
	
	
	public void crearAnuncio(Contacto autor)
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
			System.out.print("Titulo del anuncio : ");
			titulo = leerStrings.nextLine();
			System.out.print("Contenido : ");
			
			AnuncioGeneral a = (AnuncioGeneral) anuncio.creaAnuncioGeneral();
			
			contenido = leerStrings.nextLine();
			
			System.out.print("¿Desea publicarlo ya? (si|no) : ");
			publicar = leerStrings.nextLine();
			if(publicar.equals("si"))
			{
				a.setEstado(Estado.publicado);
			}
			else if(publicar.equals("no"))
			{
				a.setEstado(Estado.editado);
			}
			
			
			

			
			a.setCuerpo(contenido);
			a.setTitulo(titulo);
			
			a.setId(tablon_.size());
			a.setAutor(autor);
			
			System.out.println("Anuncio añadido");
			tablon_.add(a);
			
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
	
	public void publicarAnuncio(int id) {
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
			if (a.getAutor().equals(propietario)) list.add(a);
		}

		return list;
	}
	
	public void buscarPorDestinatario() {}
	
	public void listarAnuncios()
	{
		if(tablon_.size() == 0)
			System.out.println("No existe ningun anuncio");
		else
		{
			for(int i = 0;i<tablon_.size();i++)
			{
				System.out.println(tablon_.get(i).toString());
			}			
		}
		

	}
	
}
