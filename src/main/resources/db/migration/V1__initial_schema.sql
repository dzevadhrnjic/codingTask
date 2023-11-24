CREATE EXTENSION postgis;

CREATE TABLE category(
	id SERIAL PRIMARY KEY,
	name varchar(30)
);

CREATE TABLE product(
	id SERIAL PRIMARY KEY,
	name varchar(30),
	description varchar(50),
	category int REFERENCES category(id),
	price numeric,
	views int,
	image varchar(100),
	geog geography
);

CREATE INDEX ON product USING gist(geog);

CREATE TABLE price_history(
	id SERIAL PRIMARY KEY,
	productId int REFERENCES Product(id) ON DELETE CASCADE,
	price numeric,
	timestamp timestamp
);

CREATE TABLE dbuser(
	id SERIAL PRIMARY KEY,
	name varchar(50),
	address varchar(50),
	phonenumber varchar(50),
	email varchar(50),
	password varchar(200)
);