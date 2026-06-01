package se.kth.bikerepair.model;

/**
 * Represents the result of a single diagnostic task performed on a bike.
 */
public class DiagnosticTaskResult {
    private String taskName;
    private String resultText;
    private Amount cost;

    /**
     * Creates a new instance with the specified diagnostic task details.
     * @param taskName The name of the diagnostic task.
     * @param resultText The result description of the diagnostic task.
     * @param cost The cost associated with this diagnostic task.
     */
    public DiagnosticTaskResult(String taskName, String resultText, Amount cost) {
        this.taskName = taskName;
        this.resultText = resultText;
        this.cost = cost;
    }

    /**
     * Returns the cost of this diagnostic task result.
     * @return The cost.
     */
    public Amount getCost() {
        return cost;
    }

    /**
     * Returns a string representation of this diagnostic task result.
     * @return The diagnostic task result details as a string.
     */
    @Override
    public String toString() {
        return taskName + ": " + resultText + " (" + cost + ")";
    }
}