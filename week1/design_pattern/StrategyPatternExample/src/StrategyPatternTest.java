/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class StrategyPatternTest {
    public static void main(String[] args) {
        // Create different payment strategies
        PaymentStrategy creditCardPayment = new CreditCardPayment("1234-5678-9876-5432", "John Doe");
        PaymentStrategy payPalPayment = new PayPalPayment("john.doe@example.com");

        // Create context with Credit Card payment strategy
        PaymentContext context = new PaymentContext(creditCardPayment);
        context.executePayment(100.0); // Output: Paying $100.0 using Credit Card.

        // Change strategy to PayPal payment
        context = new PaymentContext(payPalPayment);
        context.executePayment(200.0); // Output: Paying $200.0 using PayPal.
    }
}