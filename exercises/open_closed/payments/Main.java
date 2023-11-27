import cards.*;

public class Main {

  public static void main(String[] args) {
    System.out.print(
      "\n-------------------------------------------------------------------\n\n"
    );

    Mastercarp card1 = new Mastercarp("9988776655443322", "3023-12-15", "143");
    new PaymentProcessor(23.15, card1).call();

    AnchoVisa card2 = new AnchoVisa("0123456789101112", "3022-03-01", "992");
    new PaymentProcessor(250.00, card2).call();

    // You'll be asked to uncomment this code later
    // ClamericanExpress card3 = new ClamericanExpress("2468135746803579", "3024-08-13", "8264");
    // new PaymentProcessor(92.99, card3).call();

    System.out.print(
      "-------------------------------------------------------------------\n\n"
    );
  }
}