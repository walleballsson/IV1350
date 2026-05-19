package view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Handles error messages shown to the user and logged to a file.
 */
public class ErrorMessageHandler {
    private static final String LOG_FILE = "error-log.txt";

    /**
     * Shows an informative error message to the user.
     * @param message The message to display.
     */
    public void showErrorMessage(String message) {
        System.out.println("\n[ERROR] " + message);
    }

    /**
     * Logs a technical error to the log file, for developers.
     * @param exception The exception to log.
     */
    public void logException(Exception exception) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            writer.println("--- Error at " + LocalDateTime.now() + " ---");
            exception.printStackTrace(writer);
            writer.println();
        } catch (IOException e) {
            System.out.println("[LOGGING FAILED] Could not write to log file: " 
                               + e.getMessage());
        }
    }
}