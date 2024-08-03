-- Drop trigger if it exists to avoid errors during re-creation
BEGIN
    EXECUTE IMMEDIATE 'DROP TRIGGER UpdateCustomerLastModified';
EXCEPTION
    WHEN OTHERS THEN
        NULL; -- Ignore if the trigger does not exist
END;
/

-- Create UpdateCustomerLastModified Trigger
CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    -- Update the LastModified column to the current date
    :NEW.LastModified := SYSDATE;
END UpdateCustomerLastModified;
/

BEGIN
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (3, 'Alice Walker', TO_DATE('1980-12-05', 'YYYY-MM-DD'), 12000, SYSDATE);

    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (4, 'Bob Stone', TO_DATE('1975-03-22', 'YYYY-MM-DD'), 8000, SYSDATE);
EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        -- Ignore error if sample data already exists
        NULL;
END;
/

BEGIN
    UPDATE Customers
    SET Balance = Balance + 1000
    WHERE CustomerID = 3;
    COMMIT;
END;
/

SELECT CustomerID, Name, Balance, LastModified
FROM Customers
WHERE CustomerID = 3;
