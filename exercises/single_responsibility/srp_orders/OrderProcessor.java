import java.io.IOException;

public class OrderProcessor {
    public static void processOrder(String orderId, String item, double amount, CardType card) throws IOException {
        CustomerOrderData order = new CustomerOrderData(orderId, item, amount);
        CustomerOrderLogger logger = new CustomerOrderLogger(order);
        InvoiceCalculator calculator = new InvoiceCalculator(order.getAmount(), card);
        InvoicePrinter printer = new InvoicePrinter(order, calculator);
        logger.log();
        printer.print();
    }

    public static void main(String[] args) throws IOException {
        processOrder("abc_defghi_jkl", "Lunch", 38.20, CardType.AMEX);
        processOrder("mno_pqrstu_vwx", "Dinner", 24.99, CardType.VISA);
    }
}
