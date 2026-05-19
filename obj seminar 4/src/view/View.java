package view;

import controller.RepairController;
import integration.CustomerNotFoundException;
import integration.DatabaseFailureException;
import model.Amount;

/**
 * Handles all interaction with the user. In this implementation
 * all calls are hard-coded to simulate a user interaction.
 */
public class View {
    private RepairController controller;
    private ErrorMessageHandler errorMessageHandler;

    /**
     * Creates a new instance with the specified controller.
     * @param controller The controller used to handle repair operations.
     */
    public View(RepairController controller) {
        this.controller = controller;
        this.errorMessageHandler = new ErrorMessageHandler();
    }

    /**
     * Starts the application and runs through the basic flow.
     */
    public void start() {
        System.out.println("=== Repair Electric Bike System ===\n");

        // Demonstrate customer not found exception
        try {
            controller.identifyCustomer("0799999999");
        } catch (CustomerNotFoundException e) {
            errorMessageHandler.showErrorMessage(
                "No customer found with that phone number. Please try again.");
        } catch (DatabaseFailureException e) {
            errorMessageHandler.showErrorMessage(
                "System error: could not reach the database. Please contact support.");
            errorMessageHandler.logException(e);
        }

        // Demonstrate database failure exception
        try {
            controller.identifyCustomer("0000000000");
        } catch (CustomerNotFoundException e) {
            errorMessageHandler.showErrorMessage(
                "No customer found with that phone number. Please try again.");
        } catch (DatabaseFailureException e) {
            errorMessageHandler.showErrorMessage(
                "System error: could not reach the database. Please contact support.");
            errorMessageHandler.logException(e);
        }

        // Step 1 - Create a repair order
        int orderId = controller.startNewRepairOrder(
            "John Doe", "john@email.com", "0701234567",
            "Trek", "FX3", "SN123456",
            "Battery not charging"
        );
        System.out.println("Repair order created with ID: " + orderId);
        RepairOrderView repairOrderView = new RepairOrderView();
        RepairOrderLogger repairOrderLogger = new RepairOrderLogger();
        controller.addObserver(orderId, repairOrderView);
        controller.addObserver(orderId, repairOrderLogger);

        // Step 2 - Register diagnosis
        controller.addDiagnosticTaskResult(orderId, "Battery check", "Battery cells damaged", new Amount(200));
        controller.addDiagnosticTaskResult(orderId, "Charger check", "Charger port faulty", new Amount(150));
        controller.finishDiagnosis(orderId);
        System.out.println("\nDiagnosis complete:");
        System.out.println(controller.findRepairOrder(orderId));

        // Step 3 - Approve repair
        controller.approveRepair(orderId);
        System.out.println("\nRepair approved.");

        // Step 4 - Add and complete repair tasks
        controller.addRepairTask(orderId, "Replace battery", "Install new battery pack", new Amount(800));
        controller.addRepairTask(orderId, "Fix charger port", "Replace charging port", new Amount(300));
        controller.markRepairTaskCompleted(orderId, "Replace battery");
        controller.markRepairTaskCompleted(orderId, "Fix charger port");

        // Step 5 - Complete repair
        Amount totalCost = controller.completeRepair(orderId);
        System.out.println("\nRepair completed.");
        System.out.println("Total cost: " + totalCost);

        // Step 6 - Pay
        controller.payRepair(orderId, totalCost);
        System.out.println("\nPayment registered.");
    }
}