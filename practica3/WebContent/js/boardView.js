/**
 * 
 */


const input = document.getElementById("buscador");

const boton = document.getElementById("botonBuscar");

const productos = document.getElementsByClassName('anuncio')

let resultado = document.getElementById("resultados");

var radios = document.getElementsByName('buscar');


console.log(productos)



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
	
	console.log(indice)
	
	const text = input.value.toLowerCase()
	
	for(let elemento of productos)
	{
		let nombre = elemento.childNodes[2].innerHTML
		let autor = elemento.childNodes[5].innerHTML
		let fecha = elemento.childNodes[8].innerHTML
		
		if(indice === 0)
		{
			if(nombre.indexOf(text) !== -1)
			{
				elemento.style.display = "";
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


boton.addEventListener("click", filtrar2)
input.addEventListener("keyup", filtrar2)

filtrar2();


