package se.kth.bikerepair.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a repair order for an electric bike.
 */
public class RepairOrder {
    private int id;
    private LocalDate creationDate;
    private String problemDescription;
    private Customer customer;
    private Bike bike;
    private DiagnosticReport diagnosticReport;
    private List<RepairTask> repairTasks;
    private String state;

    /**
     * Creates a new repair order with the specified details.
     * @param id The unique identifier of this repair order.
     * @param customer The customer who owns the bike.
     * @param bike The bike to be repaired.
     * @param problemDescription A description of the problem.
     */
    public RepairOrder(int id, Customer customer, Bike bike, String problemDescription) {
        this.id = id;
        this.customer = customer;
        this.bike = bike;
        this.problemDescription = problemDescription;
        this.creationDate = LocalDate.now();
        this.diagnosticReport = new DiagnosticReport();
        this.repairTasks = new ArrayList<>();
        this.state = "NEWLY_CREATED";
    }

    /**
     * Adds a diagnostic task result to this repair order.
     * @param result The diagnostic task result to add.
     */
    public void addDiagnosticTaskResult(DiagnosticTaskResult result) {
        diagnosticReport.addResult(result);
    }

    /**
     * Marks the diagnosis as finished and sets state to ready for approval.
     */
    public void finishDiagnosis() {
        this.state = "READY_FOR_APPROVAL";
    }

    /**
     * Approves the repair and sets state to approved.
     */
    public void approve() {
        this.state = "APPROVED";
    }

    /**
     * Rejects the repair and sets state to rejected.
     */
    public void reject() {
        this.state = "REJECTED";
    }

    /**
     * Adds a repair task to this repair order.
     * @param task The repair task to add.
     */
    public void addRepairTask(RepairTask task) {
        repairTasks.add(task);
    }

    /**
     * Marks a repair task as completed.
     * @param taskName The name of the task to mark as completed.
     */
    public void markRepairTaskCompleted(String taskName) {
        for (RepairTask task : repairTasks) {
            if (task.getName().equals(taskName)) {
                task.markCompleted();
            }
        }
    }

    /**
     * Completes the repair and sets state to completed.
     * @return The total cost of the repair.
     */
    public Amount complete() {
        this.state = "COMPLETED";
        return calculateTotalCost();
    }

    /**
     * Calculates the total cost of this repair order.
     * @return The total cost.
     */
    private Amount calculateTotalCost() {
        Amount total = diagnosticReport.calculateDiagnosticCost();
        for (RepairTask task : repairTasks) {
            total = total.add(task.getCost());
        }
        return total;
    }

    /**
     * Registers payment for this repair order and returns a receipt.
     * @param paidAmount The amount paid by the customer.
     * @return A receipt for the payment.
     */
    public Receipt registerPayment(Amount paidAmount) {
        this.state = "PAID";
        return new Receipt(id, calculateTotalCost());
    }

    /**
     * Returns the unique identifier of this repair order.
     * @return The order ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the current state of this repair order.
     * @return The state.
     */
    public String getState() {
        return state;
    }
    /**
     * Returns the name of the customer who owns the bike.
     * @return The customer name.
     */
    public String getCustomerName() {
        return customer.getName();
    }

    /**
     * Returns the brand of the bike being repaired.
     * @return The bike brand.
     */
    public String getBikeBrand() {
        return bike.getBrand();
    }

    /**
     * Returns the model of the bike being repaired.
     * @return The bike model.
     */
    public String getBikeModel() {
        return bike.getModel();
    }

    /**
     * Returns the problem description for this repair order.
     * @return The problem description.
     */
    public String getProblemDescription() {
        return problemDescription;
    }

    /**
     * Returns the current total cost as a formatted string.
     * @return The total cost string.
     */
public String getTotalCostString() {
    return calculateTotalCost().toString();
}

    /**
     * Returns a string representation of this repair order.
     * @return The repair order details as a string.
     */
    @Override
    public String toString() {
        return "RepairOrder ID: " + id + "\n" +
               "Customer: " + customer + "\n" +
               "Bike: " + bike + "\n" +
               "Problem: " + problemDescription + "\n" +
               "State: " + state + "\n" +
               "Diagnostic Report:\n" + diagnosticReport;
    }
}