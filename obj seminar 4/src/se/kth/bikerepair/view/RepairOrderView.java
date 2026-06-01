package se.kth.bikerepair.view;

import se.kth.bikerepair.controller.RepairOrderObserver;


/**
 * Displays repair order updates to technicians and receptionists
 * via System.out. Implements the Observer pattern.
 */
public class RepairOrderView implements RepairOrderObserver {

    /**
     * Called when a repair order has been updated.
     * Prints the updated repair order to System.out.
     * @param order The updated repair order.
     */
    @Override
    public void repairOrderUpdated(RepairOrderDTO order) {
        System.out.println("\n[REPAIR ORDER UPDATE]");
        System.out.println(order);
        System.out.println("-----------------------------");
    }
}