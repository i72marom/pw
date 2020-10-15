import java.util.ArrayList;

public abstract class GeneradorDeAnuncio {
	/** 
	 * Crea un anuncio general.
	 * @return Anuncio.
	*/
	public abstract Anuncio creaAnuncioGeneral();

	/** 
	 * Crea un anuncio tematico.
	 * @return Anuncio.
	*/
	public abstract Anuncio creaAnuncioTematico();

	/** 
	 * Crea un anuncio que podran ver varios usuarios.
	 * @return Anuncio.
	*/
	public abstract Anuncio creaAnuncioIndividualizado();

	/** 
	 * Crea un anuncio de duración limitada.
	 * @return Anuncio.
	*/
	public abstract Anuncio creaAnuncioFlash();
	
}