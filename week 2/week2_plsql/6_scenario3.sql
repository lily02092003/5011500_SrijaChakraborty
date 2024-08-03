
DECLARE
   
    c_interest_rate_adjustment CONSTANT NUMBER := 0.5;

    CURSOR cur_loans IS
        SELECT LoanID, CustomerID, InterestRate
        FROM Loans
        ORDER BY CustomerID;

    rec_loan cur_loans%ROWTYPE;

BEGIN
    OPEN cur_loans;

    LOOP
        FETCH cur_loans INTO rec_loan;

        EXIT WHEN cur_loans%NOTFOUND;

        DECLARE
            v_new_interest_rate NUMBER;
        BEGIN
            v_new_interest_rate := rec_loan.InterestRate + c_interest_rate_adjustment;

            UPDATE Loans
            SET InterestRate = v_new_interest_rate
            WHERE LoanID = rec_loan.LoanID;

            DBMS_OUTPUT.PUT_LINE('Loan ID: ' || rec_loan.LoanID);
            DBMS_OUTPUT.PUT_LINE('Customer ID: ' || rec_loan.CustomerID);
            DBMS_OUTPUT.PUT_LINE('Old Interest Rate: ' || rec_loan.InterestRate || '%');
            DBMS_OUTPUT.PUT_LINE('New Interest Rate: ' || v_new_interest_rate || '%');
            DBMS_OUTPUT.PUT_LINE('Interest Rate Adjustment: ' || c_interest_rate_adjustment || '%');
            DBMS_OUTPUT.PUT_LINE('-----------------------------------------');
        END;
    END LOOP;

    CLOSE cur_loans;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Interest rates for all loans have been updated based on the new policy.');

END;
/
