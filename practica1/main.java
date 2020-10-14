package practica1;

import java.util.Date;

public class main {

	public static void main(String[] args) {
		
		Date fecha = new Date();
		
		

		
		GestorContactos gestorContactos = GestorContactos.getInstance();
		gestorContactos.mostrarContactos();
		gestorContactos.darAltaContacto();
		gestorContactos.darAltaContacto();
		gestorContactos.mostrarContactos();
		//gestorContactos.buscarContactoPorNombre();
		//gestorContactos.buscarContactoPorEdad();
		gestorContactos.buscarContactoPorEmail();
		
	}

}
