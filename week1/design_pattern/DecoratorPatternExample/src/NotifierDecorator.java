/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public abstract class NotifierDecorator implements Notifier {
    protected Notifier decoratedNotifier;

    public NotifierDecorator(Notifier decoratedNotifier) {
        this.decoratedNotifier = decoratedNotifier;
    }

    @Override
    public void send(String message) {
        decoratedNotifier.send(message);
    }
}