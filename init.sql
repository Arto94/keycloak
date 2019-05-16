

CREATE USER app WITH PASSWORD 'app1dev';
CREATE DATABASE app
  WITH
  OWNER = app
  ENCODING = 'UTF8'
  LC_COLLATE = 'en_US.utf8'
  LC_CTYPE = 'en_US.utf8'
  TABLESPACE = pg_default;

GRANT ALL PRIVILEGES ON DATABASE app TO app;



CREATE USER keycloak WITH PASSWORD 'k34cloak';
CREATE DATABASE keycloakdb
  WITH
  OWNER = keycloak
  ENCODING = 'UTF8'
  LC_COLLATE = 'en_US.utf8'
  LC_CTYPE = 'en_US.utf8'
  TABLESPACE = pg_default;
GRANT ALL PRIVILEGES ON DATABASE keycloakdb TO keycloak;