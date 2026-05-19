package integration;

import model.Customer;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles storage and retrieval of customers.
 */
public class CustomerRegistry {
    private static final String DB_FAILURE_PHONE = "0000000000";
    private List<Customer> customers;

    /**
     * Creates a new instance and initializes the customer storage.
     */
    public CustomerRegistry() {
        customers = new ArrayList<>();
    }

    /**
     * Finds a customer by phone number.
     * @param phoneNumber The phone number to search for.
     * @return The customer with the specified phone number.
     * @throws CustomerNotFoundException if no customer with that phone number exists.
     * @throws DatabaseFailureException if the database cannot be reached.
     */
    public Customer findCustomerByPhone(String phoneNumber)
            throws CustomerNotFoundException, DatabaseFailureException {
        if (phoneNumber.equals(DB_FAILURE_PHONE)) {
            throw new DatabaseFailureException("findCustomerByPhone",
                new Exception("Database server is not running"));
        }
        for (Customer customer : customers) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                return customer;
            }
        }
        throw new CustomerNotFoundException(phoneNumber);
    }

    /**
     * Adds a new customer to the registry.
     * @param customer The customer to add.
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
}