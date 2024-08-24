
CREATE OR REPLACE PACKAGE CustomerManagement AS
    
    PROCEDURE AddCustomer(
        p_CustomerID NUMBER,
        p_Name VARCHAR2,
        p_DOB DATE,
        p_Balance NUMBER
    );

    PROCEDURE UpdateCustomer(
        p_CustomerID NUMBER,
        p_Name VARCHAR2,
        p_DOB DATE,
        p_Balance NUMBER
    );

    FUNCTION GetCustomerBalance(
        p_CustomerID NUMBER
    ) RETURN NUMBER;
END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

    PROCEDURE AddCustomer(
        p_CustomerID NUMBER,
        p_Name VARCHAR2,
        p_DOB DATE,
        p_Balance NUMBER
    ) IS
    BEGIN
        INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
        VALUES (p_CustomerID, p_Name, p_DOB, p_Balance, SYSDATE);

        DBMS_OUTPUT.PUT_LINE('Customer added successfully: ' || p_Name);
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Error: Customer with ID ' || p_CustomerID || ' already exists.');
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
    END AddCustomer;

    PROCEDURE UpdateCustomer(
        p_CustomerID NUMBER,
        p_Name VARCHAR2,
        p_DOB DATE,
        p_Balance NUMBER
    ) IS
    BEGIN
        UPDATE Customers
        SET Name = p_Name, DOB = p_DOB, Balance = p_Balance, LastModified = SYSDATE
        WHERE CustomerID = p_CustomerID;

        IF SQL%ROWCOUNT = 0 THEN
            DBMS_OUTPUT.PUT_LINE('No customer found with ID ' || p_CustomerID);
        ELSE
            DBMS_OUTPUT.PUT_LINE('Customer updated successfully: ' || p_Name);
        END IF;
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
    END UpdateCustomer;

    FUNCTION GetCustomerBalance(
        p_CustomerID NUMBER
    ) RETURN NUMBER IS
        v_Balance NUMBER;
    BEGIN
        SELECT Balance INTO v_Balance
        FROM Customers
        WHERE CustomerID = p_CustomerID;

        RETURN v_Balance;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Error: No customer found with ID ' || p_CustomerID);
            RETURN NULL;
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
            RETURN NULL;
    END GetCustomerBalance;

END CustomerManagement;
/

BEGIN
    CustomerManagement.AddCustomer(1, 'John Doe', TO_DATE('1985-05-15', 'YYYY-MM-DD'), 1000);
    CustomerManagement.AddCustomer(2, 'Jane Smith', TO_DATE('1990-07-20', 'YYYY-MM-DD'), 2000);
    CustomerManagement.AddCustomer(3, 'Alice Johnson', TO_DATE('1975-08-10', 'YYYY-MM-DD'), 3000);
    CustomerManagement.AddCustomer(4, 'Bob Brown', TO_DATE('1980-01-22', 'YYYY-MM-DD'), 4000);
END;
/

BEGIN
    CustomerManagement.UpdateCustomer(2, 'Jane Doe', TO_DATE('1990-07-20', 'YYYY-MM-DD'), 2500);

    DBMS_OUTPUT.PUT_LINE('Balance for Customer ID 2: ' || CustomerManagement.GetCustomerBalance(2));
END;
/
