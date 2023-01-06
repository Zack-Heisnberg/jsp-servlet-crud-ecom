drop table IF EXISTS cartProduct ;
drop table IF EXISTS orders ;
drop table IF EXISTS clients ;
drop table IF EXISTS products;

create table products (
	id  int NOT NULL AUTO_INCREMENT,
	title varchar(120) NOT NULL,
	description varchar(220) NOT NULL,
    price float NOT NULL,
    image mediumblob,
	PRIMARY KEY (id)
);


create table clients (
	id  int NOT NULL AUTO_INCREMENT,
	fname varchar(120) NOT NULL,
	lname varchar(120) NOT NULL,
    address varchar(120) NOT NULL,
    phone varchar(120) NOT NULL,
	PRIMARY KEY (id)
);

create table orders (
	id  int NOT NULL AUTO_INCREMENT,
    clientid int,
    total float,
    time date,
	PRIMARY KEY (id),
	FOREIGN KEY (clientid) REFERENCES clients(id) ON DELETE CASCADE
);

create table cartProduct (
	id  int NOT NULL AUTO_INCREMENT,
    orderid int,
    productid int,
    qt int,
    price float,
	PRIMARY KEY (id),
    FOREIGN KEY (orderid) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY (productid) REFERENCES products(id) ON DELETE CASCADE
);