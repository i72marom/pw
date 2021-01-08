		<div id="filtros">
			<form action="Tablon" method="GET" id="formBuscar">		    
				<section class="buscador">
					<input type="search" placeholder="Buscar..." id="textoBuscar" name="search">		    	
					<button id="botonBuscar">Buscar</button>
				</section>

				<br/>
				<br/>
				<br/>
				<br/>

				<label class="checkbox-class">Autor
				  <input type="radio" name="by" value="autor" id="CHautor">
				  <span class="checkmark-radio"></span>
				</label>
				<label class="checkbox-class">Titulo
				  <input type="radio" name="by" value="titulo" id="CHtitulo" checked>
				  <span class="checkmark-radio"></span>
				</label>
				<label class="checkbox-class">Fecha
				  <input type="radio" name="by" value="fecha" id="CHfecha">
				  <span class="checkmark-radio"></span>
				</label>

				<br/>
				<br/>

				<h2>Filtro por temas</h2>
				
				<label class="checkbox-class">Amor
					<input type="checkbox" value="amor" id="CBamor" onclick="cambiarFiltros()" class="checkboxTema" checked>
					<span class="checkmark"></span>
				</label>

				<label class="checkbox-class">Cultura
					<input type="checkbox" value="cultura" id="CBcultura" onclick="cambiarFiltros()" class="checkboxTema" checked>
					<span class="checkmark"></span>
				</label>

				<label class="checkbox-class">Entretenimiento
					<input type="checkbox" value="entretenimiento" id="CBentretenimiento" onclick="cambiarFiltros()" class="checkboxTema" checked>
					<span class="checkmark"></span>
				</label>
				<label class="checkbox-class">Comida
					<input type="checkbox" value="comida" id="CBcomida" onclick="cambiarFiltros()" class="checkboxTema" checked>
					<span class="checkmark"></span>
				</label>
				<label class="checkbox-class">Deportes
					<input type="checkbox" value="deportes" id="CBdeportes" onclick="cambiarFiltros()" class="checkboxTema" checked>
					<span class="checkmark"></span>
				</label>				

				<br/>
				<br/>

				<h2>Ordenar por</h2>
				
				<label class="checkbox-class">Autor
					<input type="radio" name="order_by" value="autor" id="OBautor" onclick="cookieOrderBy('autor')">
					<span class="checkmark-radio"></span>
				</label>
				<label class="checkbox-class">Titulo
					<input type="radio" name="order_by" value="titulo" id="OBtitulo" onclick="cookieOrderBy('titulo')">
					<span class="checkmark-radio"></span>
				</label>
				<label class="checkbox-class">Fecha
					<input type="radio" name="order_by" value="fecha" id="OBfecha" onclick="cookieOrderBy('fecha')">
					<span class="checkmark-radio"></span>
				</label>
			</form>
		</div>