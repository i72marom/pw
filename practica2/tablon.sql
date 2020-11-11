create database tablon;

create table contactos(
	id_contacto int,
	nombre varchar(255),
	primer_apellido varchar(255),
	segundo_apellido varchar(255),
	email varchar(255),
	fecha_nacimiento date,
	tags set("amor", "entretenimiento", "cultura", "deportes", "comida")
);

create table tablon(
	id_tablon int,
	id_contacto int,
	id_anuncio int
);

create table anuncio(
	id_anuncio int,
	id_contacto int,
	titulo varchar(255),
	cuerpo varchar(65535),
	fecha date,
	estado enum("editado", "en_espera", "publicado", "archivado"),
	tags set("amor", "entretenimiento", "cultura", "deportes", "comida")
);
