package se.kth.bikerepair.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a diagnostic report containing the results of all diagnostic tasks.
 */
public class DiagnosticReport {
    private List<DiagnosticTaskResult> results;

    /**
     * Creates a new empty diagnostic report.
     */
    public DiagnosticReport() {
        results = new ArrayList<>();
    }

    /**
     * Adds a diagnostic task result to this report.
     * @param result The diagnostic task result to add.
     */
    public void addResult(DiagnosticTaskResult result) {
        results.add(result);
    }

    /**
     * Calculates the total cost of all diagnostic tasks in this report.
     * @return The total diagnostic cost.
     */
    public Amount calculateDiagnosticCost() {
        Amount total = new Amount(0);
        for (DiagnosticTaskResult result : results) {
            total = total.add(result.getCost());
        }
        return total;
    }

    /**
     * Returns a string representation of this diagnostic report.
     * @return The diagnostic report details as a string.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (DiagnosticTaskResult result : results) {
            sb.append(result).append("\n");
        }
        return sb.toString();
    }
}