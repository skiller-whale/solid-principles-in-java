/*
1. Update the ComplexFeeCalculator so that it satisfies the interface expected
   by the CurrencyConverter. Don't make any changes to the CurrencyConverter.

2. Update this exercise script to create an instance of the ComplexFeeCalculator
   class, and use this with the CurrencyConverter to charge a fee of:

       £3.50 (GBP) + 1% of the total converted.

   Try running the script to convert 800 CAD to EUR and check everything works.
*/

import convert.*;
import forex.FxService;
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        FxService fxService = new FxService("www.cashforforeigncash.biz");

        // TODO: Create a ComplexFeeCalculator instance that will work with the
        // CurrencyConverter, to charge a fixed fee equivalent to £3.50 (GBP),
        // and a percentage fee equal to 1% of the total converted.
        ComplexFeeCalculator calculator = new ComplexFeeCalculator(
            fxService,
            new BigDecimal("3.50"),
            "GBP"
        );

        CurrencyConverter currencyConverter = new CurrencyConverter(
            fxService,
            calculator
        );
        String quote = currencyConverter.generateQuote(
            new BigDecimal("800"),
            "CAD",
            "EUR"
        );

        System.out.println("\nQuote from more complex converter");
        System.out.println(quote);
    }
}
