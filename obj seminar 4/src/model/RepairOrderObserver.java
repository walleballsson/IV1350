package model;

/**
 * Observer interface for receiving updates about repair orders.
 */
public interface RepairOrderObserver {
    /**
     * Called when a repair order has been updated.
     * @param order The updated repair order.
     */
    void repairOrderUpdated(RepairOrder order);
}