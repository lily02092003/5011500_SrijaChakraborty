CREATE OR REPLACE PACKAGE AccountOperations AS
    
    PROCEDURE OpenAccount(
        p_AccountID NUMBER,
        p_CustomerID NUMBER,
        p_AccountType VARCHAR2,
        p_Balance NUMBER,
        p_LastModified DATE
    );

    PROCEDURE CloseAccount(
        p_AccountID NUMBER
    );

    FUNCTION GetTotalBalance(
        p_CustomerID NUMBER
    ) RETURN NUMBER;
END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS

    PROCEDURE OpenAccount(
        p_AccountID NUMBER,
        p_CustomerID NUMBER,
        p_AccountType VARCHAR2,
        p_Balance NUMBER,
        p_LastModified DATE
    ) IS
    BEGIN
        INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
        VALUES (p_AccountID, p_CustomerID, p_AccountType, p_Balance, p_LastModified);

        DBMS_OUTPUT.PUT_LINE('Account opened successfully for Customer ID: ' || p_CustomerID);
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Error: Account with ID ' || p_AccountID || ' already exists.');
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
    END OpenAccount;

    PROCEDURE CloseAccount(
        p_AccountID NUMBER
    ) IS
    BEGIN
        DELETE FROM Accounts WHERE AccountID = p_AccountID;

        IF SQL%ROWCOUNT = 0 THEN
            DBMS_OUTPUT.PUT_LINE('No account found with ID ' || p_AccountID);
        ELSE
            DBMS_OUTPUT.PUT_LINE('Account closed successfully: Account ID ' || p_AccountID);
        END IF;
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
    END CloseAccount;
    FUNCTION GetTotalBalance(
        p_CustomerID NUMBER
    ) RETURN NUMBER IS
        v_TotalBalance NUMBER;
    BEGIN
        SELECT SUM(Balance) INTO v_TotalBalance
        FROM Accounts
        WHERE CustomerID = p_CustomerID;

        RETURN v_TotalBalance;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Error: No accounts found for Customer ID ' || p_CustomerID);
            RETURN NULL;
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
            RETURN NULL;
    END GetTotalBalance;

END AccountOperations;
/

BEGIN
    AccountOperations.OpenAccount(1, 1, 'Savings', 1000, SYSDATE);
    AccountOperations.OpenAccount(2, 2, 'Checking', 1500, SYSDATE);
    AccountOperations.OpenAccount(3, 1, 'Checking', 2000, SYSDATE);
    AccountOperations.OpenAccount(4, 3, 'Savings', 2500, SYSDATE);
END;
/

BEGIN
    AccountOperations.CloseAccount(3);

    DBMS_OUTPUT.PUT_LINE('Total Balance for Customer ID 1: ' || AccountOperations.GetTotalBalance(1));
END;
/
