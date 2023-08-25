use mam_billing;


CREATE TABLE IF NOT EXISTS products (
`id` int PRIMARY KEY auto_increment not null,
product_name VARCHAR(100) not null ,
quantity int not null,
special_name VARCHAR(100),
quantity_type enum("g","ml","nos"),
is_active boolean default true
);

INSERT INTO products (product_name, quantity, special_name, quantity_type) VALUES
('Rice', 5020, NULL, 'g'),
('Sugar', 10200, 'Special Edition', 'g'),
('Seasame Oil', 2000, NULL, 'ml'),
('Soap', 51, 'Limited Edition', 'nos'),
('Choculate', 15, NULL, 'nos');

CREATE TABLE price (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tax DECIMAL(10, 2) not null,
    mrp DECIMAL(10, 2) not null,
    discount DECIMAL(10, 2)not null,
    start_date timestamp DEFAULT  current_timestamp,
    end_date  timestamp ,
    product_id INT unique not null,
    FOREIGN KEY (product_id) REFERENCES products(id)
);

INSERT INTO price (tax, mrp, discount,start_date, end_date, product_id) VALUES
(5.0, 100.00, 10.00,  '2023-08-01', '2023-08-15', 1),
(8.5, 250.00, 20.00,  '2023-08-10', '2023-08-25', 2),
(2.0, 50.00, 5.00,  '2023-08-05', '2023-08-20', 3),
(4.5, 80.00, 15.00,  '2023-08-12', '2023-08-28', 5),
(6.0, 120.00, 25.00,  '2023-08-03', '2023-08-18', 4);

CREATE TABLE IF NOT EXISTS users (
  `id` int PRIMARY KEY auto_increment not null,
  name VARCHAR(50) not null,
  phone_number BIGINT(10) unique,
  email VARCHAR(150),
  address VARCHAR(250)
);

INSERT INTO users (name, phone_number, email,address) VALUES
("Maruthan",7810061572,"amaruthan@gmail.com","Pudukkottai"),
("Ram",8978675645,"ramr@gmail.com","Selam"),
("Vignesh",6787879878,"srvn@gmail.com","Kumbakonam"),
("Santhosh",9878675645,null,"Manalmedu"),
("Mathi",6787878787,"mathip@gmail.com","PondyCheri");

create TABLE IF NOT EXISTS bills (
  `id` int PRIMARY KEY auto_increment not null,
  timestamp timestamp DEFAULT  current_timestamp not null,
	 user_id INT not null,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO bills (user_id) VALUES
(1),
(2),
(3),
(4),
(5);

CREATE TABLE IF NOT EXISTS bill_items (
  `id` int PRIMARY KEY auto_increment not null,
   bill_id INT not null,
   product_id INT not null,
   price_id INT not null,
   quantity int not null,
    FOREIGN KEY (bill_id) REFERENCES bills(id),
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (price_id) REFERENCES price(id)
);

INSERT INTO bill_items (bill_id,product_id,price_id,quantity) VALUES
(1,1,1,100),
(2,2,2,100),
(3,3,3,100),
(4,4,4,100),
(5,5,5,100);


Select * from users;
Select * from products;
Select * from price;
Select * from bills;
Select * from bill_items;

-- drop table bill_items;
-- drop table bill;
-- drop table users;
-- drop table price;
-- drop table products;



