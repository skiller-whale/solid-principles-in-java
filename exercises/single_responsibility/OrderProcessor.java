import java.io.IOException;

public class OrderProcessor {
    public static void processOrder(String orderId, String item, double amount, CardType card) throws IOException {
        CustomerOrder order = new CustomerOrder(orderId, item, amount);
        order.logOrder();
        order.printInvoice(card);
    }

    public static void main(String[] args) throws IOException {
        processOrder("abc_defghi_jkl", "Lunch", 38.20, CardType.AMEX);
        processOrder("mno_pqrstu_vwx", "Dinner", 24.99, CardType.VISA);
    }
}
