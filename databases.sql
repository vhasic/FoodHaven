CREATE DATABASE IF NOT EXISTS ingredientServiceDB;
CREATE DATABASE IF NOT EXISTS reviewServiceDB;
CREATE DATABASE IF NOT EXISTS recipeServiceDB;
CREATE DATABASE IF NOT EXISTS userServiceDB;
CREATE DATABASE IF NOT EXISTS systemEventsServiceDB;

GRANT ALL ON ingredientServiceDB.* TO 'springuser'@'%';
GRANT ALL ON reviewServiceDB.* TO 'springuser'@'%';
GRANT ALL ON recipeServiceDB.* TO 'springuser'@'%';
GRANT ALL ON userServiceDB.* TO 'springuser'@'%';
GRANT ALL ON systemEventsServiceDB.* TO 'springuser'@'%';


INSERT INTO userServiceDB.role (id, date_created, last_updated, name) VALUES ('39f815d9-9f82-4015-858c-a5bf37d7a3ae', '2022-05-18 13:52:03.169084', '2022-05-18 13:52:03.169084', 'User');
INSERT INTO userServiceDB.role (id, date_created, last_updated, name) VALUES ('4cc8d514-53b7-4c2b-9c20-c394788d5447', '2022-05-18 13:52:03.084270', '2022-05-18 13:52:03.084270', 'Administrator');

INSERT INTO userServiceDB.user (id, date_created, email, first_name, last_name, last_updated, password, username, role_id) VALUES ('1124dc7e-1a3a-4a2b-9c8b-c7a3d3ed9476', '2022-05-18 13:52:03.215990', 'admin@nesto.com', 'Administrator', 'Administrator', '2022-05-18 13:52:03.215990', 'Password1!', 'admin', '4cc8d514-53b7-4c2b-9c20-c394788d5447');
INSERT INTO userServiceDB.user (id, date_created, email, first_name, last_name, last_updated, password, username, role_id) VALUES ('d655a515-aa56-40ca-a1cc-78a896e03c5e', '2022-05-18 13:52:03.262818', 'user@nesto.com', 'User', 'User', '2022-05-18 13:52:03.262818', 'Password1!', 'user', '39f815d9-9f82-4015-858c-a5bf37d7a3ae');
