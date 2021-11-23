-- Deploy flipr:payment to pg
-- requires: loan

BEGIN;

CREATE TABLE account.payment (
    id SERIAL PRIMARY KEY,
    loanId INT,
    nominalPaid INT  NOT NULL,
    paidDate DATE,
    CONSTRAINT fk_loan
      FOREIGN KEY(loanId) 
	  REFERENCES account.loan(id)
);


COMMIT;

