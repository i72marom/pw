function verMas() {
	var desplegar = document.getElementById('desplegar');
	var mas       = document.getElementById('mas');
	var menos     = document.getElementById('menos');

	desplegar.style.display = "block";
	mas.style.display       = "none";
	menos.style.display     = "block";
}

function verMenos() {
	var desplegar = document.getElementById('desplegar');
	var mas       = document.getElementById('mas');
	var menos     = document.getElementById('menos');

	desplegar.style.display = "none";
	mas.style.display       = "block";
	menos.style.display     = "none";
}

function modoNoche(a) {
	var noche   = document.getElementById('noche');
	var dia     = document.getElementById('dia');
	var filtros = document.getElementById('filtros');
	var body    = document.getElementById('body');

	if (a == noche) {
		filtros.style.background = "#303234";
		body.style.background    = "#4d5255";
		body.style.color         = "#fff";
		noche.style.display      = "none";
		dia.style.display        = "block";
	} else {
		filtros.style.background = "#e5ebef";
		body.style.background    = "#fff";
		body.style.color         = "#000";
		noche.style.display      = "block";
		dia.style.display        = "none";
	}
}
