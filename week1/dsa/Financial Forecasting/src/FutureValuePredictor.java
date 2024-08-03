/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class FutureValuePredictor {

    // Recursive method to calculate the future value based on growth rate
    public static double predictFutureValue(double initialValue, double growthRate, int periods) {
        // Base case: if no periods left, return the initial value
        if (periods == 0) {
            return initialValue;
        } else {
            // Recursive case: calculate future value for one less period
            return predictFutureValue(initialValue * (1 + growthRate), growthRate, periods - 1);
        }
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
