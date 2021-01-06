/**
 * 
 */


'use strict'


let tipo;
let radioGeneral = document.getElementById("radioGeneral");
let radioIndividualizado = document.getElementById("radioIndividualizado");
let radioTematico = document.getElementById("radioTematico");
let radioFlash = document.getElementById("radioFlash");


//QUITA LOS CAMPOS DEL FORMULARIO DE OTROS TIPOS DE ANUNCIOS.
function quitarCamposAnteriores()
{
		if(document.getElementById("camposTematico") != null)
			document.getElementById("camposTematico").remove()
		if(document.getElementById("camposFlash") != null)
			document.getElementById("camposFlash").remove()
		if(document.getElementById("camposIndividualizado") != null)
			document.getElementById("camposIndividualizado").remove()	

}

let botonesDestinatariosBoolean;


//AÑADE LOS LISTENERS A LOS BOTONES DE LOS DESTINATARIOS
//FUNCION PARA SELECCIONAR DESTINATARIOS
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


//CHECKEA SI SE DAN LAS CONDICIONES PARA CREAR UN ANUNCIO
function checkSubmit()
{
	//console.log(tipo);
	
	//console.log(document.getElementById("titulo").value);
	//console.log(document.getElementById("contenido").value);
	if(document.getElementById("titulo").value !== "" && document.getElementById("contenido").value !== "")
	{

	
		if(radioIndividualizado.checked === true)
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
				
				document.getElementById("formularioCrearAnuncio").submit();
				
			}
		}
		else if(radioTematico.checked === true)
		{
			if(confirm("¿Desea crear el anuncio?"))
			{
				document.getElementById("formularioCrearAnuncio").submit();
			}
		}
		else if(radioFlash.checked === true)
		{
			
			if(confirm("¿Desea crear el anuncio?") && document.getElementById("fechaInicio").value != "" && document.getElementById("fechaFin").value != "")
			{
				document.getElementById("formularioCrearAnuncio").submit();
			}
		}
		else
		{
			if(confirm("¿Desea crear el anuncio?"))
			{
				document.getElementById("formularioCrearAnuncio").submit();
			}
		}
	}
	else
	{
		alert("Rellena los campos.");
	}
	
	
}


//CADA VEZ QUE CAMBIO UNA FECHA EN EL FORMULARIO SE UPDATEA LA FECHAFIN RESPECTO A LA FECHAINICIO
function checkFechas()
{
	let inputFechaInicio = document.getElementById("fechaInicio");
	let inputFechaFin = document.getElementById("fechaFin");
	FechaFin.min = inputFechaInicio.value;		
	if(inputFechaFin.value < inputFechaInicio.value)
	{
		inputFechaFin.value = inputFechaInicio.value;
		
	}

	
	
}


