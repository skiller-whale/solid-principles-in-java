public class InvoiceCalculator {
    public static final double SALES_TAX = 0.2;

    private double amount;
    private CardType cardType;

    public InvoiceCalculator(double amount, CardType cardType) {
        this.amount = amount;
        this.cardType = cardType;
    }

    public double getTotal() {
        return amount + getSalesTax() + getCardFees();
    }

    public double getSubTotal() {
      return amount;
    }

    public double getSalesTax() {
        return SALES_TAX * amount;
    }

    public double getCardFees() {
        return cardType.getFeeRate() * amount;
    }
}
