<!DOCTYPE html>
<html lang="es">
<head>
	<title>Registro</title>
</head>
<body>

	<!-- CONTAINER -->
	<div class="container">
		<div class="divmes">
			<p id="message" align="center" ><b>Registrate en la aplicaión</b></p>
		</div>

		<div class="form">
			<form>
				<label class="label" for="nombre">Nombre</label>
				<input type="text" id="nombre" name="name" placeholder="Nombre" required>

				<br/>

				<label class="label" for="prim_ap">Primer apellido</label>
				<input type="text" id="prim_ap" name="prim_ap" placeholder="Primer apellido" required>

				<br/>

				<label class="label" for="seg_ap">Segundo apellido</label>
				<input type="text" id="seg_ap" name="seg_ap" placeholder="Segundo apellido" required>

				<br/>

				<label class="label" for="correo">Correo electrónico</label>
				<input type="text" id="correo" name="gmail" placeholder="Correo" required>

				<br/>

				<label class="label" for="fechanac">Fecha de nacimiento</label>
				<input type="text" id="fechanac" name="date" placeholder="aaaa/mm/dd" required>

				<br/>

				<label class="label" for="pass">Contraseña</label>
				<input type="password" id="pass" name="pass" required>
				<input type="password" id="pass_rep" name="pass_rep" required>

				<br/>
				<br/>
				
				<div class="button">
					<input id="boton" type="submit" value="Continuar"></input>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
