package kindergarten.management.exceptions;

public class RequestException extends Exception {

    public RequestException(Exception e) {
        super(e);
    }

    public RequestException(String e) {
        super(e);
    }
}
