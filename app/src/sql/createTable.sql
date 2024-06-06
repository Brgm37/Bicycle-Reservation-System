begin;

create table DISPOSITIVO(
	noserie integer primary key,
	latitude numeric(6,4) check (latitude between -90 and 90),
	longitude numeric(6,4) check (longitude between -180 and 180),
	bateria integer check (bateria between 0 and 100)
	);

create table BICICLETA (
	id serial primary key,
	peso numeric(4,2) check (peso >= 0),
	raio integer check (raio between 13 and 23),
	modelo varchar(20),
	marca varchar(30),
	mudanca integer check (mudanca in (1,6,18,24)),
	estado varchar(30) check (estado in ('livre', 'ocupado', 'em manutenção', 'em reserva')),
	atrdisc char(1) check (atrdisc in ('C', 'E')),
	dispositivo integer,
    version integer not null default 0,
	foreign key (dispositivo) references dispositivo(noserie)
	);

create table CLASSICA(
	bicicleta integer, 
	foreign key (bicicleta) references bicicleta(id)
);

create table ELETRICA(
	bicicleta integer, 
	foreign key (bicicleta) references bicicleta(id),
	autonomia integer, 
	velocidade integer 
);

create table PESSOA(
	id serial primary key, 
	nome varchar(40),
	morada varchar(150),
    email varchar(40) check (email SIMILAR TO '[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}') unique,
	telefone varchar(30) unique,
	noident char(12) unique,
	nacionalidade varchar(20),
	atrdisc char(2) check (atrdisc in ('C', 'G'))
	);


create table LOJA( 
	codigo integer primary key,
    email varchar(40) check (email SIMILAR TO '[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}') unique,
	endereco varchar(100),
	localidade varchar(30),
	gestor integer,
	foreign key(gestor) references pessoa(id)
);

create table RESERVA(
	noreserva serial,
	loja integer,
	primary key (noreserva, loja),
	dtinicio timestamp,
	dtfim timestamp,
	valor numeric(5,2),
	bicicleta serial,
    version integer not null default 0,
	foreign key(bicicleta) references bicicleta(id),
	foreign key(loja) references loja(codigo)
);

create table TELEFONELOJA(
	loja integer,
	foreign key (loja) references loja(codigo),
	numero varchar(10),
	primary key (numero)
);

create table CLIENTERESERVA( 
	cliente integer,
	foreign key (cliente) references pessoa(id),
	reserva integer,
	loja integer, 
	foreign key (reserva, loja) references reserva(noreserva, loja)
);

commit;

rollback;