package se.kth.bikerepair.view;

public class RepairOrderDTO {
    public final int id;
    public final String customerName;
    public final String bikeInfo;
    public final String state;
    public final String details;

    public RepairOrderDTO(int id, String customerName, 
                          String bikeInfo, String state, String details) {
        this.id = id;
        this.customerName = customerName;
        this.bikeInfo = bikeInfo;
        this.state = state;
        this.details = details;
    }

    @Override
    public String toString() {
        return "RepairOrder ID: " + id + "\nCustomer: " + customerName +
               "\nBike: " + bikeInfo + "\nState: " + state + 
               "\nDetails:\n" + details;
    }
}