/*
You'll now update the CurrencyConverter class to allow injection of an alternate
formatting method.

1. Modify the CurrencyConverter class so a quote formatter can be
   injected to the constructor, instead of tightly coupling to current
   `QuoteFormatter.formatQuote` method.

   (HINT: You could optionally use constructor overloading to make the
   existing `formatQuote` method a default value for the formatter argument,
   so that you don't need to change other uses of the CurrencyConverter class.)

2. Apply the DIP to think about the interface between the CurrencyConverter and
   the quote formatter method. Create a new QuoteFormatter interface to use
   for this purpose.

3. Modify the formatQuote method and the CurrencyConverter class to fit this
   interface, and run this exercise script to check that everything still works.

4. Update this exercise script so that the converter uses the simpleFormatQuote
   method from this file instead of the default formatQuote method.

   Run the script and check that the output has changed as expected.
*/

import convert.*;
import forex.FxService;
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        FxService fxService = new FxService("www.cashforforeigncash.biz");
        PercentageFeeCalculator calculator = new PercentageFeeCalculator(1.2);

        CurrencyConverter converter = new CurrencyConverter(
            fxService,
            calculator
        );

        BigDecimal amount = new BigDecimal("30.00");
        String quote = converter.generateQuote(amount, "GBP", "YEN");

        System.out.println(quote);
    }

    // A formatter that just tells you how much you get. Nothing else. Feel free
    // to use this in your solution, or write your own.
    public static String simpleFormatQuote(
        BigDecimal amount,
        BigDecimal fees,
        String toCurrency
    ) {
        return String.format(
            "Your quote: %.2f %s",
            amount.subtract(fees).doubleValue(),
            toCurrency
        );
    }
}
