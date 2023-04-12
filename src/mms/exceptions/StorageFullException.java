package mms.exceptions;

/**
 * Exception thrown when the program attempts to add more items to storage than that particular
 * storage unit can handle.
 */
public class StorageFullException extends PackingException {

    /**
     * Creates a StorageFullException without a detail message
     */
    public StorageFullException() {
        super();
    }

    /**
     * Creates a StorageFullException with a detail message explaining why the exception occurred.
     * @param message detail message
     */
    public StorageFullException(String message) {
        super(message);
    }
}