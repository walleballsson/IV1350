package se.kth.bikerepair.controller;
import se.kth.bikerepair.view.RepairOrderDTO;

/**
 * Observer interface for receiving updates about repair orders.
 */
public interface RepairOrderObserver {
    /**
     * Called when a repair order has been updated.
     * @param order The updated repair order.
     */
    void repairOrderUpdated(RepairOrderDTO order);
}