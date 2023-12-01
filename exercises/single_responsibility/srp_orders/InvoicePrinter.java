import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InvoicePrinter {

    private static final int WIDTH = 40;

    private CustomerOrderData order;
    private InvoiceCalculator invoiceCalculator;

    public InvoicePrinter(
        CustomerOrderData order,
        InvoiceCalculator invoiceCalculator
    ) {
        this.order = order;
        this.invoiceCalculator = invoiceCalculator;
    }

    private String formatValue(String key, BigDecimal value) {
        return String.format("%-" + (WIDTH - 12) + "s%12.2f", key + ":", value);
    }

    private String getInvoiceNumber() {
        return "INV-" + order.getOrderId().replace('_', '-');
    }

    public void print() {
        String bar = "-".repeat(WIDTH);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            "HH:mma yy-MMM-dd"
        );
        String datetime = LocalDateTime.now().format(formatter);

        System.out.println(
            "\n" +
            String.format(
                "%" + ((WIDTH + "INVOICE".length()) / 2) + "s",
                "INVOICE"
            ) +
            "\n" +
            bar +
            "\n" +
            String.format("%" + WIDTH + "s", datetime) +
            "\n" +
            String.format("%" + WIDTH + "s", "No: " + getInvoiceNumber()) +
            "\n" +
            bar +
            "\n" +
            order.getItemName() +
            " | " +
            order.getAmount() +
            "\n" +
            bar +
            "\n" +
            formatValue("Sub Total", invoiceCalculator.getSubTotal()) +
            "\n" +
            formatValue(
                "Sales Tax (" + InvoiceCalculator.SALES_TAX + "%)",
                invoiceCalculator.calculateSalesTax()
            ) +
            "\n" +
            formatValue("Card Fees", invoiceCalculator.calculateCardFees()) +
            "\n" +
            formatValue("Total", invoiceCalculator.getTotal()) +
            "\n" +
            bar
        );
    }
}
