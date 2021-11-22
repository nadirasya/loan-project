-- Deploy flipr:loan to pg
-- requires: account

BEGIN;

CREATE TABLE account.loan (
    id SERIAL PRIMARY KEY,
    accountId INT,
    nominal INT  NOT NULL,
    dueDate DATE,
    paidDate DATE,
    CONSTRAINT fk_account
      FOREIGN KEY(accountId) 
	  REFERENCES account.accounts(id)
);


COMMIT;
