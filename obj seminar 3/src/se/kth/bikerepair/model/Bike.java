package se.kth.bikerepair.model;

/**
 * Represents an electric bike to be repaired.
 */
public class Bike {
    private String brand;
    private String model;
    private String serialNumber;

    /**
     * Creates a new instance with the specified bike details.
     * @param brand The brand of the bike.
     * @param model The model of the bike.
     * @param serialNumber The serial number of the bike.
     */
    public Bike(String brand, String model, String serialNumber) {
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
    }

    /**
     * Returns the serial number of this bike.
     * @return The serial number.
     */
    public String getSerialNumber() {
        return serialNumber;
    }
    /**
     * Returns the brand of this bike.
     * @return The bike brand.
     */
    public String getBrand() {
        return brand;
    }
    /**
     * Returns the model name of this bike.
     * @return The bike model.
     */
    public String getModel() {
        return model;
    }

    /**
     * Returns a string representation of this bike.
     * @return The bike details as a string.
     */
    @Override
    public String toString() {
        return brand + " " + model + " (Serial: " + serialNumber + ")";
    }
}