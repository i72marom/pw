/**
 * 
 */


function mostrarConfirmacion()
{
	if(confirm("¿Seguro que deseas eliminar el anuncio?"))
	{
		document.getElementById("formEliminar").submit();
	}
	else
	{
		return false;
	}
	
	
	
}