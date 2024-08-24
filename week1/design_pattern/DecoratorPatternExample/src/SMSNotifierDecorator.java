/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class SMSNotifierDecorator extends NotifierDecorator {
    public SMSNotifierDecorator(Notifier decoratedNotifier) {
        super(decoratedNotifier);
    }

    @Override
    public void send(String message) {
        super.send(message);
        sendSMS(message);
    }

    private void sendSMS(String message) {
        System.out.println("Sending SMS with message: " + message);
    }
}