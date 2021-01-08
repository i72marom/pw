<div id="filtros">
			<form action="" method="">		    
				<section class="buscador">
					<input type="search" placeholder="Buscar..." name="search" id="textoBuscar">		    	
					<button>Buscar</button>
				</section>

				<br/>
				<br/>
				<br/>
				<br/>
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
				
				<label class="checkbox-class">Publicado
					<input type="checkbox" class="checkBoxEstado" value="publicado" onclick="cambiarFiltros()" checked>
					<span class="checkmark"></span>
				</label>

				<label class="checkbox-class">Editado
					<input type="checkbox" class="checkBoxEstado" value="editado" onclick="cambiarFiltros()" checked>
					<span class="checkmark"></span>
				</label>

				<label class="checkbox-class">Archivado
					<input type="checkbox" class="checkBoxEstado" value="archivado" onclick="cambiarFiltros()" checked>
					<span class="checkmark"></span>
				</label>
				<label class="checkbox-class">En espera
					<input type="checkbox" class="checkBoxEstado" value="espera" onclick="cambiarFiltros()" checked>
					<span class="checkmark"></span>
				</label>

				

				<br/>
				<br/>

				<h2>Ordenar por</h2>
				<label class="checkbox-class">Titulo
					<input type="radio" name="order_by" value="titulo" id="OBtitulo">
					<span class="checkmark-radio"></span>
				</label>
				<label class="checkbox-class">Fecha
					<input type="radio" name="order_by" value="fecha" id="OBfecha" checked>
					<span class="checkmark-radio"></span>
				</label>
			</form>
		</div>