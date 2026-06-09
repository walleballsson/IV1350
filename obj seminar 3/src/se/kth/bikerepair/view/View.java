package se.kth.bikerepair.view;

import se.kth.bikerepair.controller.RepairController;
import se.kth.bikerepair.integration.CustomerDTO;
import se.kth.bikerepair.integration.RepairOrderInfoDTO;

/**
 * Handles all interaction with the user. In this implementation
 * all calls are hard-coded to simulate a user interaction.
 */
public class View {
    private RepairController controller;

    /**
     * Creates a new instance with the specified controller.
     * @param controller The controller used to handle repair operations.
     */
    public View(RepairController controller) {
        this.controller = controller;
    }

    /**
     * Starts the application and runs through the basic flow.
     */
    public void start() {
        System.out.println("=== Repair Electric Bike System ===\n");
    
        // Step 1 - Identify the customer by phone number
        String phoneNumber = "0701234567";
        CustomerDTO customer = controller.identifyCustomer(phoneNumber);
        if (customer == null) {
            System.out.println("No customer found for phone: " + phoneNumber);
            return;
        }
        System.out.println("Customer identified:");
        System.out.println(customer);
    
        // Step 2 - Start a new repair order for the identified customer
        int orderId = controller.startNewRepairOrder(
            customer.phoneNumber,
            "Trek", "FX3", "SN123456",
            "Battery not charging"
        );
        System.out.println("\nRepair order created with ID: " + orderId);
    
        // Step 3 - Show repair order info to technician
        RepairOrderInfoDTO orderInfo = controller.getRepairOrderInfo(orderId);
        System.out.println("\nRepair order details:");
        System.out.println(orderInfo);
    
        // Step 4 - Technician registers diagnosis results
        controller.addDiagnosticTaskResult(orderId, "Battery check",
                                           "Battery cells damaged", 200);
        controller.addDiagnosticTaskResult(orderId, "Charger check",
                                           "Charger port faulty", 150);
        controller.finishDiagnosis(orderId);
    
        // Show updated order info to customer for approval
        orderInfo = controller.getRepairOrderInfo(orderId);
        System.out.println("\nDiagnosis complete. Order summary presented to customer:");
        System.out.println(orderInfo);
    
        // Step 5 - Customer approves the repair
        controller.approveRepair(orderId);
        System.out.println("\nRepair approved by customer.");
    
        // Step 6 - Technician performs repair tasks
        controller.addRepairTask(orderId, "Replace battery",
                                 "Install new battery pack", 800);
        controller.addRepairTask(orderId, "Fix charger port",
                                 "Replace charging port", 300);
        controller.markRepairTaskCompleted(orderId, "Replace battery");
        controller.markRepairTaskCompleted(orderId, "Fix charger port");
    
        // Step 7 - Complete repair, get total cost
        double totalCost = controller.completeRepair(orderId);
        System.out.println("\nRepair completed. Total cost: " + totalCost + " SEK");
    
        // Show final repair order printout
        orderInfo = controller.getRepairOrderInfo(orderId);
        System.out.println("\nFinal repair order:");
        System.out.println(orderInfo);
    
        // Step 8 - Register payment; receipt is printed inside controller via Printer
        controller.payRepair(orderId, totalCost);
        System.out.println("\nPayment registered. Receipt printed.");
    }
}