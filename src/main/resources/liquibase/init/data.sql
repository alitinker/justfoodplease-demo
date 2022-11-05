--liquibase formatted sql


--changeset alison.tinker:role-insert
INSERT INTO jfp_role (id, jfp_role) VALUES (1, 'ROLE_ADMIN');
INSERT INTO jfp_role (id, jfp_role) VALUES (2, 'ROLE_CLIENT');
INSERT INTO jfp_role (id, jfp_role) VALUES (3, 'ROLE_USER');

--changeset alison.tinker:unit-insert
INSERT INTO unit (id, abbreviation, name, description, date_modified) VALUES (1, 'Tbsp', 'Tablespoon', 'Tablespoon', now());
INSERT INTO unit (id, abbreviation, name, description, date_modified) VALUES (2, 'tsp', 'Teaspoon', 'Teaspoon', now());
INSERT INTO unit (id, abbreviation, name, description, date_modified) VALUES (3, 'C', 'Cup', 'Cup', now());
INSERT INTO unit (id, abbreviation, name, description, date_modified) VALUES (4, 'qt', 'Quart', 'Quart', now());
INSERT INTO unit (id, abbreviation, name, description, date_modified) VALUES (5, 'oz', 'Ounce', 'Ounce', now());
INSERT INTO unit (id, abbreviation, name, description, date_modified) VALUES (6, 'lb', 'Pound', 'Pound', now());
INSERT INTO unit (id, abbreviation, name, description, date_modified) VALUES (7, 'pt', 'Pint', 'Pint', now());
INSERT INTO unit (id, abbreviation, name, description, date_modified) VALUES (8, 'mL', 'Milliliter', 'Milliliter', now());
INSERT INTO unit (id, abbreviation, name, description, date_modified) VALUES (9, 'g', 'Gram', 'Gram', now());

--changeset alison.tinker:author-insert
INSERT INTO author (id, name, description, date_modified) VALUES (1, 'Albert Camus', 'Writer', now());
INSERT INTO author (id, name, description, date_modified) VALUES (2, 'Virginia Woolf', 'Writer', now());

