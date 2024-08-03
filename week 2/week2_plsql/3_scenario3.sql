-- Step 1: Create the procedure
CREATE OR REPLACE PROCEDURE TransferFunds (
    p_SourceAccountID IN NUMBER,
    p_DestinationAccountID IN NUMBER,
    p_Amount IN NUMBER
) AS
    v_SourceBalance NUMBER;
    v_DestinationBalance NUMBER;
BEGIN
    -- Check if the amount is positive
    IF p_Amount <= 0 THEN
        DBMS_OUTPUT.PUT_LINE('Error: Transfer amount must be positive.');
        RETURN;
    END IF;

    -- Retrieve the current balance of the source account
    SELECT Balance INTO v_SourceBalance
    FROM Accounts
    WHERE AccountID = p_SourceAccountID;

    -- Retrieve the current balance of the destination account
    SELECT Balance INTO v_DestinationBalance
    FROM Accounts
    WHERE AccountID = p_DestinationAccountID;

    -- Check if the source account has sufficient balance
    IF v_SourceBalance < p_Amount THEN
        DBMS_OUTPUT.PUT_LINE('Error: Insufficient funds in source account.');
        RETURN;
    END IF;

    -- Perform the fund transfer
    UPDATE Accounts
    SET Balance = Balance - p_Amount
    WHERE AccountID = p_SourceAccountID;

    UPDATE Accounts
    SET Balance = Balance + p_Amount
    WHERE AccountID = p_DestinationAccountID;

    -- Output success message
    DBMS_OUTPUT.PUT_LINE('Funds transferred successfully from account ' || p_SourceAccountID || ' to account ' || p_DestinationAccountID);

    -- Commit the transaction
    COMMIT;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Error: One or both account IDs are invalid.');
        ROLLBACK;
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
        ROLLBACK;
END TransferFunds;
/

-- Step 2: Insert sample data for testing (if necessary)
BEGIN
    -- Ensure there are accounts to work with
    INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
    VALUES (1, 1, 'Savings', 5000, SYSDATE);
    
    INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
    VALUES (2, 1, 'Checking', 2000, SYSDATE);
END;
/

-- Step 3: Example usage of the procedure
BEGIN
    -- Transfer $1000 from account 1 to account 2
    TransferFunds(p_SourceAccountID => 1, p_DestinationAccountID => 2, p_Amount => 1000);
    
    -- Display updated balances
    FOR rec IN (SELECT AccountID, Balance FROM Accounts) LOOP
        DBMS_OUTPUT.PUT_LINE('AccountID: ' || rec.AccountID || ' - Balance: ' || rec.Balance);
    END LOOP;
END;
/
