
abstract class PaymentHandler {
    protected PaymentHandler next;

    public void setNext(PaymentHandler next) {
        this.next = next;
    }

    public abstract void handlePayment(double amount);
}

class BankPaymentHandler extends PaymentHandler {
    public void handlePayment(double amount) {
        if (amount <= 500) {
            System.out.println("Paid using bank account: $" + amount);
        } else {
            next.handlePayment(amount);
        }
    }
}

class CreditCardPaymentHandler extends PaymentHandler {
    public void handlePayment(double amount) {
        if (amount <= 1000) {
            System.out.println("Paid using credit card: $" + amount);
        } else {
            next.handlePayment(amount);
        }
    }
}

class PayPalPaymentHandler extends PaymentHandler {
    public void handlePayment(double amount) {
        if (amount <= 1500) {
            System.out.println("Paid using PayPal: $" + amount);
        } else if (next != null) {
            next.handlePayment(amount);
        } else {
            System.out.println("Amount is very high");
        }
    }
}

class ChainOfResponsibilityExample {

    private static PaymentHandler getChainOfPayments() {
        PaymentHandler bank = new BankPaymentHandler();
        PaymentHandler creditCard = new CreditCardPaymentHandler();
        PaymentHandler paypal = new PayPalPaymentHandler();
        bank.setNext(creditCard);
        creditCard.setNext(paypal);
        return bank;
    }

    public static void main(String[] args) {

        PaymentHandler paymentMethod = getChainOfPayments();

        paymentMethod.handlePayment(600);
        paymentMethod.handlePayment(200);
        paymentMethod.handlePayment(1200);
        paymentMethod.handlePayment(600);

    }
}

