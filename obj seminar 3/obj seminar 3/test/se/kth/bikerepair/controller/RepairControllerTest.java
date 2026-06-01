package se.kth.bikerepair.controller;

import se.kth.bikerepair.integration.CustomerRegistry;
import se.kth.bikerepair.integration.RepairOrderRegistry;
import se.kth.bikerepair.model.Amount;
import se.kth.bikerepair.model.Receipt;
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
            "Anna", "anna@email.com", "0701234567",
            "Trek", "FX3", "SN123", "Flat tire"
        );
    }

    @Test
    public void testStartNewRepairOrderReturnsValidId() {
        assertTrue(orderId > 0, "Order id should be greater than 0");
    }

    @Test
    public void testCompleteRepairReturnsNonNullAmount() {
        Amount total = controller.completeRepair(orderId);
        assertNotNull(total, "completeRepair should return a non-null amount");
    }

    @Test
    public void testPayRepairReturnsReceipt() {
        controller.finishDiagnosis(orderId);
        controller.approveRepair(orderId);
        controller.completeRepair(orderId);
        Receipt receipt = controller.payRepair(orderId, new Amount(0));
        assertNotNull(receipt, "Receipt should not be null after payment");
    }
}