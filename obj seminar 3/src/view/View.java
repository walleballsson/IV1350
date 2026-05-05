package view;

import controller.RepairController;
import model.Amount;
import model.Bike;
import model.Customer;
import model.Receipt;
import model.RepairOrder;

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

        // Step 1 - Create a repair order
        Customer customer = new Customer("John Doe", "john@email.com", "0701234567");
        Bike bike = new Bike("Trek", "FX3", "SN123456");
        String problemDescription = "Battery not charging";

        RepairOrder order = controller.startNewRepairOrder(customer, bike, 
                                                           problemDescription);
        System.out.println("Repair order created:");
        System.out.println(order);

        // Step 2 - Register diagnosis
        controller.addDiagnosticTaskResult(order.getId(), "Battery check", 
                                           "Battery cells damaged", new Amount(200));
        controller.addDiagnosticTaskResult(order.getId(), "Charger check", 
                                           "Charger port faulty", new Amount(150));
        controller.finishDiagnosis(order.getId());

        RepairOrder diagnosedOrder = controller.findRepairOrder(order.getId());
        System.out.println("\nDiagnosis complete:");
        System.out.println(diagnosedOrder);

        // Step 3 - Approve repair
        controller.approveRepair(order.getId());
        System.out.println("\nRepair approved.");

        // Step 4 - Add and complete repair tasks
        controller.addRepairTask(order.getId(), "Replace battery", 
                                 "Install new battery pack", new Amount(800));
        controller.addRepairTask(order.getId(), "Fix charger port", 
                                 "Replace charging port", new Amount(300));

        controller.markRepairTaskCompleted(order.getId(), "Replace battery");
        controller.markRepairTaskCompleted(order.getId(), "Fix charger port");

        // Step 5 - Complete repair
        Amount totalCost = controller.completeRepair(order.getId());
        System.out.println("\nRepair completed.");
        System.out.println("Total cost: " + totalCost);

        // Step 6 - Pay
        Receipt receipt = controller.payRepair(order.getId(), totalCost);
        System.out.println("\nPayment registered:");
        System.out.println(receipt);
    }
}