public class Generador extends GeneradorDeAnuncios {
	public abstract Anuncio creaAnuncioGeneral() {
		return new AnuncioGeneal();
	}

	public abstract Anuncio creaAnuncioTematico() {
		return new AnuncioTematico();
	}

	public abstract Anuncio creaAnuncioIndividualizado() {
		return new AnuncioIndividualizado();
	}

	public abstract Anuncio creaAnuncioFlash() {
		return new AnuncioFlash();
	}
}