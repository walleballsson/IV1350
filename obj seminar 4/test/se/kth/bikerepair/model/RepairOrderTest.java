package se.kth.bikerepair.model;

import se.kth.bikerepair.model.Amount;
import se.kth.bikerepair.model.Bike;
import se.kth.bikerepair.model.Customer;
import se.kth.bikerepair.model.DiagnosticTaskResult;
import se.kth.bikerepair.model.RepairOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class RepairOrderTest {
    private RepairOrder order;

    @BeforeEach
    public void setUp() {
        Customer customer = new Customer("John Doe", "john@email.com", "0701234567");
        Bike bike = new Bike("Trek", "FX3", "SN123456");
        order = new RepairOrder(1, customer, bike, "Battery not charging");
    }

    @Test
    public void testInitialStateIsNewlyCreated() {
        assertEquals("NEWLY_CREATED", order.getState(), 
                     "Initial state should be NEWLY_CREATED");
    }

    @Test
    public void testFinishDiagnosisSetsStateToReadyForApproval() {
        order.finishDiagnosis();
        assertEquals("READY_FOR_APPROVAL", order.getState(),
                     "State should be READY_FOR_APPROVAL after finishing diagnosis");
    }

    @Test
    public void testApproveSetsStateToApproved() {
        order.approve();
        assertEquals("APPROVED", order.getState(),
                     "State should be APPROVED after approving");
    }

    @Test
    public void testRejectSetsStateToRejected() {
        order.reject();
        assertEquals("REJECTED", order.getState(),
                     "State should be REJECTED after rejecting");
    }

    @Test
    public void testCompleteReturnsTotalCost() {
        order.addDiagnosticTaskResult(
            new DiagnosticTaskResult("Battery check", "Damaged", new Amount(200)));
        Amount total = order.complete();
        assertEquals(200.0, total.getValue(),
                     "Complete should return total cost of 200");
    }

    @Test
    public void testCompleteSetsStateToCompleted() {
        order.complete();
        assertEquals("COMPLETED", order.getState(),
                     "State should be COMPLETED after completing");
    }
}