/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class FinancialForecasting {

    // Recursive method to calculate the future value
    public static double calculateFutureValue(double principal, double rate, int periods) {
        // Base case: if no periods left, return the principal as the future value
        if (periods == 0) {
            return principal;
        } else {
            // Recursive case: calculate future value for one less period and apply the interest rate
            return calculateFutureValue(principal * (1 + rate), rate, periods - 1);
        }
    }

    public static void main(String[] args) {
        // Example usage
        double principal = 1000.0; // Initial investment
        double rate = 0.05;        // Annual interest rate (5%)
        int periods = 10;          // Number of years

        double futureValue = calculateFutureValue(principal, rate, periods);
        System.out.println("Future Value: " + futureValue);
    }
}
