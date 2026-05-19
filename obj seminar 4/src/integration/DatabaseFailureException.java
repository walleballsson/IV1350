package integration;

/**
 * Thrown when the database cannot be reached or called.
 * This indicates a program failure, not a user error.
 */
public class DatabaseFailureException extends Exception {
    private String operation;

    /**
     * Creates a new instance with information about what operation failed.
     * @param operation The operation that failed.
     * @param cause The original exception that caused the failure.
     */
    public DatabaseFailureException(String operation, Exception cause) {
        super("Database failure during: " + operation, cause);
        this.operation = operation;
    }

    /**
     * Returns the operation that failed.
     * @return The operation name.
     */
    public String getOperation() {
        return operation;
    }
}