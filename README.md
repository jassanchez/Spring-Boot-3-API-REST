# Spring Boot API REST (Spring Security + Spring Data) + Json Web Token Auth

A simple API Rest Secured with JWT Bearer Token using Spring Boot, Spring Security and Spring Data.

This project was created with Visual Studio Code with Java 21 and Spring Extensions.

## Installation

Create this database for use the project with MySQL.

```SQL
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
```

If you don't have installed Java, the link below redirect you to official page of Amazon Corretto JDK
[Amazon JDK Corretto](https://aws.amazon.com/es/corretto)

*Build the project to download the dependencies*

## Use

The Api Rest have the following endpoints:
  
### GET
- /api/products:         **required authentication**
- /api/products/{id}: **required authentication**
- /api/users: **required authentication**
 
### POST
- /api/products: **required authentication**
- /api/users:   **required authentication**
- /api/users/register: **Open endpoint to create user with low role level by default**
- /login **Login with credentials username and password**

### PUT
- /api/products/{id}: **required authentication**

### DELETE
- /api/products/{id}: **required authentication**

**To have a user with ADMIN ROLE, you need to assign the role from db, with this user, you can create any ADMIN user with the endpoint *POST /api/users***.

*To know about the JSON required for the operations check the entities package.*

Be yourself 😄.