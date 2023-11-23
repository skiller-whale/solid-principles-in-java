import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class CustomerOrder {
    private static final int INVOICE_WIDTH = 40;
    private static final double SALES_TAX = 0.2;

    private String orderId;
    private String itemName;
    private double amount;

    public CustomerOrder(String orderId, String itemName, double amount) {
        this.orderId = orderId;
        this.itemName = itemName;
        this.amount = amount;
    }

    private String getInvoiceNumber() {
        return "INV-" + orderId.replace('_', '-');
    }

    private String formatOrder() {
        return itemName + " | " + amount;
    }

    private String formatValue(String key, double value) {
        return String.format("%-" + (INVOICE_WIDTH - 12) + "s%12.2f", key + ":", value);
    }

    public double getSalesTax() {
        return SALES_TAX * amount;
    }

    public double getCardFees(CardType cardType) {
        return cardType.getFeeRate() * amount;
    }

    public double getTotalCost(CardType cardType) {
        return amount + getSalesTax() + getCardFees(cardType);
    }

    public void logOrder() throws IOException {
        String filename = Paths.get(System.getProperty("user.dir"), "_invoices", getInvoiceNumber()).toString();
        try (FileWriter fileWriter = new FileWriter(new File(filename))) {
            fileWriter.write(formatOrder());
        }
    }

    public void printInvoice(CardType cardType) {
        String bar = "-".repeat(INVOICE_WIDTH);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mma yy-MMM-dd");
        String datetime = LocalDateTime.now().format(formatter);

        System.out.println("\n" +
            String.format("%" + ((INVOICE_WIDTH + "INVOICE".length()) / 2) + "s", "INVOICE") + "\n" +
            bar + "\n" +
            String.format("%" + INVOICE_WIDTH + "s", datetime) + "\n" +
            String.format("%" + INVOICE_WIDTH + "s", "No: " + getInvoiceNumber()) + "\n" +
            bar + "\n" +
            formatOrder() + "\n" +
            bar + "\n" +
            formatValue("Sub Total", amount) + "\n" +
            formatValue("Sales Tax (" + SALES_TAX + "%)", getSalesTax()) + "\n" +
            formatValue("Card Fees", getCardFees(cardType)) + "\n" +
            formatValue("Total", getTotalCost(cardType)) + "\n" +
            bar
        );
    }
}
