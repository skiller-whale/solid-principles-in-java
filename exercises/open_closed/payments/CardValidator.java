import cards.*;
import java.time.LocalDate;

public class CardValidator {

    public static void validateCard(Card card) throws InvalidCardException {
        LocalDate expiryDate = LocalDate.parse(card.getExpiryDate());
        if (expiryDate.isBefore(LocalDate.now())) {
            throw new InvalidCardException("Card has expired");
        }

        if (card.getCvv().length() != 3) {
            throw new InvalidCardException("cvv should be 3 digits long");
        }

        char firstDigit = card.getNumber().charAt(0);
        if ("mastercarp".equals(card.getCardType())) {
            if (Character.getNumericValue(firstDigit) < 5) {
                throw new InvalidCardException(
                    "mastercarp card numbers must start with a digit >= 5"
                );
            }
        } else if ("anchovisa".equals(card.getCardType())) {
            if (Character.getNumericValue(firstDigit) >= 5) {
                throw new InvalidCardException(
                    "anchovisa card numbers must start with a digit <= 4"
                );
            }
        }
    }
}
