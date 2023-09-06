use mam_billing;


CREATE TABLE IF NOT EXISTS products (
`id` int PRIMARY KEY auto_increment not null,
product_name VARCHAR(100) not null ,
quantity int not null,
special_name VARCHAR(100),
quantity_type enum("g","ml","nos"),
is_active boolean default true
);


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



CREATE TABLE IF NOT EXISTS users (
  `id` int PRIMARY KEY auto_increment not null,
  name VARCHAR(50) not null,
  phone_number BIGINT(10) unique,
  email VARCHAR(150),
  address VARCHAR(250)
);



create TABLE IF NOT EXISTS bill (
  `id` int PRIMARY KEY auto_increment not null,
  timestamp timestamp DEFAULT  current_timestamp not null,
	 user_id INT not null,
    FOREIGN KEY (user_id) REFERENCES users(id)
);



CREATE TABLE IF NOT EXISTS bill_items (
  `id` int PRIMARY KEY auto_increment not null,
   bill_id INT not null,
   product_id INT not null,
   price_id INT not null,
   quantity int not null,
    FOREIGN KEY (bill_id) REFERENCES bill(id),
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (price_id) REFERENCES price(id)
);




Select * from users;
Select * from products;
Select * from price;
Select * from bills;
Select * from bill_items;



