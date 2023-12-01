import java.math.BigDecimal;

public class CustomerOrderData {

    private String orderId;
    private String itemName;
    private BigDecimal amount;

    public CustomerOrderData(
        String orderId,
        String itemName,
        BigDecimal amount
    ) {
        this.orderId = orderId;
        this.itemName = itemName;
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getItemName() {
        return itemName;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
