package se.kth.bikerepair.model;

import se.kth.bikerepair.controller.RepairOrderObserver;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import se.kth.bikerepair.view.RepairOrderDTO;

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
    private List<RepairOrderObserver> observers = new ArrayList<>();

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
        notifyObservers();
    }

    /**
     * Marks the diagnosis as finished and sets state to ready for approval.
     */
    public void finishDiagnosis() {
        this.state = "READY_FOR_APPROVAL";
        notifyObservers();
    }

    /**
     * Approves the repair and sets state to approved.
     */
    public void approve() {
        this.state = "APPROVED";
        notifyObservers();
    }

    /**
     * Rejects the repair and sets state to rejected.
     */
    public void reject() {
        this.state = "REJECTED";
        notifyObservers();
    }

    /**
     * Adds a repair task to this repair order.
     * @param task The repair task to add.
     */
    public void addRepairTask(RepairTask task) {
        repairTasks.add(task);
        notifyObservers();
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
        notifyObservers();
    }

    /**
     * Completes the repair and sets state to completed.
     * @return The total cost of the repair.
     */
    public Amount complete() {
        this.state = "COMPLETED";
        notifyObservers();
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
        notifyObservers();
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
     * Returns a string representation of this repair order.
     * @return The repair order details as a string.
     */
    @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("RepairOrder ID: ").append(id).append("\n");
            sb.append("Customer: ").append(customer).append("\n");
            sb.append("Bike: ").append(bike).append("\n");
            sb.append("Problem: ").append(problemDescription).append("\n");
            sb.append("State: ").append(state).append("\n");
            sb.append("Diagnostic Report:\n").append(diagnosticReport);
            sb.append("Repair Tasks:\n");
            for (RepairTask task : repairTasks) {
                sb.append("  ").append(task).append("\n");
            }
            return sb.toString();
        }

        /**
         * Adds an observer to be notified when this repair order is updated.
         * @param observer The observer to add.
         */
        public void addObserver(RepairOrderObserver observer) {
            observers.add(observer);
        }

        /**
         * Notifies all observers that this repair order has been updated.
         */
        private void notifyObservers() {
            StringBuilder details = new StringBuilder();
            details.append(diagnosticReport.toString());
            for (RepairTask task : repairTasks) {
                details.append("  ").append(task).append("\n");
            }
            RepairOrderDTO dto = new RepairOrderDTO(
                id,
                customer.getName(),
                bike.toString(),
                state,
                details.toString()
            );
            for (RepairOrderObserver observer : observers) {
                observer.repairOrderUpdated(dto);
            }
        }
}