public class SingletonTest {
    public static void main(String[] args) {
        // Retrieve instances of Logger
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        // Log messages using both instances
        logger1.log("This is a log message from logger1.");
        logger2.log("This is a log message from logger2.");

        // Check if both instances are the same
        if (logger1 == logger2) {
            System.out.println("Both instances are the same.");
        } else {
            System.out.println("Different instances were created.");
        }
        
        // Log additional messages to further demonstrate the shared instance
        logger1.log("Logging from logger1 again.");
        logger2.log("Logging from logger2 again.");
    }
}
Sin