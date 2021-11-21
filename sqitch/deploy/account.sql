-- Deploy flipr:account to pg
-- requires: appschema

BEGIN;

CREATE TABLE account.acounts (
    id SERIAL PRIMARY KEY,
    email TEXT        NOT NULL,
    password  TEXT        NOT NULL,
    isActive BOOLEAN NOT NULL
);

COMMIT;
