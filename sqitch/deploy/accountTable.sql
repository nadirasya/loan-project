-- Deploy flipr:accountTable to pg
-- requires: appschema

BEGIN;

CREATE TABLE account (
    id SERIAL PRIMARY KEY,
    email TEXT        NOT NULL,
    password  TEXT        NOT NULL,
    isActive BOOLEAN NOT NULL
);


COMMIT;
