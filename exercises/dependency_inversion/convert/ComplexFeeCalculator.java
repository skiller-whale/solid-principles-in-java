package convert;

import forex.FxService;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ComplexFeeCalculator {

    private FxService fxService;
    private BigDecimal fixedFeeAmount;
    private String fixedFeeCurrency;
    private String targetCurrency;

    public ComplexFeeCalculator(
        FxService fxService,
        BigDecimal fixedFeeAmount,
        String fixedFeeCurrency
    ) {
        this.fxService = fxService;
        this.fixedFeeAmount = fixedFeeAmount;
        this.fixedFeeCurrency = fixedFeeCurrency;
        this.targetCurrency = "USD"; // Default target currency
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    // Calculates the total fee which includes a fixed fee AND
    // a percentage of the total amount.
    public BigDecimal calculateFee(BigDecimal amount, double percentage) {
        BigDecimal fixedFee = fxService.convert(
            fixedFeeCurrency,
            targetCurrency,
            fixedFeeAmount
        );
        BigDecimal percentageFee = amount.multiply(
            BigDecimal.valueOf(percentage / 100.0)
        );
        return fixedFee.add(percentageFee).setScale(2, RoundingMode.HALF_UP);
    }
}
