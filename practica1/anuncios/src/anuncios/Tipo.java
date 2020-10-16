package anuncios;

public enum Tipo {
	general,
	flash,
	tematico,
	individualizado
}


public ArrayList<Anuncio> buscarPorDestinatario(Contacto c) {
	ArrayList<Anuncio> anuncios;

	for (Anuncio a : this.tablon_) {
		if (a.getTipo() == Tipo.individualizado && a.existeContacto(c)) {
			anuncios.add(a);
		}
	}

	return anuncios;
}
