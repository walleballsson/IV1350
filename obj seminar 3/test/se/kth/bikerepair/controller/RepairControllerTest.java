package se.kth.bikerepair.controller;

import se.kth.bikerepair.integration.CustomerRegistry;
import se.kth.bikerepair.integration.RepairOrderRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RepairControllerTest {
    private RepairController controller;
    private int orderId;

    @BeforeEach
    public void setUp() {
        CustomerRegistry customerRegistry = new CustomerRegistry();
        RepairOrderRegistry repairOrderRegistry = new RepairOrderRegistry();
        controller = new RepairController(customerRegistry, repairOrderRegistry);
        orderId = controller.startNewRepairOrder(
            "0701234567",
            "Trek", "FX3", "SN123", "Flat tire"
        );
    }

    @Test
    public void testStartNewRepairOrderReturnsValidId() {
        assertTrue(orderId > 0, "Order id should be greater than 0");
    }

    @Test
    public void testCompleteRepairReturnsNonNullAmount() {
        double total = controller.completeRepair(orderId);
        assertTrue(total >= 0, "completeRepair should return a non-negative cost");
    }

    @Test
    public void testPayRepairDoesNotThrow() {
        controller.finishDiagnosis(orderId);
        controller.approveRepair(orderId);
        controller.completeRepair(orderId);
        assertDoesNotThrow(() -> controller.payRepair(orderId, 0));
    }
}