/*
The CurrencyConverter class is used to generate quotes.

It uses:

* An `FxService` to convert money from one currency to another.
* A `PercentageFeeCalculator` which calculates a fee to deduct.

You'll need to update the CurrencyConverter class to make it more flexible:

--------------------------------------------------------------------------------

1. Allow different currencies to be passed as arguments to generateQuote
   (e.g. fromCurrency and toCurrency)

   Update the usage of CurrencyConverter in this file so the output remains the
   same.

2. Inject an instance of the FxService as a dependency to CurrencyConverter
   instead of creating a new FxService in the constructor.

   Update the usage of CurrencyConverter in this file so the output remains the
   same.

   (you'll need to create a new FxService instance and pass it as an argument)

3. Decouple the CurrencyConverter and PercentageFeeCalculator, by injecting a
   fee calculator instance to the constructor. The class signature should now be:

    public CurrencyConverter(fxService, feeCalculator) {
        / ...

   Again, update this exercise file so it runs and produces the same output.
*/

import convert.CurrencyConverter;
import forex.FxService;
import java.math.BigDecimal;

public class Exercise1 {

    public static void main(String[] args) {
        CurrencyConverter converter = new CurrencyConverter();

        String quote = converter.generateQuote(new BigDecimal("250.00"));
        System.out.printf("\n\n%s\n\n", quote);
    }
}
