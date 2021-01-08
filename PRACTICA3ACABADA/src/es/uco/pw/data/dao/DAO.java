package es.uco.pw.data.dao;


import java.util.ArrayList;
/**
 * Interfaz genérica que añade la funcionalidad básica de un DAO para un Template<T,K> siendo T el valor y K la key
 * @author Javi y Manu
 *
 * @param <T>
 * @param <K>
 */
public interface DAO<T, K> {

	void insertar(T a) throws DAOException;
	
	void borrar(T a) throws DAOException;
	
	void Modificar(T a) throws DAOException;
	
	T obtener(K id) throws DAOException;
	
	ArrayList<T> obtenerTodos() throws DAOException;
	
	
}
