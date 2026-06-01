package se.kth.bikerepair.model;

import se.kth.bikerepair.model.Amount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class AmountTest {
    private Amount amount;

    @BeforeEach
    public void setUp() {
        amount = new Amount(100.0);
    }

    @Test
    public void testAddPositiveAmount() {
        Amount other = new Amount(50.0);
        Amount result = amount.add(other);
        assertEquals(150.0, result.getValue(), "Adding 100 and 50 should give 150");
    }

    @Test
    public void testAddZeroAmount() {
        Amount zero = new Amount(0);
        Amount result = amount.add(zero);
        assertEquals(100.0, result.getValue(), "Adding 0 to 100 should still give 100");
    }

    @Test
    public void testAddTwoZeroAmounts() {
        Amount zero1 = new Amount(0);
        Amount zero2 = new Amount(0);
        Amount result = zero1.add(zero2);
        assertEquals(0.0, result.getValue(), "Adding 0 and 0 should give 0");
    }

    @Test
    public void testGetValue() {
        assertEquals(100.0, amount.getValue(), "getValue should return 100");
    }
}