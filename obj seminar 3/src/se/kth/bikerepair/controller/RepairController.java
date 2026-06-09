package se.kth.bikerepair.controller;

import se.kth.bikerepair.integration.CustomerDTO;
import se.kth.bikerepair.integration.CustomerRegistry;
import se.kth.bikerepair.integration.Printer;
import se.kth.bikerepair.integration.RepairOrderInfoDTO;
import se.kth.bikerepair.integration.RepairOrderRegistry;
import se.kth.bikerepair.model.Amount;
import se.kth.bikerepair.model.Bike;
import se.kth.bikerepair.model.Customer;
import se.kth.bikerepair.model.DiagnosticTaskResult;
import se.kth.bikerepair.model.Receipt;
import se.kth.bikerepair.model.RepairOrder;
import se.kth.bikerepair.model.RepairTask;

/**
 * Controls the repair process and coordinates between view and model.
 */
public class RepairController {
    private CustomerRegistry customerRegistry;
    private RepairOrderRegistry repairOrderRegistry;
    private Printer printer;

    /**
     * Creates a new instance with the specified registries.
     * @param customerRegistry The registry used to store and retrieve customers.
     * @param repairOrderRegistry The registry used to store and retrieve repair orders.
     */
    public RepairController(CustomerRegistry customerRegistry, 
                            RepairOrderRegistry repairOrderRegistry) {
        this.customerRegistry = customerRegistry;
        this.repairOrderRegistry = repairOrderRegistry;
        this.printer = new Printer();
    }

    /**
     * Identifies a customer by phone number.
     * @param phoneNumber The phone number of the customer.
     * @return The customer with the specified phone number, or null if not found.
     */
    public CustomerDTO identifyCustomer(String phoneNumber) {
        return customerRegistry.findCustomerByPhone(phoneNumber);
    }

    /**
     * Starts a new repair order for the customer with the given phone number.
     * @param customerPhone The phone number identifying the existing customer.
     * @param bikeBrand The brand of the bike to be repaired.
     * @param bikeModel The model of the bike to be repaired.
     * @param bikeSerial The serial number of the bike.
     * @param problemDescription A description of the problem.
     * @return The ID of the newly created repair order.
     */
    public int startNewRepairOrder(String customerPhone,
        String bikeBrand, String bikeModel,
        String bikeSerial, String problemDescription) {
    Customer customer = customerRegistry.getCustomerByPhone(customerPhone);
    Bike bike = new Bike(bikeBrand, bikeModel, bikeSerial);
    return repairOrderRegistry.createRepairOrder(customer, bike, problemDescription);
    }

    /**
     * Returns a DTO summarising the current state of the repair order.
     * @param orderId The ID of the repair order.
     * @return A RepairOrderInfoDTO describing the order, or null if not found.
     */
    public RepairOrderInfoDTO getRepairOrderInfo(int orderId) {
        return repairOrderRegistry.getRepairOrderInfo(orderId);
    }

    /**
     * Adds a diagnostic task result to the specified repair order.
     * @param orderId The ID of the repair order.
     * @param taskName The name of the diagnostic task.
     * @param resultText The result of the diagnostic task.
     * @param cost The cost of the diagnostic task.
     */
    public void addDiagnosticTaskResult(int orderId, String taskName,
        String resultText, double cost) {
    RepairOrder order = repairOrderRegistry.findById(orderId);
    DiagnosticTaskResult result = new DiagnosticTaskResult(taskName, resultText, new Amount(cost));
    order.addDiagnosticTaskResult(result);
    }
    /**
     * Adds a repair task to the specified repair order.
     * @param orderId The ID of the repair order.
     * @param taskName The name of the repair task.
     * @param description The description of the repair task.
     * @param cost The cost of the repair task in SEK.
     */
    public void addRepairTask(int orderId, String taskName,
    String description, double cost) {
    RepairOrder order = repairOrderRegistry.findById(orderId);
    RepairTask task = new RepairTask(taskName, description, new Amount(cost));
    order.addRepairTask(task);
    }

    /**
     * Finishes the diagnosis for the specified repair order.
     * @param orderId The ID of the repair order.
     */
    public void finishDiagnosis(int orderId) {
        RepairOrder order = repairOrderRegistry.findById(orderId);
        order.finishDiagnosis();
    }

    /**
     * Approves the repair for the specified repair order.
     * @param orderId The ID of the repair order.
     */
    public void approveRepair(int orderId) {
        RepairOrder order = repairOrderRegistry.findById(orderId);
        order.approve();
    }

    /**
     * Marks a repair task as completed for the specified repair order.
     * @param orderId The ID of the repair order.
     * @param taskName The name of the task to mark as completed.
     */
    public void markRepairTaskCompleted(int orderId, String taskName) {
        RepairOrder order = repairOrderRegistry.findById(orderId);
        order.markRepairTaskCompleted(taskName);
    }

    /**
     * Completes the repair for the specified repair order.
     * @param orderId The ID of the repair order.
     * @return The total cost of the repair in SEK as a plain double.
     */
    public double completeRepair(int orderId) {
        RepairOrder order = repairOrderRegistry.findById(orderId);
        Amount total = order.complete();
        return total.getValue();
    }

    /**
     * Registers payment and prints the receipt.
     * @param orderId The ID of the repair order.
     * @param paidAmount The amount paid by the customer in SEK.
     */
    public void payRepair(int orderId, double paidAmount) {
        RepairOrder order = repairOrderRegistry.findById(orderId);
        Receipt receipt = order.registerPayment(new Amount(paidAmount));
        printer.print(receipt);
    }


}