package mms.exceptions;

/**
 * Exception thrown when the program attempts to add an items in the incorrect order.
 */
public class PackingOrderException extends PackingException {

    /**
     * Creates a PackingOrderException without a detail message
     */
    public PackingOrderException() {
        super();
    }

    /**
     * Creates a PackingOrderException with a detail message explaining why the exception occurred.
     * @param message detail message
     */
    public PackingOrderException(String message) {
        super(message);
    }
}