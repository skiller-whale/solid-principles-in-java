/*
The FixedFeeCalculator is incompatible with the CurrencyConverter's design.
This means it cannot be substituted for the PercentageFeeCalculator (try running
this script to see what happens)

1. Think about what the interface between FeeCalculators and the
   CurrencyConverter class should look like. At the very least, the interface
   should provide enough data for both the PercentageFeeCalculator and
   FixedFeeCalculator to work with the CurrencyConverter.

   This will probably require you to define a new type that represents the data
   being transferred.

2. Update the CurrencyConverter, PercentageFeeCalculator, and FixedFeeCalculator
   so that they all work with this interface. The CurrencyConverter class should
   work the same way, whichever calculator is passed in.
*/

import convert.*;
import forex.FxService;
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        FxService fxService = new FxService("www.forexr.us");

        // Use the percentage fee converter to generate a quote:
        CurrencyConverter percentageFeeConverter = new CurrencyConverter(
            fxService,
            new PercentageFeeCalculator(0.5)
        );

        System.out.println("\nQuote from percentage fee converter:");
        System.out.println(
            percentageFeeConverter.generateQuote(
                new BigDecimal("500.00"),
                "USD",
                "GBP"
            )
        );

        // Use the fixed fee converter to generate a quote:
        CurrencyConverter fixedFeeConverter = new CurrencyConverter(
            fxService,
            new FixedFeeCalculator(fxService, new BigDecimal("3.50"), "GBP")
        );
        System.out.println("\nQuote from fixed fee converter:");
        System.out.println(
            fixedFeeConverter.generateQuote(
                new BigDecimal("300.00"),
                "CAD",
                "RMB"
            )
        );
    }
}
/*
OPTIONAL EXTRA

3. The fixed fee calculator needs access to an FxService to perform the
   conversion. At the moment, this is provided through injection to the
   constructor.

   How could you change the interface between the CurrencyConverter and its
   fee calculator to guarantee that the same converter is always used for the
   conversion, and for fee calculation?
*/
