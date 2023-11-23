import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class CustomerOrderLogger {
    private static CustomerOrderData order;

    public CustomerOrderLogger(CustomerOrderData order) {
        this.order = order;
    }

    private String getInvoiceNumber() {
        return "INV-" + order.getOrderId().replace('_', '-');
    }

    private String formatOrder() {
        return order.getItemName() + " | " + order.getAmount();
    }

    public void log() throws IOException {
        String filename = Paths.get(System.getProperty("user.dir"), "_invoices", getInvoiceNumber()).toString();
        try (FileWriter fileWriter = new FileWriter(new File(filename))) {
            fileWriter.write(formatOrder());
        }
    }
}
