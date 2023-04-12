package mms.exceptions;

/**
 * Exception thrown when an error occurs during the packing of a Storage class.
 */
public class PackingException extends Exception {

    /**
     * Creates a PackingException without a detail message
     */
    public PackingException() {
        super();
    }

    /**
     * Creates a PackingException with a detail message explaining why the exception occurred.
     * @param message detail message
     */
    public PackingException(String message) {
        super(message);
    }
}