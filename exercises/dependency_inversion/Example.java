import convert.*;
import forex.FxService;
import java.math.BigDecimal;

public class Example {

    public static void main(String[] args) {
        // Create a new currency converter
        CurrencyConverter converter = new CurrencyConverter();

        // The amount of money to convert. BigDecimal is used instead of
        // double for more accurate financial calculations
        BigDecimal amountToConvert = new BigDecimal("250.00");

        // A string quote for the conversion that took place
        String quote = converter.generateQuote(amountToConvert);
        System.out.printf("\n\n%s\n\n", quote);
    }
}
