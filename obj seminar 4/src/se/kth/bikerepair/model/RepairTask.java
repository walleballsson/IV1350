package se.kth.bikerepair.model;

/**
 * Represents a single repair task to be performed on a bike.
 */
public class RepairTask {
    private String name;
    private String description;
    private Amount cost;
    private boolean completed;

    /**
     * Creates a new instance with the specified repair task details.
     * @param name The name of the repair task.
     * @param description The description of the repair task.
     * @param cost The cost of the repair task.
     */
    public RepairTask(String name, String description, Amount cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.completed = false;
    }

    /**
     * Marks this repair task as completed.
     */
    public void markCompleted() {
        this.completed = true;
    }

    /**
     * Returns whether this repair task is completed.
     * @return True if completed, false otherwise.
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Returns the name of this repair task.
     * @return The task name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the cost of this repair task.
     * @return The cost.
     */
    public Amount getCost() {
        return cost;
    }

    /**
     * Returns a string representation of this repair task.
     * @return The repair task details as a string.
     */
    @Override
    public String toString() {
        return name + ": " + description + " (" + cost + ") - " + 
               (completed ? "COMPLETED" : "NOT STARTED");
    }
}