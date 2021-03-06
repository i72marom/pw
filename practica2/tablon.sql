create table contactos(
	id_contacto int primary key auto_increment not null,
	nombre varchar(55) not null,
	primer_apellido varchar(55) not null,
	segundo_apellido varchar(55) not null,
	email varchar(55) not null,
	fecha_nacimiento date not null,
	pass varchar(50) not null,
	tags set("amor", "entretenimiento", "cultura", "deportes", "comida")
);

create table anuncio(
	id_anuncio int primary key auto_increment not null,
	id_autor_fk int not null,
	titulo varchar(255) not null,
	cuerpo text(65535) not null,
	fecha date not null,
	estado enum("editado", "en_espera", "publicado", "archivado") not null
);

create table anuncio_flash(
	id_anuncio_flash int primary key auto_increment not null,
	anuncio_fk_f int not null,
	fecha_fin date not null
);

create table anuncio_individualizado(
	id_anuncio_ind int primary key auto_increment not null,
	anuncio_fk_i int not null
);

create table anuncio_tematico(
	id_anuncio_tematico int primary key auto_increment not null,
	anuncio_fk_t int not null,
	tags set("amor", "entretenimiento", "cultura", "deportes", "comida")
);

create table tema_dest(
	id_tema_dest int primary key auto_increment not null,
	anuncio_nm_fk int not null,
	dest_fk int not null
);

alter table anuncio add foreign key (id_autor_fk) references contactos(id_contacto);
alter table anuncio_flash add foreign key (anuncio_fk_f) references anuncio(id_anuncio);
alter table anuncio_individualizado add foreign key (anuncio_fk_i) references anuncio(id_anuncio);
alter table anuncio_tematico add foreign key (anuncio_fk_t) references anuncio(id_anuncio);
alter table tema_dest add foreign key (anuncio_nm_fk) references anuncio(id_anuncio);
alter table tema_dest add foreign key (dest_fk) references contactos(id_contacto);

alter table contactos add unique (id_contacto);
alter table anuncio add unique (id_anuncio);
alter table anuncio_flash add unique (id_anuncio_flash);
alter table anuncio_individualizado add unique (id_anuncio_ind);
alter table anuncio_tematico add unique (id_anuncio_tematico);
alter table tema_dest add unique (id_tema_dest);
