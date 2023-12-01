import java.math.BigDecimal;

public enum CardType {
    VISA(new BigDecimal("0.015")),
    MASTERCARD(new BigDecimal("0.01")),
    AMEX(new BigDecimal("0.03"));

    private final BigDecimal feeRate;

    CardType(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }

    public BigDecimal getFeeRate() {
        return feeRate;
    }
}
