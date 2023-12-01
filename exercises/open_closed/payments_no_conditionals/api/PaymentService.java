// Ignore the code in this file. It's just here to make the exercises work.
// It throws RuntimeExceptions in order to be loud if the card is invalid.
package api;

import cards.*;
import java.time.LocalDate;

public class PaymentService {

    private static void takePayment(
        String cardType,
        String number,
        double amount
    ) {
        String displayNumber =
            number.substring(0, 4) +
            " " +
            number.substring(4, 8) +
            " " +
            number.substring(8, 12) +
            " " +
            number.substring(12);
        System.out.print(
            "Taking " + cardType + " payment from <" + displayNumber + ">"
        );
        try {
            for (int i = 0; i < 4; i++) {
                System.out.print(".");
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread interrupted");
        }
        System.out.println(" complete.");
    }

    private static void checkExpiryDate(Card card) {
        LocalDate expiryDate = LocalDate.parse(card.getExpiryDate());
        if (expiryDate.isBefore(LocalDate.now())) {
            throw new RuntimeException("Card has expired");
        }
    }

    public static void mastercarpPayment(Card card, double amount) {
        if (card.getCvv().length() != 3) {
            throw new RuntimeException(
                "Mastercarp cvv should be 3 digits long"
            );
        }
        checkExpiryDate(card);
        char firstDigit = card.getNumber().charAt(0);
        if (Character.getNumericValue(firstDigit) < 5) {
            throw new RuntimeException(
                "Mastercarp card numbers must start with a digit >= 5"
            );
        }
        takePayment("Mastercarp", card.getNumber(), amount);
    }

    public static void anchovisaPayment(Card card, double amount) {
        char firstDigit = card.getNumber().charAt(0);
        if (Character.getNumericValue(firstDigit) >= 5) {
            throw new RuntimeException(
                "Anchovisa card numbers must start with a digit <= 4"
            );
        }
        checkExpiryDate(card);
        if (card.getCvv().length() != 3) {
            throw new RuntimeException("AnchoVisa cvv should be 3 digits long");
        }
        takePayment("AnchoVisa", card.getNumber(), amount);
    }

    public static void clamexPayment(Card card, double amount) {
        if (card.getCvv().length() != 4) {
            throw new RuntimeException(
                "ClamericanExpress cvv should be 4 digits long"
            );
        }
        checkExpiryDate(card);
        takePayment("Clamerican Express", card.getNumber(), amount);
    }
}
