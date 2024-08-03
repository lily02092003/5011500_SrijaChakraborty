SET SERVEROUTPUT ON

CREATE OR REPLACE PROCEDURE UpdateSalary (
    p_employee_id IN NUMBER,
    p_percentage IN NUMBER
) AS
    v_current_salary NUMBER;
BEGIN
    IF p_percentage <= 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Percentage must be positive.');
    END IF;

    BEGIN
        SELECT Salary INTO v_current_salary
        FROM Employees_1
        WHERE EmployeeID = p_employee_id
        FOR UPDATE;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Employee ID ' || p_employee_id || ' not found.');
            INSERT INTO Employees_1 (EmployeeID, Name, Position, Salary, Department, HireDate)
            VALUES (99999, 'Error', 'Error', 0, 'Error', SYSDATE); -- Dummy entry for error
            RAISE_APPLICATION_ERROR(-20002, 'Employee ID not found.');
    END;

    UPDATE Employees_1
    SET Salary = Salary + (Salary * p_percentage / 100)
    WHERE EmployeeID = p_employee_id;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Salary updated successfully.');

EXCEPTION
    WHEN OTHERS THEN
        
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
        INSERT INTO Employees_1 (EmployeeID, Name, Position, Salary, Department, HireDate)
        VALUES (99999, 'Error', 'Error', 0, 'Error', SYSDATE); 
/


BEGIN
    UpdateSalary(p_employee_id => 3, p_percentage => 10);
END;
/

BEGIN
    UpdateSalary(p_employee_id => 999, p_percentage => 10);
END;
/
SET SERVEROUTPUT ON;

SELECT EmployeeID, Name, Position, Salary
FROM Employees_1
WHERE EmployeeID IN (3, 4) OR EmployeeID = 999;
/