package convert;

import forex.FxService;
import java.math.BigDecimal;

public class FixedFeeCalculator {

    private static final BigDecimal DEFAULT_FEE_AMOUNT = new BigDecimal("5.0");
    private static final String DEFAULT_FEE_CURRENCY = "USD";

    private FxService fxService;
    private BigDecimal feeAmount;
    private String feeCurrency;

    // Constructor with default fee amount and currency
    public FixedFeeCalculator(FxService fxService) {
        this(fxService, DEFAULT_FEE_AMOUNT, DEFAULT_FEE_CURRENCY);
    }

    // Constructor with all parameters
    public FixedFeeCalculator(
        FxService fxService,
        BigDecimal feeAmount,
        String feeCurrency
    ) {
        this.fxService = fxService;
        this.feeAmount = feeAmount;
        this.feeCurrency = feeCurrency;
    }

    public BigDecimal calculate(String targetCurrency) {
        return fxService.convert(feeCurrency, targetCurrency, feeAmount);
    }
}
