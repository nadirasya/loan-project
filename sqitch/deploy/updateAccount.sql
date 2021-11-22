-- Deploy flipr:updateAccount to pg
-- requires: appschema

BEGIN;

ALTER TABLE account.acounts
RENAME TO accounts; 

COMMIT;
