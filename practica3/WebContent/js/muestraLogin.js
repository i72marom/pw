function muestraLogin() {
	var login    = document.getElementById('login-form');
	var registro = document.getElementById('registro-form');

	login.style.display="block";
	registro.style.display="none";
}

function muestraRegistro() {
	var login    = document.getElementById('login-form');
	var registro = document.getElementById('registro-form');

	login.style.display="none";
	registro.style.display="block";
}