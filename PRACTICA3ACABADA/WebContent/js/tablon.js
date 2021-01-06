/**
 * 
 */


const input = document.getElementById("buscador");


let productos = document.getElementsByClassName("anuncio-class");
let productosArray = [];
let resultado = document.getElementById("tablon");
let numAnuncios = resultado.length;

let radios = document.getElementsByName("buscar");

let divsDesplegar    = document.getElementsByClassName("desplegar");
let botonesMas       = document.getElementsByClassName("mas");
let botonesMenos     = document.getElementsByClassName("menos");
let camposContenidoReducido = document.getElementsByClassName("anuncioReducido");
let camposContenido = document.getElementsByClassName("anuncioCompleto");
let checkBoxesOrdenar = document.getElementsByClassName("checkBoxOrdenar")
let formOrderBy = document.getElementById("formOrderBy");

function inicializarArray()
{
	for(let producto of productos)
	{
		productosArray.push(producto);
	}
}


function inicializarListeners(){
	
	for(let botonMas of botonesMas)
	{
		botonMas.addEventListener("click", verMas);
	}
	for(let botonMenos of botonesMenos)
	{
		botonMenos.addEventListener("click", verMenos);
	}
	for(let checkBox of checkBoxesOrdenar)
	{
		checkBox.addEventListener("click", ordenarPor);
	}
}


function verMas() {

	let id = this.id.replace("verMas", "");
	console.log(id);


	camposContenidoReducido[id].style.display = "none";
	camposContenido[id].style.display = "block";

	let nombreMenos = "verMenos" + id;
	let nombreMas = "verMas" + id;
	
	document.getElementById(nombreMenos).style.display = "block";
	document.getElementById(nombreMas).style.display = "none";

	console.log(nombreMenos)
	console.log(nombreMas)

	divsDesplegar[id].style.display = "block";
}

function verMenos() {
	let id = this.id.replace("verMenos", "");
	console.log(id);

	console.log(camposContenidoReducido);

	camposContenidoReducido[id].style.display = "block";
	camposContenido[id].style.display = "none";

	console.log(id)

	divsDesplegar[id].style.display = "none";
	//botonesMas[id].style.display       = "block";
	
	let nombreMenos = "verMenos" + id;
	let nombreMas = "verMas" + id;
	
	console.log(nombreMenos)
	console.log(nombreMas)
	
	document.getElementById(nombreMenos).style.display = "none";
	document.getElementById(nombreMas).style.display = "block";
	
	//botonesMenos[id].style.display     = "none";
}

function ordenarPor()
{
	if(this.id === "cbAutor")
	{
		document.getElementById("cbFecha").checked = false;
		document.getElementById("cbTema").checked = false;
		
		formOrderBy.action="/Tablon?order_by=Autor";
		formOrderBy.submit();
		
	}
	else if(this.id === "cbFecha")
	{
		document.getElementById("cbTema").checked = false;
		document.getElementById("cbAutor").checked = false;
		formOrderBy.action="/Tablon?order_by=Fecha";
		formOrderBy.submit();
		
	}
	else if(this.id === "cbTitulo")
	{
		document.getElementById("cbFecha").checked = false;
		document.getElementById("cbAutor").checked = false;
		formOrderBy.action="/Tablon?order_by=Titulo";
		formOrderBy.submit();
	}
}

function cambiarFiltros()
{
	let temas = document.getElementsByClassName("checkboxTema");
	let temasSeleccionados = [];
	for(let i = 0;i<temas.length;i++)
	{
		if(temas[i].checked == true)
		{
			temasSeleccionados[i] = temas[i].value;
		}
	}
	
	for(let i = 0;i<productos.length;i++)
	{
		if(productos[i].childNodes[7].innerHTML != "")
		{
			let encontrado = false;
			let temasAnuncioArray = productos[i].childNodes[7].innerHTML.split(",");
			console.log(temasAnuncioArray);
			for(let o = 0;o<temasSeleccionados.length && !encontrado;o++)
			{
				for(let u = 0;u<temasAnuncioArray.length && !encontrado;u++)
				{
					if(temasSeleccionados[o] === temasAnuncioArray[u])
					{
						productos[i].style.display = "";
						encontrado = true;
					}
				}				
			}
			if(!encontrado)
			{	
				productos[i].style.display = "none";
			}
		}
		else
		{
			productos[i].style.display = "";
		}
	}
	console.log(temasSeleccionados);
	
	
	
}

function inicializarFiltros(){
	
	const param = new URLSearchParams(window.location.search);
	let idBotonRadio, idBotonRadioOrderBy;
	if(param.get('search') != null)
	{
		console.log(param.get('search'));
		
		document.getElementById("textoBuscar").value=param.get('search');
	}
	
	
	
	if(param.get('by') != null)
	{
		console.log(param.get('by'));
		idBotonRadio = "CH"+param.get('by');
		console.log(idBotonRadio);
		document.getElementById(idBotonRadio).checked = true;
	}
	
	if(param.get('order_by') != null)
	{
		
		console.log(param.get('order_by'));
		idBotonRadioOrderBy = "OB"+param.get('order_by');
		
		document.getElementById(idBotonRadioOrderBy).checked = true;
		
	}
	
}



//input.addEventListener("keyup", filtrar2)
cambiarFiltros();
inicializarListeners();
inicializarArray();
inicializarFiltros();
//filtrar2();


