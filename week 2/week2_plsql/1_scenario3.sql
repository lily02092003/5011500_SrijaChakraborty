CLEAR SCREEN

SET SERVEROUTPUT ON;

DECLARE
    CURSOR c_loans_due IS
        SELECT c.CustomerID, c.Name, l.LoanID, l.EndDate
        FROM Customers c
        JOIN Loans l ON c.CustomerID = l.CustomerID
        WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30;
BEGIN
    FOR rec IN c_loans_due LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder: Loan Due Soon');
        DBMS_OUTPUT.PUT_LINE('Customer ID: ' || rec.CustomerID);
        DBMS_OUTPUT.PUT_LINE('Customer Name: ' || rec.Name);
        DBMS_OUTPUT.PUT_LINE('Loan ID: ' || rec.LoanID);
        DBMS_OUTPUT.PUT_LINE('Due Date: ' || TO_CHAR(rec.EndDate, 'YYYY-MM-DD'));
        DBMS_OUTPUT.PUT_LINE('-------------------------------');
    END LOOP;
END;
/
