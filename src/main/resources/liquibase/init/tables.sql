--liquibase formatted sql


--changeset alison.tinker:unit-creation
create table if not exists unit(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    abbreviation varchar(10) NOT NULL,
    name varchar(50) NOT NULL,
    description varchar(100),
    date_modified timestamp NOT NULL
    )

--changeset alison.tinker:author-creation
create table if not exists author(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(50) NOT NULL,
    description varchar(100),
    date_modified timestamp NOT NULL
    )

--changeset alison.tinker:recipe-creation
create table if not exists recipe(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title varchar(50) NOT NULL,
    subtitle varchar(50),
    description varchar(150),
    instructions json,
    minutes tinyint,
    serving tinyint,
    author_id int NOT NULL,
    date_modified timestamp NOT NULL,
    FOREIGN KEY (author_id) REFERENCES author(id)
)

--changeset alison.tinker:ingredient-creation
create table if not exists ingredient(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(50) NOT NULL,
    description varchar(150),
    url text,
    date_modified timestamp NOT NULL
)

--changeset alison.tinker:recipe_ingredient-creation
create table if not exists recipe_ingredient(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    recipe_id int NOT NULL,
    ingredient_id int NOT NULL,
    quantity varchar(10),
    unit_id int,
    date_modified timestamp NOT NULL,
    FOREIGN KEY (recipe_id) REFERENCES recipe(id),
    FOREIGN KEY (ingredient_id) REFERENCES ingredient(id),
    FOREIGN KEY (unit_id) REFERENCES unit(id)
)

--changeset alison.tinker:jfp_user-creation
create table if not exists jfp_user(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    active boolean NOT NULL,
    jfp_password varchar(255) NOT NULL,
    jfp_username varchar(50),
    date_modified timestamp NOT NULL
)

--changeset alison.tinker:jfp_role-creation
create table if not exists jfp_role(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    jfp_role varchar(50) NOT NULL
)

--changeset alison.tinker:jfp_user_role-creation
create table if not exists jfp_user_role(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    jfp_user_id int NOT NULL,
    jfp_role_id int NOT NULL,
    date_modified timestamp NOT NULL,
    FOREIGN KEY (jfp_user_id) REFERENCES jfp_user(id),
    FOREIGN KEY (jfp_role_id) REFERENCES jfp_role(id)
)
