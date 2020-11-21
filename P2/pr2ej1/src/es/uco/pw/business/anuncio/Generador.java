package es.uco.pw.business.anuncio;

import es.uco.pw.business.tipos.Tipo;

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