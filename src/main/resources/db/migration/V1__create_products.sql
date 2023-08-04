CREATE TABLE IF NOT EXISTS products (
`id` int PRIMARY KEY auto_increment not null,
product_name VARCHAR(100) not null,
quantity int not null,
special_name VARCHAR(100),
quantity_type enum("mg","ml","nos")
);


INSERT INTO products (product_name, quantity, special_name, quantity_type) VALUES
('Rice', 500, NULL, 'mg'),
('Sugar', 1000, 'Special Edition', 'mg'),
('Seasame Oil', 1000, NULL, 'ml'),
('Soap', 5, 'Limited Edition', 'nos'),
('Choculate', 5, NULL, 'nos');


CREATE TABLE price (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tax DECIMAL(10, 2) not null,
    mrp DECIMAL(10, 2) not null,
    discount DECIMAL(10, 2)not null,
    start_date DATE,
    end_date DATE,
    product_id INT unique not null,
    FOREIGN KEY (product_id) REFERENCES products(id)
);


INSERT INTO price (tax, mrp, discount,start_date, end_date, product_id) VALUES
(5.0, 100.00, 10.00,  '2023-08-01', '2023-08-15', 1),
(8.5, 250.00, 20.00,  '2023-08-10', '2023-08-25', 2),
(2.0, 50.00, 5.00,  '2023-08-05', '2023-08-20', 3),
(4.5, 80.00, 15.00,  '2023-08-12', '2023-08-28', 5),
(6.0, 120.00, 25.00,  '2023-08-03', '2023-08-18', 4);

select * from price;
select * from products;