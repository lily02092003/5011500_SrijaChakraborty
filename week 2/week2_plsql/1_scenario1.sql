CLEAR SCREEN
SET SERVEROUTPUT ON;

DECLARE
    CURSOR c_customers IS
        SELECT c.CustomerID, c.Name, c.DOB, l.LoanID, l.InterestRate
        FROM Customers c
        JOIN Loans l ON c.CustomerID = l.CustomerID;
    v_current_date DATE := SYSDATE;
BEGIN
    FOR rec IN c_customers LOOP
        IF MONTHS_BETWEEN(v_current_date, rec.DOB) / 12 > 60 THEN
            UPDATE Loans
            SET InterestRate = rec.InterestRate - 1
            WHERE LoanID = rec.LoanID;
            
            DBMS_OUTPUT.PUT_LINE('Customer ID: ' || rec.CustomerID);
            DBMS_OUTPUT.PUT_LINE('Customer Name: ' || rec.Name);
            DBMS_OUTPUT.PUT_LINE('Loan ID: ' || rec.LoanID);
            DBMS_OUTPUT.PUT_LINE('Old Interest Rate: ' || rec.InterestRate);
            DBMS_OUTPUT.PUT_LINE('New Interest Rate: ' || (rec.InterestRate - 1));
            DBMS_OUTPUT.PUT_LINE('-------------------------------');
        END IF;
    END LOOP;
    COMMIT;
END;
/
