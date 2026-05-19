package integration;

/**
 * Thrown when a search is made for a phone number that does not exist
 * in the customer registry.
 */
public class CustomerNotFoundException extends Exception {
    private String phoneNumber;

    /**
     * Creates a new instance with the phone number that was not found.
     * @param phoneNumber The phone number that was searched for.
     */
    public CustomerNotFoundException(String phoneNumber) {
        super("No customer with phone number: " + phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the phone number that was not found.
     * @return The phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
}