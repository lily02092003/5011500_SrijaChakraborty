
BEGIN
    EXECUTE IMMEDIATE 'DROP FUNCTION CalculateMonthlyInstallment';
EXCEPTION
    WHEN OTHERS THEN
        NULL; 
END;
/
CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment (
    p_loan_amount IN NUMBER,
    p_interest_rate IN NUMBER,
    p_duration_years IN NUMBER
) RETURN NUMBER IS
    v_monthly_rate NUMBER;
    v_number_of_payments NUMBER;
    v_monthly_installment NUMBER;
BEGIN
    
    v_monthly_rate := p_interest_rate / 12 / 100;
    v_number_of_payments := p_duration_years * 12;

    IF v_monthly_rate > 0 THEN
        v_monthly_installment := p_loan_amount * (v_monthly_rate * POWER(1 + v_monthly_rate, v_number_of_payments)) / (POWER(1 + v_monthly_rate, v_number_of_payments) - 1);
    ELSE
        v_monthly_installment := p_loan_amount / v_number_of_payments;
    END IF;

    RETURN v_monthly_installment;
END CalculateMonthlyInstallment;
/

INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate)
VALUES (2, 1, 50000, 6, SYSDATE, ADD_MONTHS(SYSDATE, 60));

INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate)
VALUES (3, 2, 100000, 5, SYSDATE, ADD_MONTHS(SYSDATE, 120));
/

SET SERVEROUTPUT ON;

DECLARE
    v_installment NUMBER;
BEGIN
    FOR rec IN (SELECT LoanAmount, InterestRate, (MONTHS_BETWEEN(EndDate, StartDate) / 12) AS DurationYears FROM Loans) LOOP
        v_installment := CalculateMonthlyInstallment(rec.LoanAmount, rec.InterestRate, rec.DurationYears);
        DBMS_OUTPUT.PUT_LINE('Loan Amount: ' || rec.LoanAmount || ' Interest Rate: ' || rec.InterestRate || '% Duration: ' || rec.DurationYears || ' years - Monthly Installment: ' || v_installment);
    END LOOP;
END;
/
