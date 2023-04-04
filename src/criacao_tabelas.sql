/*CREATE DATABASE trab03;*/

create table Clientes(
	"cliente_id" integer not null,
	"cliente_name" varchar (255),
	"cliente_email" varchar (255),
	"cliente_phone" varchar (255),
	constraint "clientes_pk" primary key ("cliente_id")
);

create table Brands(
	"brand_id" integer not null,
	"brand_name" varchar (255),
	"brand_yearCreated" integer,
	"brand_website" varchar (255),
	constraint "brands_pk" primary key ("brand_id")
);

create table Cars(
	"car_id" integer not null,
	"car_name" varchar (255),
	"car_year" integer,
	"car_brand" varchar (255),
	"car_price" real,
	constraint "cars_pk" primary key ("car_id")
);