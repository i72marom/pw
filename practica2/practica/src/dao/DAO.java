package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DAO<T, K> {

	void insertar(T a) throws DAOException;
	
	void borrar(T a) throws DAOException;
	
	void Modificar(T a) throws DAOException;
	
	T obtener(K id) throws DAOException;
	
	ArrayList<T> obtenerTodos() throws DAOException;
	
	
}
