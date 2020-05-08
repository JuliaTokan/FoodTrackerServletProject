DROP TABLE clients;
DROP TABLE users;
DROP TABLE products;
DROP TABLE meals;

CREATE TABLE clients (
  id SERIAL PRIMARY KEY,
  name VARCHAR(30) NOT NULL,
  gender_id INT NOT NULL,
  age INT NOT NULL,
  height DOUBLE PRECISION NOT NULL,
  weight DOUBLE PRECISION NOT NULL,
  nutritionGoal_id INT NOT NULL,  --?
  activity_id INT NOT NULL, --?
  calories INT NOT NULL,
  protein DOUBLE PRECISION NOT NULL,
  fats DOUBLE PRECISION NOT NULL,
  carbohydrates DOUBLE PRECISION NOT NULL
);

CREATE TABLE users (
 id SERIAL PRIMARY KEY,
 login VARCHAR(45) NOT NULL,
 password VARCHAR(45) NOT NULL,
 role_id INT NOT NULL, --?
 client_id INT
);

CREATE TABLE products (
 id SERIAL PRIMARY KEY,
 user_id INT NOT NULL,
 name VARCHAR(45) NOT NULL,
 calories INT NOT NULL ,
 protein DOUBLE PRECISION NOT NULL,
 fats DOUBLE PRECISION NOT NULL,
 carbohydrates DOUBLE PRECISION NOT NULL,
 common BOOLEAN NOT NULL
);

CREATE TABLE meals (
 id SERIAL PRIMARY KEY,
 user_id INT NOT NULL,
 product_id INT NOT NULL,
 weight INT NOT NULL,
 eat_period_id INT NOT NULL, --?
 date TIMESTAMP(10) NOT NULL
);

CREATE TABLE roles (
  id         SERIAL PRIMARY KEY,
  role VARCHAR(15) NOT NULL
);

CREATE TABLE genders (
  id         SERIAL PRIMARY KEY,
  gender VARCHAR(15) NOT NULL
);

CREATE TABLE nutritionGoals (
  id         SERIAL PRIMARY KEY,
  goal VARCHAR(45) NOT NULL,
  coefficient DOUBLE PRECISION NOT NULL
);

CREATE TABLE activities (
  id         SERIAL PRIMARY KEY,
  activity VARCHAR(30) NOT NULL,
  coefficient DOUBLE PRECISION NOT NULL
);

CREATE TABLE eatPeriods (
  id         SERIAL PRIMARY KEY,
  period VARCHAR(30) NOT NULL
);