CREATE DATABASE db_api;
USE db_api;

CREATE TABLE users(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(20) UNIQUE,
    password VARCHAR(60),
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP()
);

CREATE TABLE roles(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name	VARCHAR(45)
);

CREATE TABLE users_roles(
	user_id		BIGINT,
	role_id 	BIGINT,
	PRIMARY KEY(user_id,role_id)
);

CREATE TABLE products(
	id 		BIGINT PRIMARY KEY AUTO_INCREMENT,
    name 	VARCHAR(45),
    price	INT,
    description TEXT,
    sku VARCHAR(45)
);

INSERT INTO roles (name) VALUES ('ROLE_ADMIN'),('ROLE_USER')