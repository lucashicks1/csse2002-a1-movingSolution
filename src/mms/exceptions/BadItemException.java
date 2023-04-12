package mms.exceptions;

/**
 * Exception thrown when the program attempts to add an item of the incorrect implementing class
 * of Packable to a Storage class.
 */
public class BadItemException extends PackingException {

    /**
     * Creates a BadItemException without a detail message
     */
    public BadItemException() {
        super();
    }

    /**
     * Creates a BadItemException with a detail message explaining why the exception occurred.
     * @param message detail message
     */
    public BadItemException(String message) {
        super(message);
    }
}