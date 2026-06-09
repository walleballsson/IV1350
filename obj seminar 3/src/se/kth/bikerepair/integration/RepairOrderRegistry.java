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
    private List<RepairOrder> repairOrders = new ArrayList<>();
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
     * @return The ID of the newly created repair order.
     */
    public int createRepairOrder(Customer customer, Bike bike, String problemDescription) {
        RepairOrder order = new RepairOrder(nextId++, customer, bike, problemDescription);
        repairOrders.add(order);
        return order.getId(); 
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
    /**
     * Returns a summary DTO for the repair order with the given ID.
     * @param orderId The ID of the repair order.
     * @return A RepairOrderInfoDTO describing the repair order, or null if not found.
     */
    public RepairOrderInfoDTO getRepairOrderInfo(int orderId) {
        RepairOrder order = findById(orderId);
        if (order == null) {
            return null;
        }
        return new RepairOrderInfoDTO(
            order.getId(),
            order.getCustomerName(),
            order.getBikeBrand(),
            order.getBikeModel(),
            order.getProblemDescription(),
            order.getState(),
            order.getTotalCostString()
        );
    }
}