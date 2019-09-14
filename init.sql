

CREATE USER 'keycloak'@'localhost' IDENTIFIED BY 'k34cloak';
CREATE DATABASE `keycloakdb`CHARACTER SET utf8 COLLATE utf8_bin;
GRANT ALL PRIVILEGES ON keycloakdb.* TO 'keycloak'@'localhost';