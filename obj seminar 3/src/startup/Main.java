package startup;

import controller.RepairController;
import integration.CustomerRegistry;
import integration.RepairOrderRegistry;
import view.View;

/**
 * Contains the main method, starts the application.
 */
public class Main {

    /**
     * The main method, used to start the application.
     * @param args The application does not take any command line arguments.
     */
    public static void main(String[] args) {
        CustomerRegistry customerRegistry = new CustomerRegistry();
        RepairOrderRegistry repairOrderRegistry = new RepairOrderRegistry();
        RepairController controller = new RepairController(customerRegistry, 
                                                           repairOrderRegistry);
        View view = new View(controller);
        view.start();
    }
}