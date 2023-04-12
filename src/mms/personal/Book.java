package mms.personal;

/**
 * Represents a book that can be read, that has an owner and title and is fictional or not
 */
public class Book extends Personal {

    /**
     * Title of the book
     */
    private final String title;

    /**
     * Value for whether the book is fiction or not
     */
    private final boolean isFiction;

    /**
     * Creates a book with a given owner and title, also recording whether it is fiction or
     * non-fiction. The dimensions of a book are 20x20x5cm (WxHxL)
     * @param owner the owner of the book
     * @param title the title of the book
     * @param isFiction whether the book is fiction or non-fiction
     * @throws IllegalArgumentException if the title and/or owner is null or an empty string
     */
    public Book(String owner, String title, boolean isFiction) throws IllegalArgumentException {
        super(owner, 20, 20, 5);
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.title = title;
        this.isFiction = isFiction;
    }

    /**
     * Returns the title of the book
     * @return title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the human-readable representation of the book in the format:
     * <p>Book ('owner') Title: 'title' ('isFiction')</p>
     * Where:
     * <ul>
     *     <li>'owner' - owner of the book</li>
     *     <li>'title' - title of the book</li>
     *     <li>'isFiction' - Fiction or Non-Fiction depending on whether the book is fictional</li>
     * </ul>
     * @return string representation of this book
     */
    @Override
    public String toString() {
        return String.format(
                "%s Title: %s (%s)", super.toString(), getTitle(),
                isFiction ? "Fiction" : "Non-Fiction"
        );
    }
}