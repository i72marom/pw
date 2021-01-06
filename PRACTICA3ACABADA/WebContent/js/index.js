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

//FUNCION QUE VALIDA EL REGISTRO
function checkRegistro()
{
	let nombre = document.getElementById("nombre");
	let apellido1 = document.getElementById("prim_ap");
	let apellido2 = document.getElementById("seg_ap");
	let email = document.getElementById("correo");
	let fecha_nac = document.getElementById("fechanac");
	let edad = document.getElementById("edad");
	let password = document.getElementById("password");
	let conf_password= document.getElementById("password-rep");
	
	
	if(nombre.value === "" || apellido1.value === "" || apellido2.value === "" || email.value === "" || fecha_nac.value === "" || edad.value === "" || password.value === "" || conf_password.value === "")
	{
		alert("Rellena todos los campos.");
	}
	else
	{
		if(password.value.length < 6)
		{
			alert("Contraseña demasiado corta. Mínimo 6 caracteres.");
		}
		else
		{
			if(password.value != conf_password.value)
			{
				alert("Las contraseñas no coinciden.");
			}
			else
			{
				
				if (/^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i.test(email.value))
				{
					document.getElementById("registroForm").submit();
				}
				else
				{
					alert("Correo inválido.");
				}
					
			}			
		}

	}
	
	
}