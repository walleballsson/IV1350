package se.kth.bikerepair.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.bikerepair.model.Bike;
import se.kth.bikerepair.model.Customer;
import se.kth.bikerepair.model.RepairOrder;

/**
 * Handles storage and retrieval of repair orders.
 */
public class RepairOrderRegistry {
    private List<RepairOrder> repairOrders;
    private int nextId;

    /**
     * Creates a new instance and initializes the repair order storage.
     */
    public RepairOrderRegistry() {
        repairOrders = new ArrayList<>();
        nextId = 1;
    }

    /**
     * Creates a new repair order and stores it in the registry.
     * @param customer The customer who owns the bike.
     * @param bike The bike to be repaired.
     * @param problemDescription A description of the problem.
     * @return The newly created repair order.
     */
    public RepairOrder createRepairOrder(Customer customer, Bike bike,
                                         String problemDescription) {
        RepairOrder order = new RepairOrder(nextId, customer, bike, problemDescription);
        repairOrders.add(order);
        nextId++;
        return order;
    }

    /**
     * Finds a repair order by its ID.
     * @param orderId The ID of the repair order to find.
     * @return The repair order with the specified ID, or null if not found.
     */
    public RepairOrder findById(int orderId) {
        for (RepairOrder order : repairOrders) {
            if (order.getId() == orderId) {
                return order;
            }
        }
        return null;
    }
}