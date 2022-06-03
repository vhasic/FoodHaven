CREATE DATABASE IF NOT EXISTS ingredientServiceDB;
CREATE DATABASE IF NOT EXISTS reviewServiceDB;
CREATE DATABASE IF NOT EXISTS recipeServiceDB;
CREATE DATABASE IF NOT EXISTS userServiceDB;
CREATE DATABASE IF NOT EXISTS systemEventsServiceDB;

CREATE USER if not exists 'springuser'@'%' identified by 'ThePassword';

GRANT ALL ON ingredientServiceDB.* TO 'springuser'@'%';
GRANT ALL ON reviewServiceDB.* TO 'springuser'@'%';
GRANT ALL ON recipeServiceDB.* TO 'springuser'@'%';
GRANT ALL ON userServiceDB.* TO 'springuser'@'%';
GRANT ALL ON systemEventsServiceDB.* TO 'springuser'@'%';

USE userServiceDB;
create table if not exists role
(
    id           char(36)     not null
    primary key,
    date_created datetime(6)  not null,
    last_updated datetime(6)  not null,
    name         varchar(255) not null,
    constraint UK_8sewwnpamngi6b1dwaa88askk
    unique (name)
);
create table if not exists user
(
    id           char(36)     not null
        primary key,
    date_created datetime(6)  not null,
    email        varchar(255) not null,
    first_name   varchar(20)  not null,
    last_name    varchar(20)  not null,
    last_updated datetime(6)  not null,
    password     varchar(255) null,
    username     varchar(255) not null,
    role_id      char(36)     not null,
    constraint UK_ob8kqyqqgmefl0aco34akdtpe
        unique (email),
    constraint UK_sb8bbouer5wak8vyiiy4pf2bx
        unique (username),
    constraint FKk0j847d0uhn0nxb3a1grvqdao
        foreign key (role_id) references role (id)
);

INSERT INTO userServiceDB.role (id, date_created, last_updated, name) VALUES ('39f815d9-9f82-4015-858c-a5bf37d7a3ae', '2022-05-18 13:52:03.169084', '2022-05-18 13:52:03.169084', 'User');
INSERT INTO userServiceDB.role (id, date_created, last_updated, name) VALUES ('4cc8d514-53b7-4c2b-9c20-c394788d5447', '2022-05-18 13:52:03.084270', '2022-05-18 13:52:03.084270', 'Administrator');

INSERT INTO userServiceDB.user (id, date_created, email, first_name, last_name, last_updated, password, username, role_id) VALUES ('1124dc7e-1a3a-4a2b-9c8b-c7a3d3ed9476', '2022-05-18 13:52:03.215990', 'admin@nesto.com', 'Administrator', 'Administrator', '2022-05-18 13:52:03.215990', 'Password1!', 'admin', '4cc8d514-53b7-4c2b-9c20-c394788d5447');
INSERT INTO userServiceDB.user (id, date_created, email, first_name, last_name, last_updated, password, username, role_id) VALUES ('d655a515-aa56-40ca-a1cc-78a896e03c5e', '2022-05-18 13:52:03.262818', 'user@nesto.com', 'User', 'User', '2022-05-18 13:52:03.262818', 'Password1!', 'user', '39f815d9-9f82-4015-858c-a5bf37d7a3ae');
