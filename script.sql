DROP TABLE IF EXISTS jugadores CASCADE;
DROP TABLE IF EXISTS partidas CASCADE;
DROP TABLE IF EXISTS participantes CASCADE;
DROP TABLE IF EXISTS rondas CASCADE;

-- DDL
CREATE TABLE jugadores (
	id serial primary key,
	apodo varchar not null
);

CREATE TABLE partidas (
	id serial primary key,
	fecha date not null,
	puntaje_max integer not null
);

CREATE TABLE participantes (
	id serial,
	partida_id integer references partidas(id),
	nombre_grupo varchar,
	jugador_uno_id integer references jugadores(id) not null,
	jugador_dos_id integer references jugadores(id),
	primary key (id, partida_id)
);

CREATE TABLE rondas (
	id serial primary key,
	numero_ronda integer not null,
	puntaje integer not null,
	participante_id integer not null,
	participante_partida_id integer not null,
	FOREIGN KEY (participante_id, participante_partida_id) REFERENCES participantes(id, partida_id)
);