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