
BEGIN
    EXECUTE IMMEDIATE 'DROP PROCEDURE AddNewCustomer';
EXCEPTION
    WHEN OTHERS THEN
        NULL; 
END;
/

CREATE OR REPLACE PROCEDURE AddNewCustomer (
    p_customer_id IN NUMBER,
    p_name IN VARCHAR2,
    p_dob IN DATE,
    p_balance IN NUMBER,
    p_last_modified IN DATE
) AS
BEGIN
    BEGIN
        INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
        VALUES (p_customer_id, p_name, p_dob, p_balance, p_last_modified);
        
        COMMIT;

        DBMS_OUTPUT.PUT_LINE('Customer added successfully.');
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Customer ID ' || p_customer_id || ' already exists.');
            INSERT INTO Employees_1 (EmployeeID, Name, Position, Salary, Department, HireDate)
            VALUES (99999, 'Error', 'Error', 0, 'Error', SYSDATE); -- Dummy entry for error
        WHEN OTHERS THEN
            
            ROLLBACK;
            
            DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
            
            INSERT INTO Employees_1 (EmployeeID, Name, Position, Salary, Department, HireDate)
            VALUES (99999, 'Error', 'Error', 0, 'Error', SYSDATE); -- Dummy entry for error
    END;
END AddNewCustomer;
/


INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES (5, 'Alice Williams', TO_DATE('1982-04-12', 'YYYY-MM-DD'), 2000, SYSDATE);

BEGIN
    AddNewCustomer(p_customer_id => 6, p_name => 'Robert Johnson', p_dob => TO_DATE('1980-11-23', 'YYYY-MM-DD'), p_balance => 2500, p_last_modified => SYSDATE);
END;
/

BEGIN
    AddNewCustomer(p_customer_id => 1, p_name => 'John Doe', p_dob => TO_DATE('1985-05-15', 'YYYY-MM-DD'), p_balance => 1000, p_last_modified => SYSDATE);
END;
/

SET SERVEROUTPUT ON;

SELECT CustomerID, Name, DOB, Balance, LastModified
FROM Customers
WHERE CustomerID IN (5, 6, 1);
/
