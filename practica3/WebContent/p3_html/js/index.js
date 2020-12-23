/**
 * Al pulsar el boton de inicio de sesion, muestra
 * su formulario ocultando, si estuvieran visibles, 
 * el texto de presentación y el formulario de registro.
 * @var login recibe el id del formulario de login
 * @var registro recibe el id del formulario de registro
 * @var logo recibe el id del div con el texto de presentacion
 */
function muestraLogin() {
	var login    = document.getElementById('login-form');
	var registro = document.getElementById('registro-form');
	var logo     = document.getElementById('logo');

	login.style.display    = "block";
	registro.style.display = "none";
	logo.style.display     = "none";
}

/**
 * Al pulsar el boton de registro, muestra
 * su formulario ocultando, si estuvieran visibles, 
 * el texto de presentación y el formulario de registro.
 * @var login recibe el id del formulario de login
 * @var registro recibe el id del formulario de registro
 * @var logo recibe el id del div con el texto de presentacion
 */function muestraRegistro() {
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
