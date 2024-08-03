BEGIN
    EXECUTE IMMEDIATE 'DROP PROCEDURE ProcessMonthlyInterest';
EXCEPTION
    WHEN OTHERS THEN
        NULL; 
END;
/

CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
BEGIN
    
    UPDATE Accounts
    SET Balance = Balance + (Balance * 0.01)
    WHERE AccountType = 'Savings';
    
    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Monthly interest processed successfully.');
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
END ProcessMonthlyInterest;
/

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (3, 1, 'Savings', 1000, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (4, 2, 'Savings', 1500, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (5, 1, 'Checking', 500, SYSDATE);
/

BEGIN
    ProcessMonthlyInterest;
END;
/

SET SERVEROUTPUT ON;
SELECT AccountID, CustomerID, AccountType, Balance
FROM Accounts
WHERE AccountType = 'Savings';
/
