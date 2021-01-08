//FUNCIONES GLOBALES

let publicados = document.getElementsByClassName("publicado-class");
let archivados = document.getElementsByClassName("archivado-class");
let editados = document.getElementsByClassName("editado-class");
let en_espera = document.getElementsByClassName("espera-class");
let camposOcultosAccion = document.getElementsByClassName("accion");


let divsDesplegar    = document.getElementsByClassName("desplegar");
let botonesMas       = document.getElementsByClassName("mas");
let botonesMenos     = document.getElementsByClassName("menos");
let camposContenidoReducido = document.getElementsByClassName("anuncioReducido");
let camposContenido = document.getElementsByClassName("anuncioCompleto");


//PONE TODOS LOS DISPLAYS DEL TABLON A NONE
function borrarTodos()
{
	for(let i = 0;i<publicados.length;i++)
		publicados[i].style.display="none";
	for(let i = 0;i<archivados.length;i++)
		archivados[i].style.display="none";
	for(let i = 0;i<editados.length;i++)
		editados[i].style.display="none";
	for(let i = 0;i<en_espera.length;i++)
		en_espera[i].style.display="none";
}

//FUNCION PARA MOSTRAR SOLO AQUELLOS ANUNCIOS QUE CUMPLAN LOS FILTROS
function cambiarFiltros()
{
	borrarTodos();
	let estados = document.getElementsByClassName("checkBoxEstado");
	let estadosSeleccionados = [];
	for(let i = 0;i<estados.length;i++)
	{
		if(estados[i].checked == true)
		{
			estadosSeleccionados[i] = estados[i].value;
		}
	}
	
	for(let i = 0;i<estadosSeleccionados.length;i++)
	{
		if(estadosSeleccionados[i])
		{
			let anuncios = document.getElementsByClassName(estadosSeleccionados[i]+"-class");
			console.log(anuncios);
			for(let o = 0;o<anuncios.length;o++)
			{
				anuncios[o].style.display = "";
			}			
		}

		
	}
	
	
	
	
}

//INICIALIZA LOS LISTENERS DE LOS BOTONES VER MAS/OCULTAR Y ORDERBY
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


//FUNCIONES RELACIONADAS CON LOS BOTONES DE VER MÁS / OCULTAR DE LOS ANUNCIOS
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

//INICIALIZA LOS INPUTS CON LOS DATOS DE LO BUSCADO ANTERIORMENTE
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

//FUNCIONES RELACIONADAS CON LOS SUBMITS

function archivar(idPosicion)
{
	camposOcultosAccion[idPosicion].value = "archivar";
	let idForm = "formAnuncio"+idPosicion;
	document.getElementById(idForm).submit();
}
function publicar(idPosicion)
{
	camposOcultosAccion[idPosicion].value = "publicar";
	let idForm = "formAnuncio"+idPosicion;
	document.getElementById(idForm).submit();
}
function eliminar(idPosicion)
{
	camposOcultosAccion[idPosicion].value = "eliminar";
	let idForm = "formAnuncio"+idPosicion;
	
	if(confirm("¿Desea eliminar definitivamente este anuncio?"))
		document.getElementById(idForm).submit();
}
function recuperar(idPosicion)
{
	camposOcultosAccion[idPosicion].value = "recuperar";
	let idForm = "formAnuncio"+idPosicion;
	document.getElementById(idForm).submit();
}
function editar(idPosicion)
{
	camposOcultosAccion[idPosicion].value = "editar";
	let idForm = "formAnuncio"+idPosicion;
	document.getElementById(idForm).submit();
}

//FUNCIONES QUE SE EJECUTARÁN AL CARGAR LA PÁGINA

inicializarFiltros();
cambiarFiltros();
inicializarListeners();