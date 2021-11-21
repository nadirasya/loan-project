-- Revert flipr:appschema from pg

BEGIN;

DROP SCHEMA account;

COMMIT;
