function muestraLogin() {
	var login    = document.getElementById('login-form');
	var registro = document.getElementById('registro-form');
	var logo     = document.getElementById('logo');

	login.style.display    = "block";
	registro.style.display = "none";
	logo.style.display     = "none";
}

function muestraRegistro() {
	var login    = document.getElementById('login-form');
	var registro = document.getElementById('registro-form');
	var logo     = document.getElementById('logo');

	login.style.display    = "none";
	registro.style.display = "block";
	logo.style.display     = "none";
}

function cierraLogin() {
	var login = document.getElementById('login-form');
	var logo  = document.getElementById('logo');

	login.style.display = "none";
	logo.style.display  = "block";
}

function cierraRegistro() {
	var registro = document.getElementById('registro-form');
	var logo     = document.getElementById('logo');

	registro.style.display = "none";
	logo.style.display     = "block";
}
