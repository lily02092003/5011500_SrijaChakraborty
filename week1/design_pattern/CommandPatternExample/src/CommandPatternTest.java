/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class CommandPatternTest {
    public static void main(String[] args) {
        // Create a light instance
        Light light = new Light();

        // Create command instances
        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);

        // Create a remote control instance
        RemoteControl remote = new RemoteControl();

        // Test turning the light on
        remote.setCommand(lightOn);
        remote.pressButton(); // Output: The light is ON

        // Test turning the light off
        remote.setCommand(lightOff);
        remote.pressButton(); // Output: The light is OFF
    }
}