package exception;

public class CarProcessingException extends Exception {
    public CarProcessingException(String message) {
        super(message);
    }
    public CarProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
