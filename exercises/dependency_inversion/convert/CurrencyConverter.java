package convert;

import forex.FxService;
import java.math.BigDecimal;

public class CurrencyConverter {

    private FxService fxService;

    public CurrencyConverter() {
        this.fxService = new FxService("www.fx.com");
    }

    public String generateQuote(BigDecimal amount) {
        String fromCurrency = "USD";
        String toCurrency = "EUR";

        BigDecimal convertedAmount = fxService.convert(
            fromCurrency,
            toCurrency,
            amount
        );

        BigDecimal fees = new PercentageFeeCalculator(0.5)
            .calculate(convertedAmount);

        return QuoteFormatter.formatQuote(
            amount,
            fromCurrency,
            toCurrency,
            convertedAmount,
            fees
        );
    }

    private static class QuoteFormatter {

        public static String formatQuote(
            BigDecimal amount,
            String fromCurrency,
            String toCurrency,
            BigDecimal convertedAmount,
            BigDecimal fees
        ) {
            return String.format(
                "Your quote to convert %s %s into %s:\n\t%s %s (Fees: %s %s)",
                amount.setScale(2).toString(),
                fromCurrency,
                toCurrency,
                convertedAmount.setScale(2).toString(),
                toCurrency,
                fees.setScale(2).toString(),
                toCurrency
            );
        }
    }
}
