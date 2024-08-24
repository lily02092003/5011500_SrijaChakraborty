/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class DecoratorPatternTest {
    public static void main(String[] args) {
        // Create a basic EmailNotifier
        Notifier emailNotifier = new EmailNotifier();

        // Decorate with SMSNotifier
        Notifier smsNotifier = new SMSNotifierDecorator(emailNotifier);

        // Decorate with SlackNotifier
        Notifier slackNotifier = new SlackNotifierDecorator(smsNotifier);

        // Send a notification
        slackNotifier.send("Hello, World!");
    }
}