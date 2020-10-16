/**
 * Factoria concreta. Se encarga de generar los distintos tipos de anuncios. 
 * @author Javier Luna Carmona
 * @author Nanuel Jesus Mariscal Romero
*/

package anuncios;
public class Generador extends GeneradorDeAnuncios {
	public Anuncio creaAnuncioGeneral() {
		AnuncioGeneral nuevo = new AnuncioGeneral();
		nuevo.setTipo(Tipo.general);
		return nuevo;
	}

	public Anuncio creaAnuncioTematico() {
		AnuncioTematico nuevo = new AnuncioTematico();
		nuevo.setTipo(Tipo.tematico);
		return nuevo;
	}

	public Anuncio creaAnuncioIndividualizado() {
		AnuncioIndividualizado nuevo = new AnuncioIndividualizado();
		nuevo.setTipo(Tipo.individualizado);
		return nuevo;
	}

	public Anuncio creaAnuncioFlash() {
		AnuncioFlash nuevo = new AnuncioFlash();
		nuevo.setTipo(Tipo.flash);
		return nuevo;
	}
}