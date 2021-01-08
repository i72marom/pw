/**
 * 
 */


function checkEditarBoton()
{
	let formulario = document.getElementById("formularioEditarAnuncio");
	
	
	if(document.getElementById("tipoAnuncio").value === "individualizado")
	{
		
	}
	
	//formulario.submit();
}


//VALIDA LOS CAMPOS A LA HORA DE EDITAR EL ANUNCIO
function checkSubmit()
{
	
	
	let tipo = document.getElementById("tipoAnuncio").value;
	if(document.getElementById("titulo").value !== "" && document.getElementById("contenido").value !== "")
	{

	
		if(tipo === "individualizado")
		{
			let checkboxesDestinatarios = document.getElementsByName("destinatario");
			
			var numDestinatarios = 0;
			for(let i = 0;i<checkboxesDestinatarios.length;i++)
			{
				if(checkboxesDestinatarios[i].checked)
				{
					numDestinatarios++;
				}
			}
			
			if(numDestinatarios === 0)
			{
				alert("Elige al menos un destinatario");
			}	
			else
			{
				let idDestinatarios = "";
				
				
				
				for(let i = 0;i<checkboxesDestinatarios.length;i++)
				{
					if(checkboxesDestinatarios[i].checked)
					{
						let idReal = checkboxesDestinatarios[i].id.replace("botonDestinatario","");
						
						idDestinatarios+=idReal+=",";
					}
				}
				
				document.getElementById("idsDestinatarios").value = idDestinatarios;
				
				document.getElementById("formularioEditarAnuncio").submit();
				
			}
		}
		else if(tipo === "tematico")
		{
			let checkboxesTemas = document.getElementsByName("tema");
			var numTemas = 0;
			for(let i = 0;i<checkboxesTemas.length;i++)
			{
				if(checkboxesTemas[i].checked)
				{
					numTemas++;
				}
			}
			if(numTemas === 0)
			{
				alert("Elige al menos un tema");
			}
			else
			{
				if(confirm("¿Desea editar el anuncio?"))
				{
					document.getElementById("formularioEditarAnuncio").submit();
				}				
			}

		}
		else if(tipo === "flash")
		{
			if(confirm("¿Desea editar el anuncio?"))
			{
				document.getElementById("formularioEditarAnuncio").submit();
			}
		}
		else
		{
			if(confirm("¿Desea editar el anuncio?"))
			{
				document.getElementById("formularioEditarAnuncio").submit();
			}
		}
	}
	else
	{
		alert("Rellena los campos.");
	}
	
	
}


