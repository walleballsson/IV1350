package integration;

import model.Receipt;

/**
 * Handles printing of receipts to the system output.
 */
public class Printer {

    /**
     * Prints the specified receipt to the system output.
     * @param receipt The receipt to print.
     */
    public void print(Receipt receipt) {
        System.out.println("--- PRINTING RECEIPT ---");
        System.out.println(receipt);
        System.out.println("------------------------");
    }
}