CREATE TABLE user (
  name varchar(20) DEFAULT NULL,
  age int DEFAULT NULL,
  uid int NOT NULL IDENTITY,
);

CREATE TABLE product (
  pid int not null IDENTITY,
  type int not null,
  product_desc varchar(100) NOT NULL,
  product_name varchar(100) NOT NULL
);

