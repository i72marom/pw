package es.uco.pw.data.dao;


import java.time.LocalDate;
import java.util.ArrayList;

import es.uco.pw.business.anuncio.Anuncio;
/**
 * Interfaz que extiende de la interfaz DAO y añade funcionalidad a un DAO sobre la clase Anuncio
 * @author Javi y Manu
 *
 */
public interface AnuncioDAO extends DAO<Anuncio, Long>{
	
	ArrayList<Anuncio> obtenerPorIdAutor(Long idAutor) throws DAOException;
	void agregarDestinatario(Long idDestinatario, Long idAnuncio) throws DAOException;
	void borrarDestinatarios(Long idAnuncio) throws DAOException;
	void modificarTags(Long idAnuncio, ArrayList<String> tags_) throws DAOException;
	void quitarDestinatario(Long idDestinatario, Long idAnuncio) throws DAOException;
	void updateFechas(LocalDate fechaInicio, LocalDate fechaFin, Long idAnuncio) throws DAOException;


	
}
