################################################################
# Esquema E/R que gestiona contactos de agenda.
# @Autor: Fernando Ismael Pacheco Sánchez (fips).
# @web: www.ferpaxecosanxez.com
################################################################
drop database if exists agenda;
create database agenda;
use agenda;

# Contiene información que identifica a un contacto.
create table Contacto (
	id int not null auto_increment,
	nombre varchar(50) not null,
    direccion varchar(100) not null,
    descripcion varchar(256) default 'Alguna descripción de contacto',
    constraint PK_Contacto primary key (id)
);

# Contiene información sobre teléfonos de contacto.
create table Telefono (
	id int not null auto_increment,
	telefono varchar(12) not null,
    tipo enum ('Movil', 'Fijo', 'Trabajo') not null,
    idContacto int,
    constraint PK_Telefono primary key (id),
    constraint FK_Telefono_Contacto foreign key (idContacto) references Contacto (id)
		on delete restrict
        on update cascade
);

# Contiene información sobre una cita concertada de un usuario.
create table Cita (
	id int not null auto_increment,
	fechaHora datetime not null,
    descripcion varchar(100) default 'Alguna descripción de cita',
    idContacto int,
    constraint PK_Citas primary key (id),
    constraint FK_Cita_Contacto foreign key (idContacto) references Contacto (id)
		on delete restrict
        on update cascade
);

