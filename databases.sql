CREATE DATABASE IF NOT EXISTS ingredientServiceDB;
CREATE DATABASE IF NOT EXISTS reviewServiceDB;
CREATE DATABASE IF NOT EXISTS recipeServiceDB;
CREATE DATABASE IF NOT EXISTS userServiceDB;
CREATE DATABASE IF NOT EXISTS systemEventsServiceDB;

CREATE USER IF NOT EXISTS 'springuser'@'%' IDENTIFIED BY 'ThePassword';

GRANT ALL PRIVILEGES ON ingredientServiceDB.* TO 'springuser'@'%';
GRANT ALL PRIVILEGES ON reviewServiceDB.* TO 'springuser'@'%';
GRANT ALL PRIVILEGES ON recipeServiceDB.* TO 'springuser'@'%';
GRANT ALL PRIVILEGES ON userServiceDB.* TO 'springuser'@'%';
GRANT ALL PRIVILEGES ON systemEventsServiceDB.* TO 'springuser'@'%';

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


USE recipeServiceDB;
create table if not exists recipeServiceDB.picture
(
    id           char(36)     not null
        primary key,
    date_created datetime(6)  not null,
    last_updated datetime(6)  not null,
    name         varchar(255) null,
    pic_byte     mediumblob   null,
    type         varchar(255) null
);

create table if not exists recipeServiceDB.category
(
    id                  char(36)    not null
        primary key,
    date_created        datetime(6) not null,
    last_updated        datetime(6) not null,
    name                varchar(50) not null,
    category_picture_id char(36)    not null,
    constraint FK6lxc43bviodc19wbbanus223d
        foreign key (category_picture_id) references recipeServiceDB.picture (id)
);

create table if not exists recipeServiceDB.Recipe
(
    id                 char(36)     not null
        primary key,
    date_created       datetime(6)  not null,
    deleted            bit          null,
    description        varchar(255) null,
    last_updated       datetime(6)  not null,
    name               varchar(50)  not null,
    preparation_time   int          not null,
    userid             char(36)     not null,
    recipe_category_id char(36)     not null,
    recipe_picture_id  char(36)     not null,
    constraint FK8w5y7i7u924xuhxbeumltsv84
        foreign key (recipe_category_id) references recipeServiceDB.category (id),
    constraint FKqe2kb8qs58vg0hiqfa4i2sx3o
        foreign key (recipe_picture_id) references recipeServiceDB.picture (id)
);

create table if not exists recipeServiceDB.step
(
    id              char(36)     not null
        primary key,
    date_created    datetime(6)  not null,
    description     varchar(255) not null,
    last_updated    datetime(6)  not null,
    o_number        int          not null,
    step_picture_id char(36)     null,
    step_recipe_id  char(36)     not null,
    constraint FKe6orvvb80cyvvjiam91ylppw0
        foreign key (step_recipe_id) references recipeServiceDB.recipe (id),
    constraint FKp0y6k3ir54dm00x72e2cg3e4v
        foreign key (step_picture_id) references recipeServiceDB.picture (id)
);

USE reviewServiceDB;
create table if not exists rating
(
    id           char(36)     not null
        primary key,
    comment      varchar(255) not null,
    date_created datetime(6)  not null,
    last_updated datetime(6)  not null,
    rating       int          not null,
    recipe_id    char(36)     not null,
    user_id      char(36)     not null
);


USE ingredientServiceDB;
create table if not exists ingredientServiceDB.picture
(
    id       char(36)     not null
        primary key,
    name     varchar(255) null,
    pic_byte mediumblob   null,
    type     varchar(255) null
);

create table if not exists ingredientServiceDB.ingredient
(
    id                    char(36)     not null
        primary key,
    calorie_count         int          null,
    carbohidrates         int          null,
    fat                   int          null,
    measuring_unit        varchar(50)  null,
    name                  varchar(255) null,
    proteins              int          null,
    vitamins              int          null,
    ingredient_picture_id char(36)     null,
    constraint FK4x4ygxyhqi7gwh3pd5kdafouf
        foreign key (ingredient_picture_id) references ingredientServiceDB.picture (id)
);

create table if not exists ingredientServiceDB.ingredient_recipe
(
    id                     char(36) not null
        primary key,
    quantity               int      null,
    recipeid               char(36) null,
    ingredient_recipeid_id char(36) not null,
    constraint FKm9gqek56u99yj7dra5w6uipln
        foreign key (ingredient_recipeid_id) references ingredientServiceDB.ingredient (id)
);