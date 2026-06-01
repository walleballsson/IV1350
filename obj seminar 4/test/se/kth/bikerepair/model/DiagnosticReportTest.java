package se.kth.bikerepair.model;

import se.kth.bikerepair.model.Amount;
import se.kth.bikerepair.model.DiagnosticReport;
import se.kth.bikerepair.model.DiagnosticTaskResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class DiagnosticReportTest {
    private DiagnosticReport report;

    @BeforeEach
    public void setUp() {
        report = new DiagnosticReport();
    }

    @Test
    public void testEmptyReportHasZeroCost() {
        Amount cost = report.calculateDiagnosticCost();
        assertEquals(0.0, cost.getValue(), 
                     "Empty report should have zero cost");
    }

    @Test
    public void testSingleResultCostIsCorrect() {
        report.addResult(new DiagnosticTaskResult("Battery check", 
                                                   "Damaged", new Amount(200)));
        Amount cost = report.calculateDiagnosticCost();
        assertEquals(200.0, cost.getValue(), 
                     "Cost should be 200 after adding one result");
    }

    @Test
    public void testMultipleResultCostsAreSummedCorrectly() {
        report.addResult(new DiagnosticTaskResult("Battery check", 
                                                   "Damaged", new Amount(200)));
        report.addResult(new DiagnosticTaskResult("Charger check", 
                                                   "Faulty", new Amount(150)));
        report.addResult(new DiagnosticTaskResult("Wheel check", 
                                                   "OK", new Amount(50)));
        Amount cost = report.calculateDiagnosticCost();
        assertEquals(400.0, cost.getValue(), 
                     "Total cost should be 400 after adding three results");
    }
}