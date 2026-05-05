package model;

import java.time.LocalDate;

/**
 * Represents a receipt for a completed repair and payment.
 */
public class Receipt {
    private int orderId;
    private Amount totalCost;
    private LocalDate paymentDate;

    /**
     * Creates a new instance with the specified receipt details.
     * @param orderId The ID of the repair order.
     * @param totalCost The total cost of the repair.
     */
    public Receipt(int orderId, Amount totalCost) {
        this.orderId = orderId;
        this.totalCost = totalCost;
        this.paymentDate = LocalDate.now();
    }

    /**
     * Returns a string representation of this receipt.
     * @return The receipt details as a string.
     */
    @Override
    public String toString() {
        return "Receipt\n" +
               "Order ID: " + orderId + "\n" +
               "Total cost: " + totalCost + "\n" +
               "Payment date: " + paymentDate;
    }
}