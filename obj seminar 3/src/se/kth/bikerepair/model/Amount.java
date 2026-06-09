package se.kth.bikerepair.model;

/**
 * Represents a monetary amount.
 */
public class Amount {
    private double value;

    /**
     * Creates a new instance with the specified value.
     * @param value The monetary value.
     */
    public Amount(double value) {
        this.value = value;
    }

    /**
     * Adds another amount to this amount and returns the result.
     * @param other The amount to add.
     * @return A new Amount representing the sum.
     */
    public Amount add(Amount other) {
        return new Amount(this.value + other.value);
    }

    /**
     * Returns the value of this amount.
     * @return The monetary value.
     */
    public double getValue() {
        return value;
    }

    /**
     * Returns a string representation of this amount.
     * @return The amount as a string.
     */
    @Override
    public String toString() {
        return value + " SEK";
    }
}