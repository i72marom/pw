/**
 * 
 */


const input = document.getElementById("buscador");


let productos = document.getElementsByClassName("anuncio");
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


function filtrar2()
{
	
	let acabado = false;
	let indice = 0;
	for(let i = 0;i<radios.length && !acabado;i++)
	{
		if(radios[i].checked)
		{
			acabado = true;
			indice = i;
		}
	}
	
	const text = input.value.toLowerCase().trim()
	
	//console.log(productos);
	
	for(let elemento of productosArray)
	{
		//console.log(elemento);
		
		resultado.innerHTML = "";
		
		let titulo = elemento.childNodes[1].innerHTML.trim();
		let autor = elemento.childNodes[3].innerHTML.trim();
		let fecha = elemento.childNodes[5].innerHTML.trim();
		
		//console.log("TITULO : " + titulo);
		//console.log("AUTOR : "  + autor);
		//console.log("FECHA : "  + fecha);
		
		if(indice === 0)
		{
			if(titulo.indexOf(text) !== -1)
			{
				elemento.style.display = "";
				resultado.innerHTML += elemento
			}
			else
			{
				elemento.style.display = "none";
			}			
		}
		else if(indice === 1)
		{
			if(autor.indexOf(text) !== -1)
			{
				elemento.style.display = "";
			}
			else
			{
				elemento.style.display = "none";
			}				
		}
		else if(indice === 2)
		{
			if(fecha.indexOf(text) !== -1)
			{
				elemento.style.display = "";
			}
			else
			{
				elemento.style.display = "none";
			}				
		}

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




//input.addEventListener("keyup", filtrar2)
inicializarListeners();
inicializarArray();
//filtrar2();


