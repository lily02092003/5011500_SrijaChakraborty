
BEGIN
    EXECUTE IMMEDIATE 'DROP FUNCTION HasSufficientBalance';
EXCEPTION
    WHEN OTHERS THEN
        NULL; 
END;
/

CREATE OR REPLACE FUNCTION HasSufficientBalance (
    p_account_id IN NUMBER,
    p_amount IN NUMBER
) RETURN BOOLEAN IS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance
    FROM Accounts
    WHERE AccountID = p_account_id;

    IF v_balance >= p_amount THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE; 
    WHEN OTHERS THEN
        RETURN FALSE; 
END HasSufficientBalance;
/

BEGIN
    INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
    VALUES (8, 3, 'Savings', 5000, SYSDATE);

    INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
    VALUES (9, 4, 'Checking', 3000, SYSDATE);
EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        
        NULL;
END;
/

SET SERVEROUTPUT ON;

DECLARE
    v_account_id NUMBER := 8;
    v_amount NUMBER := 2000;
    v_has_balance BOOLEAN;
BEGIN
    v_has_balance := HasSufficientBalance(v_account_id, v_amount);
    IF v_has_balance THEN
        DBMS_OUTPUT.PUT_LINE('Account ' || v_account_id || ' has sufficient balance for the transaction of ' || v_amount);
    ELSE
        DBMS_OUTPUT.PUT_LINE('Account ' || v_account_id || ' does not have sufficient balance for the transaction of ' || v_amount);
    END IF;
END;
/
