package se.kth.bikerepair.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.bikerepair.model.Customer;

/**
 * Handles storage and retrieval of customers.
 */
public class CustomerRegistry {
    private List<Customer> customers;

    /**
     * Creates a new instance and initializes the customer storage.
     */
    public CustomerRegistry() {
        customers = new ArrayList<>();
        customers.add(new Customer("John Doe", "john@email.com", "0701234567"));
    }

    /**
     * Finds a customer by phone number.
     * @param phoneNumber The phone number to search for.
     * @return The customer with the specified phone number, or null if not found.
     */
    public CustomerDTO findCustomerByPhone(String phoneNumber) {
        for (Customer customer : customers) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                return new CustomerDTO(customer.getName(), customer.getEmail(), customer.getPhoneNumber());
            }
        }
        return null;
    }

    /**
     * Returns the internal customer model object for the given phone number.
     * Used within the integration layer when creating repair orders.
     * @param phoneNumber The phone number to look up.
     * @return The Customer, or null if not found.
     */
    public Customer getCustomerByPhone(String phoneNumber) {
        for (Customer customer : customers) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                return customer;
            }
        }
        return null;
    }

    /**
     * Adds a new customer to the registry.
     * @param customer The customer to add.
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
}