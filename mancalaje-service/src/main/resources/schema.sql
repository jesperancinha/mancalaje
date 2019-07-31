DROP TABLE IF EXISTS oauth_access_token;
CREATE TABLE IF NOT EXISTS oauth_access_token
(
    authentication_id VARCHAR(256),
    token_id          VARCHAR(256),
    token             bytea,
    user_name         VARCHAR(256),
    client_id         VARCHAR(256),
    authentication    bytea,
    refresh_token     VARCHAR(256)
);

DROP TABLE IF EXISTS oauth_refresh_token;
CREATE TABLE IF NOT EXISTS oauth_refresh_token
(
    token_id       VARCHAR(256),
    token          bytea,
    authentication bytea
);

DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users
(
    email    VARCHAR(256),
    name     VARCHAR(256),
    password VARCHAR(256),
    role     VARCHAR(256),
    date     TIMESTAMP
);
DROP TABLE IF EXISTS DATABASECHANGELOGLOCK;
DROP TABLE IF EXISTS DATABASECHANGELOG;