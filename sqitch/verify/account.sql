-- Verify flipr:account on pg

BEGIN;

SELECT id, email, password, isActive
  FROM account.accounts
 WHERE FALSE;

ROLLBACK;
