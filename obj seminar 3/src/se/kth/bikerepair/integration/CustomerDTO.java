package se.kth.bikerepair.integration;

/**
 * Transfers customer data between the integration layer and the view.
 * Prevents model objects from leaking outside the model layer.
 */
public class CustomerDTO {
    /** The customer's full name. */
    public final String name;
    /** The customer's email address. */
    public final String email;
    /** The customer's phone number. */
    public final String phoneNumber;

    /**
     * Creates a new CustomerDTO with the given customer data.
     * @param name The customer's full name.
     * @param email The customer's email address.
     * @param phoneNumber The customer's phone number.
     */
    public CustomerDTO(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns a string representation of this customer DTO.
     * @return A formatted string with customer details.
     */
    @Override
    public String toString() {
        return "Customer: " + name + " | Email: " + email + " | Phone: " + phoneNumber;
    }
}