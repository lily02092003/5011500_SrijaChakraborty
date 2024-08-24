
BEGIN
    EXECUTE IMMEDIATE 'DROP PROCEDURE UpdateEmployeeBonus';
EXCEPTION
    WHEN OTHERS THEN
        NULL;
END;
/

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_department IN VARCHAR2,
    p_bonus_percentage IN NUMBER
) AS
BEGIN
    UPDATE Employees_1
    SET Salary = Salary + (Salary * p_bonus_percentage / 100)
    WHERE Department = p_department;
    
    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Employee bonuses updated successfully in department ' || p_department || '.');
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
END UpdateEmployeeBonus;
/

INSERT INTO Employees_1 (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (3, 'Charlie Davis', 'Analyst', 55000, 'Finance', SYSDATE);

INSERT INTO Employees_1 (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (4, 'Diana Evans', 'Clerk', 45000, 'Finance', SYSDATE);

INSERT INTO Employees_1 (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (5, 'Evelyn Green', 'Manager', 70000, 'IT', SYSDATE);
/

BEGIN
    UpdateEmployeeBonus(p_department => 'Finance', p_bonus_percentage => 10);
END;
/

SET SERVEROUTPUT ON;

SELECT EmployeeID, Name, Position, Salary, Department
FROM Employees_1
WHERE Department = 'Finance';
/
