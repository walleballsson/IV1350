package model;

/**
 * Represents a customer who owns a bike to be repaired.
 */
public class Customer {
    private String name;
    private String email;
    private String phoneNumber;

    /**
     * Creates a new instance with the specified customer details.
     * @param name The name of the customer.
     * @param email The email of the customer.
     * @param phoneNumber The phone number of the customer.
     */
    public Customer(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the phone number of this customer.
     * @return The phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    // change made after feedback
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }

    /**
     * Returns a string representation of this customer.
     * @return The customer details as a string.
     */
    @Override
    public String toString() {
        return name + " (" + email + ", " + phoneNumber + ")";
    }
}