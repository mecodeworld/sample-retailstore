package app.code.service.exception;

public class DataNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 8085359947597748832L;

    /**
     * Instantiates a new data not found exception.
     *
     * @param message the message
     */
    public DataNotFoundException(String message) {
        super(message);
    }
}
