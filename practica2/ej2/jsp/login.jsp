<!DOCTYPE html>
<html lang="es">
<head>
	<title>Iniciar sesión</title>
</head>
<body>
	<div class="container">
		<div class="form">
			<form action="${pageContext.request.contextPath}/login" method="post">
				<label class="label" for="mail">Correo electrónico</label>
				<input type="text" id="user" name="mail" placeholder="Correo electrónico" required>

				<br/>

				<label class="label" for="pass">Contraseña</label>
				<input type="password" id="pass" name="pass" required>

				<br>

				<div class="button">
					<input id="boton" type="submit" value="Iniciar sesión">
				</div>
			</form>
		</div>
	</div>
</body>
</html>
