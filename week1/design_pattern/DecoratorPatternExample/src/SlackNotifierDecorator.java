/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class SlackNotifierDecorator extends NotifierDecorator {
    public SlackNotifierDecorator(Notifier decoratedNotifier) {
        super(decoratedNotifier);
    }

    @Override
    public void send(String message) {
        super.send(message);
        sendSlack(message);
    }

    private void sendSlack(String message) {
        System.out.println("Sending Slack message with content: " + message);
    }
}