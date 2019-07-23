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

CREATE TABLE IF NOT EXISTS oauth_refresh_token
(
    token_id       VARCHAR(256),
    token          bytea,
    authentication bytea
);

CREATE TABLE IF NOT EXISTS users
(
    email    VARCHAR(256),
    name     VARCHAR(256),
    password VARCHAR(256),
    role     VARCHAR(256),
    date     date
);
