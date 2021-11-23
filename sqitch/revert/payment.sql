-- Revert flipr:payment from pg

BEGIN;

DROP TABLE account.payment;

COMMIT;
