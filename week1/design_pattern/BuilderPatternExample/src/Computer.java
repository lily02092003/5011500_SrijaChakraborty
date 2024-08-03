// Computer.java
public class Computer {
    // Required parameters
    private final String CPU;
    private final int RAM;

    // Optional parameters
    private final int Storage;
    private final String GPU;
    private final boolean isSSD;

    // Private constructor to be used by the Builder class
    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.Storage = builder.Storage;
        this.GPU = builder.GPU;
        this.isSSD = builder.isSSD;
    }

    @Override
    public String toString() {
        return "Computer [CPU=" + CPU + ", RAM=" + RAM + "GB, Storage=" + Storage + "GB, GPU=" + GPU + ", SSD=" + isSSD + "]";
    }

    // Static nested Builder class
    public static class Builder {
        // Required parameters
        private final String CPU;
        private final int RAM;

        // Optional parameters
        private int Storage = 0;
        private String GPU = "";
        private boolean isSSD = false;

        // Constructor with required parameters
        public Builder(String CPU, int RAM) {
            this.CPU = CPU;
            this.RAM = RAM;
        }

        // Setter methods for optional parameters
        public Builder setStorage(int Storage) {
            this.Storage = Storage;
            return this;
        }

        public Builder setGPU(String GPU) {
            this.GPU = GPU;
            return this;
        }

        public Builder setSSD(boolean isSSD) {
            this.isSSD = isSSD;
            return this;
        }

        // Build method to return an instance of Computer
        public Computer build() {
            return new Computer(this);
        }
    }
}
