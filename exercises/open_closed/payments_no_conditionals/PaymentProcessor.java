import api.PaymentService;
import cards.*;

public class PaymentProcessor {

    private double amount;
    private Card card;

    public PaymentProcessor(double amount, Card card) {
        this.amount = amount;
        this.card = card;
    }

    public void process() throws InvalidCardException {
        card.validate();
        card.takePayment(amount);
        System.out.println("  " + amount + " payment taken\n");
    }

    public void call() {
        try {
            process();
        } catch (InvalidCardException e) {
            System.out.println("ERROR PROCESSING PAYMENT: " + e.getMessage());
        }
    }
}
