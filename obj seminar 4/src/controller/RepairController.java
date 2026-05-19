package controller;

import integration.CustomerNotFoundException;
import integration.CustomerRegistry;
import integration.DatabaseFailureException;
import integration.Printer;
import integration.RepairOrderRegistry;
import model.Amount;
import model.Bike;
import model.Customer;
import model.DiagnosticTaskResult;
import model.Receipt;
import model.RepairOrder;
import model.RepairOrderObserver;
import model.RepairTask;



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
     * @return The customer with the specified phone number.
     * @throws CustomerNotFoundException if no customer with that phone number exists.
     * @throws DatabaseFailureException if the database cannot be reached.
     */
    public Customer identifyCustomer(String phoneNumber)
            throws CustomerNotFoundException, DatabaseFailureException {
        return customerRegistry.findCustomerByPhone(phoneNumber);
    }

    // new method added after feedback
    public int startNewRepairOrder(String customerName, String customerEmail,
        String customerPhone, String bikeBrand,
        String bikeModel, String bikeSerial,
        String problemDescription) {
        Customer customer = new Customer(customerName, customerEmail, customerPhone);
        Bike bike = new Bike(bikeBrand, bikeModel, bikeSerial);
        RepairOrder order = repairOrderRegistry.createRepairOrder(customer, bike, problemDescription);
        return order.getId();
        }
    /**
     * Starts a new repair order for the specified customer and bike.
     * @param customer The customer who owns the bike.
     * @param bike The bike to be repaired.
     * @param problemDescription A description of the problem.
     * @return The newly created repair order.
     */
    public RepairOrder startNewRepairOrder(Customer customer, Bike bike, 
                                           String problemDescription) {
        return repairOrderRegistry.createRepairOrder(customer, bike, problemDescription);
    }

    /**
     * Finds a repair order by its ID.
     * @param orderId The ID of the repair order to find.
     * @return The repair order with the specified ID.
     */
    public RepairOrder findRepairOrder(int orderId) {
        return repairOrderRegistry.findById(orderId);
    }

    /**
     * Adds a diagnostic task result to the specified repair order.
     * @param orderId The ID of the repair order.
     * @param taskName The name of the diagnostic task.
     * @param resultText The result of the diagnostic task.
     * @param cost The cost of the diagnostic task.
     */
    public void addDiagnosticTaskResult(int orderId, String taskName, 
                                         String resultText, Amount cost) {
        RepairOrder order = repairOrderRegistry.findById(orderId);
        DiagnosticTaskResult result = new DiagnosticTaskResult(taskName, 
                                                                resultText, cost);
        order.addDiagnosticTaskResult(result);
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
     * Adds a repair task to the specified repair order.
     * @param orderId The ID of the repair order.
     * @param taskName The name of the repair task.
     * @param description The description of the repair task.
     * @param cost The cost of the repair task.
     */
    public void addRepairTask(int orderId, String taskName, 
                               String description, Amount cost) {
        RepairOrder order = repairOrderRegistry.findById(orderId);
        RepairTask task = new RepairTask(taskName, description, cost);
        order.addRepairTask(task);
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
     * @return The total cost of the repair.
     */
    public Amount completeRepair(int orderId) {
        RepairOrder order = repairOrderRegistry.findById(orderId);
        return order.complete();
    }

    /**
     * Registers payment for the specified repair order.
     * @param orderId The ID of the repair order.
     * @param paidAmount The amount paid by the customer.
     * @return The receipt for the payment.
     */
    public Receipt payRepair(int orderId, Amount paidAmount) {
        RepairOrder order = repairOrderRegistry.findById(orderId);
        Receipt receipt = order.registerPayment(paidAmount);
        printer.print(receipt);
        return receipt;
    }
    /**
     * Adds an observer to the specified repair order.
     * @param orderId The ID of the repair order.
     * @param observer The observer to add.
     */
    public void addObserver(int orderId, RepairOrderObserver observer) {
        RepairOrder order = repairOrderRegistry.findById(orderId);
        order.addObserver(observer);
    }
}