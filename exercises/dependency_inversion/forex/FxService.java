// You don't need to look at any of the code in here.
// It's just here to make the exercises work!
package forex;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Optional;

public class FxService {

    private static final Map<String, Double> CONVERSIONS = Map.of(
        "USD",
        1.0,
        "GBP",
        1.27,
        "EUR",
        1.10,
        "YEN",
        0.0068,
        "RMB",
        0.14,
        "CAD",
        0.74
    );
    private static final Map<String, Double> PROVIDER_BIAS = Map.of(
        "www.fx.com",
        1.0,
        "www.forexr.us",
        1.02,
        "www.cashforforeigncash.biz",
        0.97
    );

    // This just exists to make exchange rates different between providers
    private static double getProviderBias(String provider) {
        return Optional
            .ofNullable(PROVIDER_BIAS.get(provider))
            .orElseThrow(() ->
                new IllegalArgumentException(
                    "Unrecognised FX provider " + provider
                )
            );
    }

    private static double getConversionToUsd(String currency) {
        return Optional
            .ofNullable(CONVERSIONS.get(currency))
            .orElseThrow(() ->
                new IllegalArgumentException(
                    "No rates available for currency: " + currency
                )
            );
    }

    private static double getConversionRate(
        String fromCurrency,
        String toCurrency,
        String provider
    ) {
        double fromRate = getConversionToUsd(fromCurrency);
        double toRate = getConversionToUsd(toCurrency);
        double providerBias = getProviderBias(provider);
        return (fromRate / toRate) * providerBias;
    }

    private String provider;

    public FxService(String provider) {
        this.provider = provider;
    }

    public BigDecimal convert(
        String fromCurrency,
        String toCurrency,
        BigDecimal amount
    ) {
        double rate = getConversionRate(fromCurrency, toCurrency, provider);
        return amount
            .multiply(BigDecimal.valueOf(rate))
            .setScale(2, RoundingMode.HALF_UP);
    }
}
