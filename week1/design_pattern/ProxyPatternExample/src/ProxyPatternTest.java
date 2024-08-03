/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class ProxyPatternTest {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("image1.jpg");
        Image image2 = new ProxyImage("image2.jpg");

        // The image is loaded and displayed only when requested
        image1.display(); // Loading and displaying image1
        image1.display(); // Displaying image1 (no loading)

        image2.display(); // Loading and displaying image2
        image2.display(); // Displaying image2 (no loading)
    }
}