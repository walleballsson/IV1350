package view;

import model.RepairOrder;
import model.RepairOrderObserver;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Logs repair order updates to a file.
 * Implements the Observer pattern.
 */
public class RepairOrderLogger implements RepairOrderObserver {
    private static final String LOG_FILE = "repair-order-log.txt";

    /**
     * Called when a repair order has been updated.
     * Logs the updated repair order to a file.
     * @param order The updated repair order.
     */
    @Override
    public void repairOrderUpdated(RepairOrder order) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            writer.println("--- Update at " + LocalDateTime.now() + " ---");
            writer.println(order);
            writer.println();
        } catch (IOException e) {
            System.out.println("[LOGGING FAILED] Could not write to log file: "
                               + e.getMessage());
        }
    }
}