package es.uco.pw.data.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import es.uco.pw.business.anuncio.Anuncio;

public interface AnuncioDAO extends DAO<Anuncio, Long>{
	
	ArrayList<Anuncio> obtenerPorIdAutor(Long idAutor) throws DAOException;
	void agregarDestinatario(Long idDestinatario, Long idAnuncio) throws DAOException;
	void quitarDestinatario(Long idDestinatario, Long idAnuncio) throws DAOException;
	void updateFechas(LocalDate fechaInicio, LocalDate fechaFin, Long idAnuncio) throws DAOException;


	
}
