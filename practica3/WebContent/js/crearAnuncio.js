/**
 * 
 */


'use strict'



function quitarCamposAnteriores()
{
		if(document.getElementById("camposTematico") != null)
			document.getElementById("camposTematico").remove()
		if(document.getElementById("camposFlash") != null)
			document.getElementById("camposFlash").remove()
		if(document.getElementById("camposIndividualizado") != null)
			document.getElementById("camposIndividualizado").remove()	

}

var botonesDestinatariosBoolean;

function anadirListeners()
{
	let botonesDestinatarios = document.getElementsByClassName("destinatario");
	botonesDestinatariosBoolean = []
	
	for(var i = 0;i<botonesDestinatarios.length;i++)
	{
		botonesDestinatariosBoolean.push(false);
	}
	
	for(let boton of botonesDestinatarios)
	{
		boton.addEventListener("click", seleccionarDestinatario);
	}
	
	console.log(botonesDestinatariosBoolean);
	
}


function seleccionarDestinatario()
{
	var id = this.getAttribute("id");
	
	id = id.replace("botonDestinatario", "");
	
	if(botonesDestinatariosBoolean[id] === false || botonesDestinatariosBoolean[id]==="")
	{
		this.style.backgroundColor= "green";
		botonesDestinatariosBoolean[id] = true;
	}
	else
	{
		this.style.backgroundColor= "";
		botonesDestinatariosBoolean[id] = false;
	}
	
	console.log(id);
	
}

function checkSubmit()
{
	let input = document.getElementById("tipoAnuncio").value;
	var numDestinatarios = 0;
	
	if(input === "individualizado")
	{
		for(let i = 0;i<botonesDestinatariosBoolean.length;i++)
		{
			if( botonesDestinatariosBoolean[i] === true)
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
			if(confirm("¿Desea crear el anuncio?"))
			{
				document.getElementById("formularioCrearAnuncio").submit()
			}
		}
	}
	else
	{
		alert("Se podria crear");
	}
	
	
}

function checkFechas()
{
	let inputFechaInicio = document.getElementById("fechaInicio").value;
	let inputFechaFin = document.getElementById("fechaFin").value;
	
	console.log(inputFechaInicio);
	console.log(inputFechaFin);
	
	
}

function cargarScript()
{
	
	let input = document.getElementById("tipoAnuncio").value;
	
	console.log(arrayContactos);
	

	
	let formulario = document.getElementById("campos");
	

	
	console.log(input)
	
	if(input === "general")
	{
		quitarCamposAnteriores();
	}
	
	if(input === "tematico")
	{
		quitarCamposAnteriores();
		
		let div = document.createElement('div');
		div.setAttribute("class", "campo");
		div.setAttribute("id", "camposTematico");
		div.innerHTML = '<label class="label" for="temas">Temas</label><br/><input type="text" id="contenido" name="contenido" placeholder="Contenido" required>';
		formulario.appendChild(div);
	}
	else if(input === "individualizado")
	{
		quitarCamposAnteriores();
		let div = document.createElement('div');
		div.setAttribute("class", "campo");
		div.setAttribute("id", "camposIndividualizado")
		div.innerHTML = '<label class="label" for="destinatario">Destinatarios</label><br/>';
		for(let i = 0;i<arrayContactos.length;i++)
		{
			div.innerHTML += `<input type ="button" name ="destinatario" class="destinatario" id = "botonDestinatario${i}"style="margin-left:5px;" value="${arrayContactos[i].nombre}" >`
		}
		formulario.appendChild(div);
		
		anadirListeners();
		
	}
	else if(input === "flash")
	{
		
		let fecha = new Date()
		
		quitarCamposAnteriores();
		let div = document.createElement('div');
		div.setAttribute("class", "campo");
		div.setAttribute("id", "camposFlash");
		div.innerHTML = `
		<label class="label" for="temas">Fecha inicio</label>
		<br/>
		<input type="date" id="fechaInicio" name="trip-start" value="${fecha.getFullYear()}-${fecha.getMonth()+1}-${fecha.getDate()}" min="${fecha.getFullYear()}-${fecha.getMonth()+1}-${fecha.getDate()}" max="2030-12-31" onchange="checkFechas()">
		<br/>
		<label class="label" for="temas">Fecha Fin</label>
		<br/>
		<input type="date" id="fechaFin" name="trip-start" value="${fecha.getFullYear()}-${fecha.getMonth()+1}-${fecha.getDate()}" min="${fecha.getFullYear()}-${fecha.getMonth()+1}-${fecha.getDate()}" max="2030-12-31" onchange="checkFechas()">`;
		formulario.appendChild(div);
	}
	
	
	
	
}