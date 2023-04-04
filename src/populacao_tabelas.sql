/*Clientes*/
INSERT INTO Clientes (cliente_id, cliente_name, cliente_email, cliente_phone) VALUES (1, 'João da Silva', 'joao.silva@mail.com', '99999-8888');
INSERT INTO Clientes (cliente_id, cliente_name, cliente_email, cliente_phone) VALUES (2, 'Maria Joana', 'mjoana@mail.com', '98888-9999');
INSERT INTO Clientes (cliente_id, cliente_name, cliente_email, cliente_phone) VALUES (3, 'Pedro Souza', 'pedrosouza1@mail.com', '91234-5678');

/*Brands*/
INSERT INTO Brands (brand_id, brand_name, brand_year_created, brand_website) VALUES (1, 'Chevrolet', 1911, 'chevrolet.com');
INSERT INTO Brands (brand_id, brand_name, brand_year_created, brand_website) VALUES (2, 'Ford', 1903, 'ford.com');
INSERT INTO Brands (brand_id, brand_name, brand_year_created, brand_website) VALUES (3, 'Volkswagen', 1937, 'volkswagen.com');

/*Cars*/
INSERT INTO Cars (car_id, car_name, car_year, car_brand, car_price) VALUES (1, 'Omega', 1994, 'Chevrolet', 15430.30);
INSERT INTO Cars (car_id, car_name, car_year, car_brand, car_price) VALUES (2, 'Mustang', 2022, 'Ford', 540000.5);
INSERT INTO Cars (car_id, car_name, car_year, car_brand, car_price) VALUES (3, 'Jetta ', 2015, 'Volkswagen', 71070);