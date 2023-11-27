package cards;

import api.PaymentService;
import java.time.LocalDate;

public class ClamericanExpress extends Card {

  public ClamericanExpress(String number, String expiryDate, String cvv) {
    super("clamex", number, expiryDate, cvv);
  }

  @Override
  protected int getExpectedCvvLength() {
    return 4;
  }

  public void takePayment(double amount) {
    PaymentService.clamexPayment(this, amount);
  }
}
