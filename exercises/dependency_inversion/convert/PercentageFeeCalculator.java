package convert;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PercentageFeeCalculator {

    private double feeRatio;

    public PercentageFeeCalculator(double percentage) {
        this.feeRatio = percentage / 100.0;
    }

    public BigDecimal calculate(BigDecimal amount) {
        return amount
            .multiply(BigDecimal.valueOf(feeRatio))
            .setScale(2, RoundingMode.HALF_UP);
    }
}
