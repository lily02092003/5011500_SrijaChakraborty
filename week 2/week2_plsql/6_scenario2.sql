DECLARE
    c_annual_fee CONSTANT NUMBER := 50;

    CURSOR cur_accounts IS
        SELECT AccountID, CustomerID, AccountType, Balance
        FROM Accounts
        ORDER BY CustomerID;

    rec_account cur_accounts%ROWTYPE;

BEGIN
    OPEN cur_accounts;

    LOOP
        FETCH cur_accounts INTO rec_account;

        EXIT WHEN cur_accounts%NOTFOUND;

        UPDATE Accounts
        SET Balance = Balance - c_annual_fee,
            LastModified = SYSDATE
        WHERE AccountID = rec_account.AccountID;

        DBMS_OUTPUT.PUT_LINE('Account ID: ' || rec_account.AccountID);
        DBMS_OUTPUT.PUT_LINE('Customer ID: ' || rec_account.CustomerID);
        DBMS_OUTPUT.PUT_LINE('Account Type: ' || rec_account.AccountType);
        DBMS_OUTPUT.PUT_LINE('Old Balance: ' || rec_account.Balance);
        DBMS_OUTPUT.PUT_LINE('New Balance: ' || (rec_account.Balance - c_annual_fee));
        DBMS_OUTPUT.PUT_LINE('Annual Fee Deducted: ' || c_annual_fee);
        DBMS_OUTPUT.PUT_LINE('-----------------------------------------');
    END LOOP;

    CLOSE cur_accounts;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Annual maintenance fees have been applied to all accounts.');

END;
/
