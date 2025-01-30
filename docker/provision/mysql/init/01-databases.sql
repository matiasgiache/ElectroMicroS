-- create databases
CREATE DATABASE IF NOT EXISTS `product_service`;
CREATE DATABASE IF NOT EXISTS `sale_service`;
CREATE DATABASE IF NOT EXISTS `cart_service`;

-- create root user and grant rights
CREATE USER 'root'@'localhost' IDENTIFIED BY 'root';
GRANT ALL ON *.* TO 'root'@'localhost';