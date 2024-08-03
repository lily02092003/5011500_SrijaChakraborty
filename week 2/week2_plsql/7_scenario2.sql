
CREATE OR REPLACE PACKAGE EmployeeManagement AS
    PROCEDURE HireEmployee(
        p_EmployeeID NUMBER,
        p_Name VARCHAR2,
        p_Position VARCHAR2,
        p_Salary NUMBER,
        p_Department VARCHAR2,
        p_HireDate DATE
    );

    PROCEDURE UpdateEmployee(
        p_EmployeeID NUMBER,
        p_Name VARCHAR2,
        p_Position VARCHAR2,
        p_Salary NUMBER,
        p_Department VARCHAR2
    );

    FUNCTION CalculateAnnualSalary(
        p_EmployeeID NUMBER
    ) RETURN NUMBER;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS

    PROCEDURE HireEmployee(
        p_EmployeeID NUMBER,
        p_Name VARCHAR2,
        p_Position VARCHAR2,
        p_Salary NUMBER,
        p_Department VARCHAR2,
        p_HireDate DATE
    ) IS
    BEGIN
        INSERT INTO Employees_1 (EmployeeID, Name, Position, Salary, Department, HireDate)
        VALUES (p_EmployeeID, p_Name, p_Position, p_Salary, p_Department, p_HireDate);

        DBMS_OUTPUT.PUT_LINE('Employee hired successfully: ' || p_Name);
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Error: Employee with ID ' || p_EmployeeID || ' already exists.');
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
    END HireEmployee;

    PROCEDURE UpdateEmployee(
        p_EmployeeID NUMBER,
        p_Name VARCHAR2,
        p_Position VARCHAR2,
        p_Salary NUMBER,
        p_Department VARCHAR2
    ) IS
    BEGIN
        UPDATE Employees_1
        SET Name = p_Name, Position = p_Position, Salary = p_Salary, Department = p_Department
        WHERE EmployeeID = p_EmployeeID;

        IF SQL%ROWCOUNT = 0 THEN
            DBMS_OUTPUT.PUT_LINE('No employee found with ID ' || p_EmployeeID);
        ELSE
            DBMS_OUTPUT.PUT_LINE('Employee updated successfully: ' || p_Name);
        END IF;
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
    END UpdateEmployee;
	
    FUNCTION CalculateAnnualSalary(
        p_EmployeeID NUMBER
    ) RETURN NUMBER IS
        v_AnnualSalary NUMBER;
    BEGIN
        SELECT Salary * 12 INTO v_AnnualSalary
        FROM Employees_1
        WHERE EmployeeID = p_EmployeeID;

        RETURN v_AnnualSalary;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Error: No employee found with ID ' || p_EmployeeID);
            RETURN NULL;
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
            RETURN NULL;
    END CalculateAnnualSalary;

END EmployeeManagement;
/

BEGIN
    EmployeeManagement.HireEmployee(1, 'Alice Johnson', 'Manager', 7000, 'HR', TO_DATE('2015-06-15', 'YYYY-MM-DD'));
    EmployeeManagement.HireEmployee(2, 'Bob Brown', 'Developer', 5000, 'IT', TO_DATE('2017-03-20', 'YYYY-MM-DD'));
    EmployeeManagement.HireEmployee(3, 'Charlie Davis', 'Analyst', 4000, 'Finance', TO_DATE('2019-11-12', 'YYYY-MM-DD'));
    EmployeeManagement.HireEmployee(4, 'Diana Evans', 'Developer', 6000, 'IT', TO_DATE('2021-05-10', 'YYYY-MM-DD'));
END;
/

BEGIN
    EmployeeManagement.UpdateEmployee(2, 'Bob Brown', 'Senior Developer', 5500, 'IT');

    DBMS_OUTPUT.PUT_LINE('Annual Salary for Employee ID 2: ' || EmployeeManagement.CalculateAnnualSalary(2));
END;
/
