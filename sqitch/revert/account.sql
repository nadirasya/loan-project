-- Revert flipr:account from pg

BEGIN;

DROP TABLE account.accounts;

COMMIT;
