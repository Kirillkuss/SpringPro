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

CREATE TABLE Users(
	id serial PRIMARY KEY,
	username VARCHAR( 50 ) NOT NULL,
	password_user VARCHAR( 50 ) NOT NULL,
	role_user VARCHAR( 50 ) NOT NULL,
	email VARCHAR( 50 ) 
);