package se.kth.bikerepair.integration;

class RepairOrderDTO {
    final int id;
    final String customerName;
    final String customerEmail;
    final String customerPhone;
    final String bikeBrand;
    final String bikeModel;
    final String bikeSerial;
    final String problemDescription;

    RepairOrderDTO(int id,
                   String customerName, String customerEmail, String customerPhone,
                   String bikeBrand,   String bikeModel,     String bikeSerial,
                   String problemDescription) {
        this.id                 = id;
        this.customerName       = customerName;
        this.customerEmail      = customerEmail;
        this.customerPhone      = customerPhone;
        this.bikeBrand          = bikeBrand;
        this.bikeModel          = bikeModel;
        this.bikeSerial         = bikeSerial;
        this.problemDescription = problemDescription;
    }
}