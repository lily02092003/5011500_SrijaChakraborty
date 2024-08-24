
DECLARE
    
    CURSOR cur_monthly_transactions IS
        SELECT t.TransactionID, t.AccountID, t.TransactionDate, t.Amount, t.TransactionType, a.CustomerID, c.Name
        FROM Transactions t
        JOIN Accounts a ON t.AccountID = a.AccountID
        JOIN Customers c ON a.CustomerID = c.CustomerID
        WHERE TRUNC(t.TransactionDate, 'MM') = TRUNC(SYSDATE, 'MM')
        ORDER BY c.CustomerID, t.TransactionDate;

    rec_transaction cur_monthly_transactions%ROWTYPE;

    v_current_customer_id Customers.CustomerID%TYPE := NULL;
    v_total_balance NUMBER := 0;

BEGIN
    
    OPEN cur_monthly_transactions;

    
    LOOP
        FETCH cur_monthly_transactions INTO rec_transaction;

        EXIT WHEN cur_monthly_transactions%NOTFOUND;

        IF v_current_customer_id IS NULL OR v_current_customer_id != rec_transaction.CustomerID THEN
            
            IF v_current_customer_id IS NOT NULL THEN
                DBMS_OUTPUT.PUT_LINE('Total Balance for Customer ID: ' || v_current_customer_id || ' is ' || v_total_balance);
                DBMS_OUTPUT.PUT_LINE('-----------------------------------------');
            END IF;

            v_current_customer_id := rec_transaction.CustomerID;
            v_total_balance := 0;

            DBMS_OUTPUT.PUT_LINE('Customer ID: ' || rec_transaction.CustomerID);
            DBMS_OUTPUT.PUT_LINE('Customer Name: ' || rec_transaction.Name);
            DBMS_OUTPUT.PUT_LINE('Transactions for the month:');
            DBMS_OUTPUT.PUT_LINE('Transaction ID | Transaction Date | Amount | Type');
            DBMS_OUTPUT.PUT_LINE('-----------------------------------------');
        END IF;
        DBMS_OUTPUT.PUT_LINE(rec_transaction.TransactionID || ' | ' ||
                             TO_CHAR(rec_transaction.TransactionDate, 'DD-MON-YYYY') || ' | ' ||
                             rec_transaction.Amount || ' | ' ||
                             rec_transaction.TransactionType);

        IF rec_transaction.TransactionType = 'Deposit' THEN
            v_total_balance := v_total_balance + rec_transaction.Amount;
        ELSIF rec_transaction.TransactionType = 'Withdrawal' THEN
            v_total_balance := v_total_balance - rec_transaction.Amount;
        END IF;
    END LOOP;

    IF v_current_customer_id IS NOT NULL THEN
        DBMS_OUTPUT.PUT_LINE('Total Balance for Customer ID: ' || v_current_customer_id || ' is ' || v_total_balance);
        DBMS_OUTPUT.PUT_LINE('-----------------------------------------');
    END IF;

    CLOSE cur_monthly_transactions;
END;
/
