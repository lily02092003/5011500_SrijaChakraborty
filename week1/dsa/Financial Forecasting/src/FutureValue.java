/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finance;

public class FutureValue {

    // Tail-recursive helper method
    private static double predictFutureValueHelper(double currentValue, double growthRate, int periods) {
        if (periods == 0) {
            return currentValue;
        } else {
            return predictFutureValueHelper(currentValue * (1 + growthRate), growthRate, periods - 1);
        }
    }

    // Public method to calculate the future value using tail recursion
    public static double predictFutureValue(double initialValue, double growthRate, int periods) {
        return predictFutureValueHelper(initialValue, growthRate, periods);
    }

    public static void main(String[] args) {
        // Example usage
        double initialValue = 1000.0; // Initial value
        double growthRate = 0.07;     // Growth rate (7%)
        int periods = 5;              // Number of periods (years)

        double futureValue = predictFutureValue(initialValue, growthRate, periods);
        System.out.println("Future Value: " + futureValue);
    }
}

