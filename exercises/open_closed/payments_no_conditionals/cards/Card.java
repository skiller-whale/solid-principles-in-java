package cards;

import java.time.LocalDate;

public abstract class Card {

  private String cardType;
  private String number;
  private String expiryDate;
  private String cvv;

  public Card(String cardType, String number, String expiryDate, String cvv) {
    this.cardType = cardType;
    this.number = number;
    this.expiryDate = expiryDate;
    this.cvv = cvv;
  }

  public String getCardType() {
    return cardType;
  }

  public String getNumber() {
    return number;
  }

  public String getExpiryDate() {
    return expiryDate;
  }

  public String getCvv() {
    return cvv;
  }

  public void validate() throws InvalidCardException {
    LocalDate expiry = LocalDate.parse(expiryDate);
    if (expiry.isBefore(LocalDate.now())) {
      throw new InvalidCardException("Card has expired");
    }
    if (cvv.length() != 3) {
      throw new InvalidCardException("cvv should be 3 digits long");
    }
  }

  public abstract void takePayment(double amount);
}
