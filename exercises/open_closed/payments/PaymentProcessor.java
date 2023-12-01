import api.PaymentService;
import cards.*;
import java.math.BigDecimal;

public class PaymentProcessor {

    private BigDecimal amount;
    private Card card;

    public PaymentProcessor(BigDecimal amount, Card card) {
        this.amount = amount;
        this.card = card;
    }

    public void process() throws InvalidCardException {
        CardValidator.validateCard(card);

        switch (card.getCardType().toLowerCase()) {
            case "mastercarp":
                PaymentService.mastercarpPayment(card, amount);
                break;
            case "anchovisa":
                PaymentService.anchovisaPayment(card, amount);
                break;
            default:
                throw new InvalidCardException("Unknown Card Type");
        }

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
