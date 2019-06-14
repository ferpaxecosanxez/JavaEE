################################################################
# Esquema E/R que gestiona una pequeña funcionalidad de login a aplicación y gestión de compras de libros.
# @Autor: Fernando Ismael Pacheco Sánchez (fips).
# @web: www.ferpaxecosanxez.com
################################################################
drop database if exists libros;
create database libros;
use libros;

# Contiene información relacionada con datos personales de un usuario.
create table Cliente (
	id int auto_increment,
    nombre varchar(50) not null,
    apellido varchar(50) not null,
    dni varchar(9) not null,
    constraint PK_Cliente primary key (id),
    constraint UK_Cliente_dni unique key (dni)
);

# Contiene información ralacionada con el acceso a la aplicación. 
create table Usuario (
	id int auto_increment,
    usuario varchar(50) not null,
    pass varchar(50) not null,
    idCliente int not null,
    ultimoAcceso datetime not null,
    constraint PK_Usuario primary key (id),
    constraint UK_Usuario_user unique key (usuario),
	constraint FK_Usuario_Cliente foreign key (idCliente) references Cliente (id)
		on delete restrict
        on update cascade
);

# Contiene información relacionada con los temas que puede tener un libro.
create table Tema (
	id int auto_increment,
    tema varchar(50) not null,
    constraint PK_Tema primary key (id)
);

# Contiene información relacionada con un libro y su precio de venta.
create table Libro (
	isbn int auto_increment,
    titulo varchar(100) not null,
    autor varchar(50) not null,
    precio decimal(6,2) not null default 0.00,
    paginas int not null,
    idTema int not null,
    constraint PK_Libro primary key (isbn),
	constraint FK_Libro_Tema foreign key (idTema) references Tema (id)
		on delete restrict
        on update cascade
);

insert into Cliente values
(1, "Fernando", "Pacheco", "55526889S"),
(2, "David", "Claro", "00000000A"),
(3, "Antonio", "Carrión", "00000000B"),
(4, "David", "Donamaria", "77856923N");

insert into Usuario values
(1, "fpacheco", "admin", 1, current_timestamp()),
(2, "declaroba", "declaroba", 2, current_timestamp()),
(3, "acarriom", "acarriom", 3, current_timestamp()),
(4, "donamuso", "donamuso", 4, current_timestamp());

insert into Tema values
(1, "Literatura"),
(2, "Comic y Fantasía"),
(3, "Infantil y Juvenil"),
(4, "Actualidad y Empresa"),
(5, "Conocimiento y Ciencia"),
(6, "Cocina y Gastronomía"),
(7, "Bienestar y Salud"),
(8, "Viajes y Ocio"),
(9, "Agendas y Calendarios");

insert into Libro values
(1, "La ciudad del fuego", "Kate Mosse", 21.90, 640, 1),
(2, "Star Wars Secretos de la galaxia", "Daniel Wallace", 80.00, 640, 2),
(3, "Cuentos de Lucía, mi pediatra", "Lucía Galán Bertrand", 15.95, 96, 3),
(4, "La revolución de las canas", "Iñaki Ortega Cachón | Antonio Huertas Mejías", 16.95, 250, 4),
(5, "La confianza en uno mismo", "Charles Pepin", 17.90, 192, 5),
(6, "1000 recetas de oro", "Karlos Arguiñano", 24.95, 728, 6),
(7, "Vivir sin plástico", "Patricia Reina Toresano | Fernando Gómez Soria", 16.95, 208, 7),
(8, "Nuestro planeta", "Alistair Fothergill | Keith Scholey", 29.95, 320, 8),
(9, "Elevate", "Pepe Monserrate", 15.90, 200, 9)



