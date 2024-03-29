CREATE TABLE Animal(
	id serial PRIMARY KEY,
	name VARCHAR( 20 ) NOT NULL,
	amount int8 NOT NULL,
	count int
);

CREATE TABLE Person(
	id serial PRIMARY KEY,
	name VARCHAR( 20 ) NOT NULL,
	login VARCHAR( 20 ) NOT NULL,
	phone VARCHAR( 13 ) NOT NULL,
	wallet int8 NOT NULL
);

CREATE TABLE person_animal(
	idAnimal INTEGER references animal( id ),
	idPerson INTEGER references person( id ) 
)