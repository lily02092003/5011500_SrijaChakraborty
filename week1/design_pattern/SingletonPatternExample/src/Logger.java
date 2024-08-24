public class Logger {
    // Step 1: Create a private static instance of Logger (initialized to null)
    private static Logger instance;

    // Step 2: Ensure the constructor is private to prevent instantiation from outside
    private Logger() {
        // Initialization code (if needed)
    }

    // Step 3: Provide a public static method to get the instance of Logger
    public static synchronized Logger getInstance() {
        // Create the instance if it does not exist
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // Example method to log messages
    public void log(String message) {
        System.out.println("Log: " + message);
    }
}
