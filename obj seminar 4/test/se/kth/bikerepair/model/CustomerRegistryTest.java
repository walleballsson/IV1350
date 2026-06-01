package se.kth.bikerepair.integration;

import se.kth.bikerepair.integration.CustomerNotFoundException;
import se.kth.bikerepair.integration.DatabaseFailureException;
import se.kth.bikerepair.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerRegistryTest {
    private CustomerRegistry registry;

    @BeforeEach
    public void setUp() {
        registry = new CustomerRegistry();
    }

    @Test
    public void testFindExistingCustomerReturnsCustomer() throws CustomerNotFoundException, DatabaseFailureException {
        Customer customer = new Customer("John Doe", "john@email.com", "0701234567");
        registry.addCustomer(customer);
        Customer found = registry.findCustomerByPhone("0701234567");
        assertEquals("0701234567", found.getPhoneNumber(),
                     "Should return the customer with the correct phone number");
    }

    @Test
    public void testFindNonExistingCustomerThrowsCustomerNotFoundException() {
        assertThrows(CustomerNotFoundException.class, () -> {
            registry.findCustomerByPhone("0799999999");
        }, "Should throw CustomerNotFoundException when phone number is not found");
    }

    @Test
    public void testFindWithDatabaseFailurePhoneThrowsDatabaseFailureException() {
        assertThrows(DatabaseFailureException.class, () -> {
            registry.findCustomerByPhone("0000000000");
        }, "Should throw DatabaseFailureException for the hardcoded failure phone number");
    }

    @Test
    public void testCustomerNotFoundExceptionContainsPhoneNumber() {
        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class, () -> {
            registry.findCustomerByPhone("0799999999");
        });
        assertEquals("0799999999", exception.getPhoneNumber(),
                     "Exception should contain the phone number that was searched for");
    }
}