package es.uco.pw.business.contacto;


import java.sql.SQLException;

import java.util.ArrayList;



import es.uco.pw.data.dao.DAOException;
import es.uco.pw.data.mysqldao.MySQLDAOManager;


/**
 * Clase que gestiona todo lo relacionado con los Contactos a trav�s de un MySQLDAOManager ( que es un manager de los DAOS de la aplicaci�n )
 * @author Javi | Manu
 *
 */
public class GestorContactos {

	private static GestorContactos instance = null;
	
	
	

	
	//private static ArrayList<Contacto> contactos_ = new ArrayList();
	private String[] tags_disponibles_;
	
	
	private boolean logged_;

	private Contacto contacto_logeado_;
	

	
	/**
	 * Constructor privado de la clase GestorContactos
	 */
	private GestorContactos() {
		contacto_logeado_ = null;
		logged_ = false;
		
	}
	/**
	 * Constructor publico de la clase GestorContactos implementado mediante el patr�n de dise�o Singleton.
	 * @return GestorContactos
	 */
	public static GestorContactos getInstance() {
		if(instance == null) {
			instance = new GestorContactos();
		}
		return instance;
	}
	/**
	 * Getter de la variable booleana logged_
	 * @return logged_
	 */
	public boolean getLogged()
	{
		return logged_;
	}
	/**
	 * Setter de la variable booleana logged_
	 * @param logged
	 */
	public void setLogged(boolean logged)
	{
		this.logged_ = logged;
	}
	
	
	/**
	 * Funci�n del men� para crear un nuevo usuario en la base de datos.
	 * @param manager
	 * @return
	 * @throws DAOException
	 */

	public void modificarTags(Contacto userLogged, ArrayList<String> tags) throws ClassNotFoundException, SQLException, DAOException
	{
		userLogged.setTags(tags);
		MySQLDAOManager manager = MySQLDAOManager.getInstance();
		
		manager.getContactoDAO().Modificar(userLogged);
		
	}
	
	
	
	/**
	 * Obtiene el objeto Contacto que est� logeado actualmente.
	 * @return Contacto
	 */
	public Contacto getUserLogeado()
	{
		return contacto_logeado_;
	}
	/**
	 * Devuelve la lista de tags disponibles en el sistema.
	 * @return
	 */
	public String[] get_tag_disponibles()
	{
		return tags_disponibles_;
	}
	/**
	 * Funci�n del men� para visualizar los datos del usuario logeado.
	 * @param contacto
	 */
	
	public int existeContacto(Contacto contacto, MySQLDAOManager manager) throws DAOException {
		
		int resultado = -1;
		
		ArrayList<Contacto> contactos = manager.getContactoDAO().obtenerTodos();
		
		
		for(int i = 0;i<contactos.size() && resultado == -1 ;i++)
		{
			if(contacto.compareTo(contactos.get(i)) == 0)
			{
				resultado = i;
			}
		}
		return resultado;
	}
	

	/**
	 * Devuelve si un usuario tiene seleccionado cierto tag o no
	 * @param userLogeado
	 * @param tag
	 * @return
	 */
	public boolean perteneceAlTag(Contacto userLogeado, String tag)
	{
		boolean resultado = false;
		
		ArrayList<String> tagsUser = userLogeado.getTags();
		
		for(int i = 0;i<tagsUser.size() && !resultado; i++)
		{
			if(tagsUser.get(i).toLowerCase().equals(tag.toLowerCase()))
			{
				resultado = true;
			}
		}
		
		
		return resultado;
	}
	
	/**
	 * Devuelve si un tag pertenece a los disponibles o no
	 * @param tag
	 * @return
	 */
	private boolean perteneceTagsDisponibles(String tag) {
		boolean resultado = false;
		
		for(int i = 0;i<tags_disponibles_.length && !resultado;i++)
		{

			if(tags_disponibles_[i].replace(" ","").equals(tag))
			{

				resultado = true;
			}
		}

		return resultado;
	}
	
	

	
	
	
	
	

}
