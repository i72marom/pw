function conf(aux) {
	var info      = document.getElementById('info');
	var contacto  = document.getElementById('contacto');
	var tema      = document.getElementById('tema');
	var pass = document.getElementById('pass');

	if (aux == info) {
		info.style.display      = "block";
		contacto.style.display  = "none";
		tema.style.display      = "none";
		pass.style.display = "none";
	} else if (aux == contacto) {
		info.style.display      = "none";
		contacto.style.display  = "block";
		tema.style.display      = "none";
		pass.style.display = "none";
	} else if (aux == tema) {
		info.style.display      = "none";
		contacto.style.display  = "none";
		tema.style.display      = "block";
		pass.style.display = "none";
	} else if (aux == pass) {
		info.style.display      = "none";
		contacto.style.display  = "none";
		tema.style.display      = "none";
		pass.style.display = "block";
	}
}

/*function conf(aux) {
	var info      = document.getElementById('info');
	var contacto  = document.getElementById('contacto');
	var tema      = document.getElementById('tema');
	var seguridad = document.getElementById('seguridad');

	if (aux == info) {
		info.style.display      = "block";
		contacto.style.display  = "none";
		tema.style.display      = "none";
		seguridad.style.display = "none";
	} else if (aux == contacto) {
		info.style.display      = "none";
		contacto.style.display  = "block";
		tema.style.display      = "none";
		seguridad.style.display = "none";
	} else if (aux == tema) {
		info.style.display      = "none";
		contacto.style.display  = "none";
		tema.style.display      = "block";
		seguridad.style.display = "none";
	} else {
		info.style.display      = "none";
		contacto.style.display  = "none";
		tema.style.display      = "none";
		seguridad.style.display = "block";
	} else
}*/