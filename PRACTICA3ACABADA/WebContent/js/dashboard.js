/**
 * 
 */




//FUNCIONES DE CONFIRMACIÓN

function hacerAdmin(id)
{
	
	document.getElementById("idUser").value = id;	
	document.getElementById("accion").value = "hacerAdmin";
	
	if(confirm("¿Seguro que desea hacerlo moderador?"))
		document.getElementById("formHacerAdmin").submit();
}
function quitarAdmin(id)
{
	
	document.getElementById("idUser").value = id;	
	document.getElementById("accion").value = "quitarAdmin";
	
	if(confirm("¿Seguro que desea quitarlo de moderador?"))
		document.getElementById("formHacerAdmin").submit();	
}
function eliminarCuenta(id)
{
	document.getElementById("idUser").value = id;	
	document.getElementById("accion").value = "eliminarCuenta";
	
	if(confirm("¿Seguro que desea eliminar esta cuenta?"))
		document.getElementById("formHacerAdmin").submit();
}
function eliminarAnuncio(id)
{
	document.getElementById("idUser").value = id;	
	document.getElementById("accion").value = "eliminarAnuncio";
	
	if(confirm("¿Seguro que desea eliminar este anuncio?"))
		document.getElementById("formHacerAdmin").submit();	
}


//CAMBIA EL DISPLAY DE LOS DIVS PARA AÑADIR DINÁMICA A LA PÁGINA AL PULSAR LOS BOTONES DE LAS SECCIONES
function cambiar()
{
	let radioModeradores = document.getElementById("radioModeradores");
	let radioUsuarios = document.getElementById("radioUsuarios");
	let radioAnuncios = document.getElementById("radioAnuncios");
	let divModeradores = document.getElementById("camposModeradores");
	let divUsuarios = document.getElementById("camposUsers");
	let divAnuncios = document.getElementById("camposAnuncios");
	
	if(radioModeradores.checked)
	{
		divModeradores.style.display = "";
		divAnuncios.style.display = "none";
		divUsuarios.style.display = "none";
	}
	else if(radioUsuarios.checked)
	{
		divModeradores.style.display = "none";
		divAnuncios.style.display = "none";
		divUsuarios.style.display = "";		
	}
	else if(radioAnuncios.checked)
	{
		divModeradores.style.display = "none";
		divAnuncios.style.display = "";
		divUsuarios.style.display = "none";		
	}
}