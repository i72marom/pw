'use strict'

// Variables globales
let tipo;
let botonesDestinatariosBoolean;
let radioGeneral         = document.getElementById("radioGeneral");
let radioIndividualizado = document.getElementById("radioIndividualizado");
let radioTematico        = document.getElementById("radioTematico");
let radioFlash           = document.getElementById("radioFlash");

// Quita los campos de otros tipos de aunucios.
function quitarCamposAnteriores() {
	if(document.getElementById("camposTematico") != null)
		document.getElementById("camposTematico").remove()
	
	if(document.getElementById("camposFlash") != null)
		document.getElementById("camposFlash").remove()
	
	if(document.getElementById("camposIndividualizado") != null)
		document.getElementById("camposIndividualizado").remove()	
}

// Añade los listeners a los botones de los destinatarios
// Función para seleccionar destinatarios.
function seleccionarDestinatario() {
	var id = this.getAttribute("id");
	id     = id.replace("botonDestinatario", "");
	
	if(botonesDestinatariosBoolean[id] === false || botonesDestinatariosBoolean[id]==="") {
		this.style.backgroundColor= "green";
		botonesDestinatariosBoolean[id] = true;
	}

	else {
		this.style.backgroundColor= "";
		botonesDestinatariosBoolean[id] = false;
	}
	
	console.log(id);
}

// Comprueba si se dan las condiciones para crear un anuncio.
function checkSubmit() {
	if(
		document.getElementById("titulo").value    !== "" && 
		document.getElementById("contenido").value !== "")
	{
		if(radioIndividualizado.checked === true) {
			let checkboxesDestinatarios = document.getElementsByName("destinatario");
			var numDestinatarios = 0;

			for(let i = 0;i<checkboxesDestinatarios.length;i++)
				if(checkboxesDestinatarios[i].checked) numDestinatarios++;
			
			if(numDestinatarios === 0)
				alert("Elige al menos un destinatario");

			else {
				let idDestinatarios = "";

				for(let i = 0;i<checkboxesDestinatarios.length;i++) {
					if(checkboxesDestinatarios[i].checked) {
						let idReal = checkboxesDestinatarios[i].id.replace("botonDestinatario","");
						idDestinatarios += idReal += ",";
					}
				}
				
				if(confirm("¿Desea crear el anuncio?")) {
					document.getElementById("idsDestinatarios").value = idDestinatarios;				
					document.getElementById("formularioCrearAnuncio").submit();					
				}
			}
		}

		else if(radioTematico.checked === true) {
			if(confirm("¿Desea crear el anuncio?"))
				document.getElementById("formularioCrearAnuncio").submit();
		}

		else if(radioFlash.checked === true) {
			if(
				confirm("¿Desea crear el anuncio?") && 
				document.getElementById("fechaInicio").value != "" && 
				document.getElementById("fechaFin").value != "") 
			{
				document.getElementById("formularioCrearAnuncio").submit();
			}
		}
		else {
			if(confirm("¿Desea crear el anuncio?"))
				document.getElementById("formularioCrearAnuncio").submit();
		}
	}

	else
		alert("Rellena los campos.");
}


// Al cambiar la fecha en el formulario, se actualiza la fecha final respecto al inicio.
function checkFechas() {
	let inputFechaInicio = document.getElementById("fechaInicio");
	let inputFechaFin    = document.getElementById("fechaFin");
	FechaFin.min         = inputFechaInicio.value;		
	
	if(inputFechaFin.value < inputFechaInicio.value)
		inputFechaFin.value = inputFechaInicio.value;
}


// Añade una funcionalidad para mostrar mas campos
function cambiarTipo() {
	let formulario = document.getElementById("campos"); 
	
	if(radioGeneral.checked === true) 
		quitarCamposAnteriores();

	else if(radioIndividualizado.checked === true) {
		quitarCamposAnteriores();
		let div = document.createElement('div');
		div.setAttribute("class", "campo");
		div.setAttribute("id", "camposIndividualizado")
		div.innerHTML = '<label class="label" for="destinatario">Destinatarios</label><br/>';
		
		for(let i = 0;i<arrayContactos.length;i++) {
			div.innerHTML += `
				<label class="checkbox-class cb">${arrayContactos[i].nombre} ${arrayContactos[i].apellidos} (${arrayContactos[i].email})
					<input type ="checkbox" name ="destinatario" class="destinatario" id = "botonDestinatario${arrayContactos[i].id}" value="${arrayContactos[i].nombre}" >
					<span class="checkmark cm"></span>
				</label>	
			`
		}
		
		div.innerHTML+=`<input type="hidden" id="idsDestinatarios" name="idsDestinatarios">`

		formulario.appendChild(div);
		anadirListeners();
	}

	else if(radioTematico.checked === true) {
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
	
	else if(radioFlash.checked === true) {
		let fecha      = new Date()
		let diaActual  = fecha.getDate();
		let mesActual  = fecha.getMonth() + 1;
		let anyoActual = fecha.getFullYear();
		
		if(diaActual < 10)
			diaActual = "0" + diaActual;
			
		if(mesActual < 10)
			mesActual = "0" + mesActual;
		
		
		quitarCamposAnteriores();
		let div = document.createElement('div');
		div.setAttribute("class", "campo");
		div.setAttribute("id", "camposFlash");
		
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
}