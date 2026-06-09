package se.kth.bikerepair.integration;

import se.kth.bikerepair.model.Bike;
import se.kth.bikerepair.model.Customer;
import se.kth.bikerepair.model.RepairOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RepairOrderRegistryTest {
    private RepairOrderRegistry registry;
    private Customer customer;
    private Bike bike;

    @BeforeEach
    public void setUp() {
        registry = new RepairOrderRegistry();
        customer = new Customer("Anna", "anna@email.com", "0701234567");
        bike = new Bike("Trek", "FX3", "SN123");
    }

    @Test
    public void testCreatedOrderHasCorrectId() {
        int id = registry.createRepairOrder(customer, bike, "Flat tire");
        assertEquals(1, id, "First order should have id 1");
    }

    @Test
    public void testSecondOrderHasIncrementedId() {
        registry.createRepairOrder(customer, bike, "Flat tire");
        int secondId = registry.createRepairOrder(customer, bike, "Battery dead");
        assertEquals(2, secondId, "Second order should have id 2");
    }

    @Test
    public void testFindByIdReturnsCorrectOrder() {
        int id = registry.createRepairOrder(customer, bike, "Flat tire");
        RepairOrder found = registry.findById(id);
        assertEquals(id, found.getId(), "Found order should have same id");
    }

    @Test
    public void testFindByIdReturnsNullForUnknownId() {
        RepairOrder found = registry.findById(999);
        assertNull(found, "Should return null for unknown id");
    }
}