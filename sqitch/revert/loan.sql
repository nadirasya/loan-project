-- Revert flipr:loan from pg

BEGIN;

DROP TABLE account.loan;

COMMIT;
