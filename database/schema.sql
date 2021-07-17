CREATE TABLE account (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    address1 VARCHAR(255),
    address2 VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    postal_code VARCHAR(255),
    phone_number VARCHAR(255)
);

CREATE TABLE product (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    slug VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(1000)
);

CREATE TABLE item (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    product_id BIGINT(20) NOT NULL,
    price INT(11) NOT NULL,
    stock INT(11) NOT NULL
)