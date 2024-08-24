CLEAR SCREEN
SET SERVEROUTPUT ON;

BEGIN
    UPDATE Customers
    SET IsVIP = 'Y'
    WHERE Balance > 10000;

    FOR rec IN (SELECT CustomerID, Name, Balance, IsVIP
                FROM Customers
                WHERE IsVIP = 'Y') LOOP
        DBMS_OUTPUT.PUT_LINE('Customer ID: ' || rec.CustomerID);
        DBMS_OUTPUT.PUT_LINE('Customer Name: ' || rec.Name);
        DBMS_OUTPUT.PUT_LINE('Balance: ' || rec.Balance);
        DBMS_OUTPUT.PUT_LINE('Is VIP: ' || rec.IsVIP);
        DBMS_OUTPUT.PUT_LINE('-------------------------------');
    END LOOP;
    
    COMMIT;
END;
/
