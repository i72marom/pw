package es.uco.pw.business.anuncio;

import es.uco.pw.business.tipos.Tipo;


/**
 * Clase que pretende crear un generador de anuncios, siguiendo el patrón Abstract Factory
 * @author Javi y Manu
 *
 */
public class Generador extends GeneradorDeAnuncios {
	/**
	 * Crea un anuncio general
	 */
	public Anuncio creaAnuncioGeneral() {
		AnuncioGeneral nuevo = new AnuncioGeneral();
		nuevo.setTipo(Tipo.general);
		return nuevo;
	}
	/**
	 * Crea un anuncio tematico
	 */
	public Anuncio creaAnuncioTematico() {
		AnuncioTematico nuevo = new AnuncioTematico();
		nuevo.setTipo(Tipo.tematico);
		return nuevo;
	}

	/**
	 * Crea un anuncio individualizado
	 */
	public Anuncio creaAnuncioIndividualizado() {
		AnuncioIndividualizado nuevo = new AnuncioIndividualizado();
		nuevo.setTipo(Tipo.individualizado);
		return nuevo;
	}

	/**
	 * Crea un anuncio flash
	 */
	public Anuncio creaAnuncioFlash() {
		AnuncioFlash nuevo = new AnuncioFlash();
		nuevo.setTipo(Tipo.flash);
		return nuevo;
	}
}