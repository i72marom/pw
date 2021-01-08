
// Cambia el borde del boton cuando se pulse para facilitar la visualización
// del apartado actual.
function cambiarEstiloBoton(actual) {
	var botones = document.getElementsByClassName("create");
	actual.classList.add("activo");
	
	for(let boton of botones) {
		if(boton != actual) {
			if(boton.classList.contains("activo"))
				boton.classList.remove("activo");
		}
	}
}

// Muestra la seccion de configuración correspondiente a la opcion marcada.
function conf(aux) {
	var info      = document.getElementById('info');
	var contacto  = document.getElementById('contacto');
	var tema      = document.getElementById('tema');
	var pass      = document.getElementById('pass');
	
	var botonInfo     = document.getElementById("botonInfo");
	var botonContacto = document.getElementById("botonContacto");
	var botonTema     = document.getElementById("botonTema");
	var botonPass     = document.getElementById("botonPass");
	
	if (aux == info) {
		info.style.display     = "block";
		contacto.style.display = "none";
		tema.style.display     = "none";
		pass.style.display     = "none";
		
		cambiarEstiloBoton(botonInfo);
	} 

	else if (aux == contacto) {
		info.style.display     = "none";
		contacto.style.display = "block";
		tema.style.display     = "none";
		pass.style.display     = "none";
		
		cambiarEstiloBoton(botonContacto);
	}

	else if (aux == tema) {
		info.style.display     = "none";
		contacto.style.display = "none";
		tema.style.display     = "block";
		pass.style.display     = "none";
		
		cambiarEstiloBoton(botonTema);
	}

	else if (aux == pass) {
		info.style.display     = "none";
		contacto.style.display = "none";
		tema.style.display     = "none";
		pass.style.display     = "block";

		cambiarEstiloBoton(botonPass);
	}
}
// Validación para el formulario de información personal.
function checkBotonInfo() {
	let nombre    = document.getElementById("nombre");
	let apellido1 = document.getElementById("prim_ap");
	let apellido2 = document.getElementById("seg_ap");
	
	if(nombre.value != "" && apellido1.value != "" && apellido2.value != "")
		document.getElementById("info").submit();

	else
		alert("No dejes campos vacíos.");
}

// Validación para el formulario de cambio de contraseña.
function checkBotonPass() {
	let passActual   = document.getElementById("password");
	let passNueva    = document.getElementById("new_pass");
	let passNuevaRep = document.getElementById("rep_new_pass");
	
	if(passActual.value.length >= 6 && passNueva.value.length >= 6 && passNuevaRep.value === passNueva.value )
		document.getElementById("pass").submit();

	else
		alert("Has introducido los datos mal");
}
