package anuncios;
public class Generador extends GeneradorDeAnuncios {
	public Anuncio creaAnuncioGeneral() {
		return new AnuncioGeneral();
	}

	public Anuncio creaAnuncioTematico() {
		return new AnuncioTematico();
	}

	public Anuncio creaAnuncioIndividualizado() {
		return new AnuncioIndividualizado();
	}

	public Anuncio creaAnuncioFlash() {
		return new AnuncioFlash();
	}
}