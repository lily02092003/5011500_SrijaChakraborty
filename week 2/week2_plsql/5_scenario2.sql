
CREATE TABLE AuditLog (
    AuditID NUMBER PRIMARY KEY,
    TransactionID NUMBER,
    AccountID NUMBER,
    TransactionDate DATE,
    Amount NUMBER,
    TransactionType VARCHAR2(10),
    LogDate DATE DEFAULT SYSDATE,
    FOREIGN KEY (TransactionID) REFERENCES Transactions(TransactionID)
);

BEGIN
    EXECUTE IMMEDIATE 'DROP TRIGGER LogTransaction';
EXCEPTION
    WHEN OTHERS THEN
        NULL; 
END;
/

CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (AuditID, TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (AuditLog_seq.NEXTVAL, :NEW.TransactionID, :NEW.AccountID, :NEW.TransactionDate, :NEW.Amount, :NEW.TransactionType);
END LogTransaction;
/

BEGIN
    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (1, 6, SYSDATE, 500, 'Deposit');

    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (2, 7, SYSDATE, 200, 'Withdrawal');

    COMMIT;
END;
/

SELECT * FROM AuditLog;
