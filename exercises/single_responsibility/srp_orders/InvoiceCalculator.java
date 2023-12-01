import java.math.BigDecimal;

public class InvoiceCalculator {

    public static final BigDecimal SALES_TAX = new BigDecimal("0.2");

    private BigDecimal amount;
    private CardType cardType;

    public InvoiceCalculator(BigDecimal amount, CardType cardType) {
        this.amount = amount;
        this.cardType = cardType;
    }

    public BigDecimal getTotal() {
        return amount.add(calculateSalesTax()).add(calculateCardFees());
    }

    public BigDecimal getSubTotal() {
        return amount;
    }

    public BigDecimal calculateSalesTax() {
        return SALES_TAX.multiply(amount);
    }

    public BigDecimal calculateCardFees() {
        return cardType.getFeeRate().multiply(amount);
    }
}
