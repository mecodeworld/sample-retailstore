package app.code.service.exception;

public class NotValidInputException extends RuntimeException {

    private static final long serialVersionUID = -6329006131232135830L;

    /**
     * Instantiates a new not valid input exception.
     *
     * @param message the message
     */
    public NotValidInputException(String message) {
        super(message);
    }
}
