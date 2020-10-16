package anuncios;
import java.util.Date;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;



public class TablonDeAnuncios {
	private ArrayList<Anuncio> tablon_ = new ArrayList();
	
	

	
	Scanner leerStrings = new Scanner(System.in);
	Scanner leerInts = new Scanner(System.in);
	
	Generador anuncio = new Generador();
	 
	public TablonDeAnuncios() {
		
		
	}

	// observadores y modificadores
	public void guardarTablon() { 
		
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("files" + File.separator + "tablon.txt");
            pw = new PrintWriter(fichero);

            for (int i = 0; i<tablon_.size(); i++)
            {
            	  pw.print(tablon_.get(i).getTitulo() + "| " + tablon_.get(i).getAutor().getNombre() + "| " + tablon_.get(i).getAutor().getApellido1() + "| "+ tablon_.get(i).getAutor().getApellido2() + "| " +tablon_.get(i).getCuerpo()+ "| " + tablon_.get(i).getEstado() + "| " + tablon_.get(i).getTipo() + "| " + tablon_.get(i).getFecha());
            	  pw.println();
            }
              

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
		
		
	}
	public void cargarTablon(GestorContactos gestor) { 
	
	}

	// otras funciones
	public void editarAnuncio(int id, Contacto autor) {
		
		String titulo, contenido, estado;
		Tipo tipo;
		Date date = new Date();
		
		System.out.println("ANUNCIO A MODIFICAR : ");
		System.out.println(tablon_.get(id).toString());
		
		
		Anuncio a = tablon_.get(id);
		System.out.print("Titulo del anuncio : ");
		titulo = leerStrings.nextLine();
		System.out.print("Contenido : ");		
		contenido = leerStrings.nextLine();
		System.out.print("Estado (publicado|archivado|editado) : ");
		estado = leerStrings.nextLine();
		if(estado.equals("publicado"))
			a.setEstado(Estado.publicado);
		if(estado.equals("archivado"))
			a.setEstado(Estado.archivado);
		if(estado.equals("editado"))
			a.setEstado(Estado.editado);
		
		
		
		
		a.setCuerpo(contenido);
		a.setTitulo(titulo);
		a.setFecha(date);
		a.setAutor(autor);
		tablon_.set(id, a);
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
			a.setFecha(date);
			a.setAutor(autor);
			a.setTipo(Tipo.general);
			
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
	
	public ArrayList<String> buscarPorPropietario(Contacto propietario) {
		ArrayList<String> list = new ArrayList();

		for (int i = 0;i<tablon_.size();i++) {
			if (tablon_.get(i).getAutor().equals(propietario)) list.add("ID : " +  i + " | " + tablon_.get(i).toString());
		}

		return list;
	}
	
	public void buscarPorDestinatario() {}
	
	public void listarAnuncios(Contacto contacto)
	{
		int numA = 0;
		System.out.println("Lista de anuncios : ");
		for(int i = 0;i<tablon_.size();i++)
		{
				if(tablon_.get(i).getEstado().equals(Estado.publicado))
				{
					System.out.println(tablon_.get(i).toString());
					numA++;
				}				
		}
						
		if(numA == 0)
			System.out.println("No hay anuncios para mostrar.\n");
	}
		
		
	
}