//AÑADE FUNCIONALIDAD PARA MOSTRAR MÁS CAMPOS EN EL FORMULARIO AL ELEGIR TIPO DE ANUNCIO
function cambiarTipo()
{
	
	let formulario = document.getElementById("campos"); 
	

	
	if(radioGeneral.checked === true)
	{
		quitarCamposAnteriores();
	}
	else if(radioIndividualizado.checked === true)
	{
		quitarCamposAnteriores();
		let div = document.createElement('div');
		div.setAttribute("class", "campo");
		div.setAttribute("id", "camposIndividualizado")
		div.innerHTML = '<label class="label" for="destinatario">Destinatarios</label><br/>';
		for(let i = 0;i<arrayContactos.length;i++)
		{
			div.innerHTML += `
			
			
			<label class="checkbox-class cb">${arrayContactos[i].nombre} ${arrayContactos[i].apellidos} (${arrayContactos[i].email})
				<input type ="checkbox" name ="destinatario" class="destinatario" id = "botonDestinatario${arrayContactos[i].id}" value="${arrayContactos[i].nombre}" >
				<span class="checkmark cm"></span>
			</label>	
			`
		}
		div.innerHTML+=`
		
		<input type="hidden" id="idsDestinatarios" name="idsDestinatarios">
		
		`
		formulario.appendChild(div);
		
		anadirListeners();
	}
	else if(radioTematico.checked === true)
	{
		quitarCamposAnteriores();
		
		let div = document.createElement('div');
		div.setAttribute("class", "campo");
		div.setAttribute("id", "camposTematico");
		div.innerHTML = `
			<div class="row">
				<div class="col-1">
					<label for="tema">Tema</label>
				</div>
				<div class="col-2">
					<label class="checkbox-class cb">Amor
						<input type="checkbox" name="tema" value="amor">
						<span class="checkmark cm"></span>
					</label>
					<label class="checkbox-class cb">Cultura
						<input type="checkbox" name="tema" value="cultura">
						<span class="checkmark cm"></span>
					</label>
					<label class="checkbox-class cb">Deportes
						<input type="checkbox" name="tema" value="deportes">
						<span class="checkmark cm"></span>
					</label>
					<label class="checkbox-class cb">Entretenimiento
						<input type="checkbox" name="tema" value="entretenimiento">
						<span class="checkmark cm"></span>
					</label>
					<label class="checkbox-class cb">Comida
						<input type="checkbox" name="tema" value="comida">
						<span class="checkmark cm"></span>
					</label>
				</div>
			</div>
		
		`;
		formulario.appendChild(div);
	}
	else if(radioFlash.checked === true)
	{
		let fecha = new Date()
		let diaActual = fecha.getDate();
		let mesActual = fecha.getMonth() + 1;
		let anyoActual = fecha.getFullYear();
		
		if(diaActual < 10)
			diaActual = "0" + diaActual;
			
		if(mesActual < 10)
			mesActual = "0" + mesActual;
		
		
		quitarCamposAnteriores();
		let div = document.createElement('div');
		div.setAttribute("class", "campo");
		div.setAttribute("id", "camposFlash");
		/*div.innerHTML = `
		<label class="label" for="temas">Fecha inicio</label>
		<br/>
		<input type="date" id="fechaInicio" name="fechaInicio" value="${fecha.getFullYear()}-${fecha.getMonth()+1}-${fecha.getDate()}" min="${fecha.getFullYear()}-${fecha.getMonth()+1}-${fecha.getDate()}" max="2030-12-31" onchange="checkFechas()">
		<br/>
		<label class="label" for="temas">Fecha Fin</label>
		<br/>
		<input type="date" id="fechaFin" name="fechaFin" value="${fecha.getFullYear()}-${fecha.getMonth()+1}-${fecha.getDate()}" min="${fecha.getFullYear()}-${fecha.getMonth()+1}-${fecha.getDate()}" max="2030-12-31" onchange="checkFechas()">`;
		*/
		div.innerHTML = `
					<div class="row">
						<div class="col-1">
							<label for="fecha_inicio">Fecha de inicio</label>
						</div>
						<div class="col-2">
							<input type="date" id="fechaInicio" name="fechaInicio" value="${anyoActual}-${mesActual}-${diaActual}" min="${anyoActual}-${mesActual}-${diaActual}}" max="2030-12-31" onchange="checkFechas()">
						</div>
					</div>
					<div class="row">
						<div class="col-1">
							<label for="fecha_fin">Fecha de cierre</label>
						</div>
						<div class="col-2">
							<input type="date" id="fechaFin" name="fechaFin" value="${anyoActual}-${mesActual}-${diaActual}" min="${anyoActual}-${mesActual}-${diaActual}" max="2030-12-31" onchange="checkFechas()">
						</div>
					</div>
		`
		formulario.appendChild(div);
		
		
		
		
	}
	
	//console.log(arrayContactos);
	

	
	//let formulario = document.getElementById("campos");
	

	
	
	
	/*if(input === "general")
	{
		quitarCamposAnteriores();
	}
	
	if(input === "tematico")
	{
		quitarCamposAnteriores();
		
		let div = document.createElement('div');
		div.setAttribute("class", "campo");
		div.setAttribute("id", "camposTematico");
		div.innerHTML = '<label class="label" for="temas">Temas</label><br/><input type="text" id="inputTemas" name="inputTemas" placeholder="temas separados por comaxd" required>';
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
		div.innerHTML+=`<input type="hidden" id="idsDestinatarios" name="idsDestinatarios">`
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
		<input type="date" id="fechaInicio" name="fechaInicio" value="${fecha.getFullYear()}-${fecha.getMonth()+1}-${fecha.getDate()}" min="${fecha.getFullYear()}-${fecha.getMonth()+1}-${fecha.getDate()}" max="2030-12-31" onchange="checkFechas()">
		<br/>
		<label class="label" for="temas">Fecha Fin</label>
		<br/>
		<input type="date" id="fechaFin" name="fechaFin" value="${fecha.getFullYear()}-${fecha.getMonth()+1}-${fecha.getDate()}" min="${fecha.getFullYear()}-${fecha.getMonth()+1}-${fecha.getDate()}" max="2030-12-31" onchange="checkFechas()">`;
		formulario.appendChild(div);
	}
	
*/	
	
	
}