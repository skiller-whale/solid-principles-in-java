public enum CardType {
    VISA(0.015),
    MASTERCARD(0.01),
    AMEX(0.03);

    private final double feeRate;

    CardType(double feeRate) {
        this.feeRate = feeRate;
    }

    public double getFeeRate() {
        return feeRate;
    }
}
