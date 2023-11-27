package cards;

import api.PaymentService;

public class AnchoVisa extends Card {

  public AnchoVisa(String number, String expiryDate, String cvv) {
    super("anchovisa", number, expiryDate, cvv);
  }

  @Override
  public void validate() throws InvalidCardException {
    super.validate();
    char firstDigit = getNumber().charAt(0);
    if (Character.getNumericValue(firstDigit) > 4) {
      throw new InvalidCardException(
        "Anchovisa card numbers must start with a digit <= 4"
      );
    }
  }

  public void takePayment(double amount) {
    PaymentService.anchovisaPayment(this, amount);
  }
}
