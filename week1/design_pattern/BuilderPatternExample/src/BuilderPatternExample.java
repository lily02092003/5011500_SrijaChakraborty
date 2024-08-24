// BuilderPatternTest.java
public class BuilderPatternExample {
    public static void main(String[] args) {
        // Create a Computer instance with required parameters and some optional parameters
        Computer gamingPC = new Computer.Builder("Intel i9", 32)
                .setStorage(1000)
                .setGPU("NVIDIA GeForce RTX 3080")
                .setSSD(true)
                .build();

        // Create another Computer instance with different configurations
        Computer officePC = new Computer.Builder("AMD Ryzen 5", 16)
                .setStorage(512)
                .setGPU("Integrated Graphics")
                .setSSD(false)
                .build();

        // Print out the configurations of both computers
        System.out.println(gamingPC);
        System.out.println(officePC);
    }
}
