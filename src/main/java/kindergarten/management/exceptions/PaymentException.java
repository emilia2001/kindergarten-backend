package kindergarten.management.exceptions;

public class PaymentException extends Exception {

    public PaymentException(Exception e) {
        super(e);
    }

    public PaymentException(String e) {
        super(e);
    }
}
