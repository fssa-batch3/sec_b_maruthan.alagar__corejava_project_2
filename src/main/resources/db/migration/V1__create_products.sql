
SET time_zone = 'Asia/Kolkata';


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
  phone_number BIGINT unique not null,
  email VARCHAR(150),
  address VARCHAR(250)
);


CREATE TABLE IF NOT EXISTS bills (
  `id` int PRIMARY KEY auto_increment not null,
  `timestamp` timestamp  not null,
  user_id INT not null,
  is_active tinyint default true,
  FOREIGN KEY (user_id) REFERENCES users(id)
);


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

CREATE TABLE shop (
    id INT AUTO_INCREMENT PRIMARY KEY,
    shop_name VARCHAR(255) not null unique,
    licence_number VARCHAR(255)not null,
    gstn_Number VARCHAR(255) not null,
    phone_number BIGINT not null,
    email VARCHAR(255) not null,
    address VARCHAR(255) not null,
    print_name VARCHAR(255) not null ,
    owner_name VARCHAR(255) not null,
    password varchar(100) not null
);


Select * from users;
Select * from products;
Select * from price;
Select * from bills;
Select * from bill_items;
Select * from shop;






