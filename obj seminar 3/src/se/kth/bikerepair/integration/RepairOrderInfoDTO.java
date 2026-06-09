package se.kth.bikerepair.integration;

/**
 * Transfers repair order summary data between layers.
 * Prevents model objects from leaking into the view or controller.
 */
public class RepairOrderInfoDTO {
    /** The unique ID of the repair order. */
    public final int id;
    /** The name of the customer who owns the bike. */
    public final String customerName;
    /** The brand of the bike being repaired. */
    public final String bikeBrand;
    /** The model of the bike being repaired. */
    public final String bikeModel;
    /** The description of the problem reported. */
    public final String problemDescription;
    /** The current state of the repair order. */
    public final String state;
    /** The total cost of the repair as a formatted string. */
    public final String totalCost;

    /**
     * Creates a new RepairOrderInfoDTO with the given repair order data.
     * @param id The repair order ID.
     * @param customerName The customer's name.
     * @param bikeBrand The bike brand.
     * @param bikeModel The bike model.
     * @param problemDescription The reported problem.
     * @param state The current state of the repair order.
     * @param totalCost The total cost as a formatted string.
     */
    public RepairOrderInfoDTO(int id, String customerName, String bikeBrand,
                              String bikeModel, String problemDescription,
                              String state, String totalCost) {
        this.id = id;
        this.customerName = customerName;
        this.bikeBrand = bikeBrand;
        this.bikeModel = bikeModel;
        this.problemDescription = problemDescription;
        this.state = state;
        this.totalCost = totalCost;
    }

    /**
     * Returns a string representation of this repair order summary.
     * @return A formatted string with repair order details.
     */
    @Override
    public String toString() {
        return "Repair Order #" + id + "\n" +
               "  Customer:    " + customerName + "\n" +
               "  Bike:        " + bikeBrand + " " + bikeModel + "\n" +
               "  Problem:     " + problemDescription + "\n" +
               "  State:       " + state + "\n" +
               "  Total cost:  " + totalCost;
    }
}