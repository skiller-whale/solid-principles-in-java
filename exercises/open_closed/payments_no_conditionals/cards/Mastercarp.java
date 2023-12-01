package cards;

import api.PaymentService;

public class Mastercarp extends Card {

    public Mastercarp(String number, String expiryDate, String cvv) {
        super("mastercarp", number, expiryDate, cvv);
    }

    @Override
    public void validate() throws InvalidCardException {
        super.validate();
        char firstDigit = getNumber().charAt(0);
        if (Character.getNumericValue(firstDigit) < 5) {
            throw new InvalidCardException(
                "Mastercarp card numbers must start with a digit >= 5"
            );
        }
    }

    public void takePayment(double amount) {
        PaymentService.mastercarpPayment(this, amount);
    }
}
