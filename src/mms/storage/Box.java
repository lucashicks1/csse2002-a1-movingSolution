package mms.storage;

import mms.exceptions.PackingException;
import mms.furniture.Furniture;
import mms.furniture.FurnitureType;
import mms.personal.Laptop;
import mms.utility.Packable;
import mms.utility.Size;

/**
 * A box to store different items in. Boxes can either be fragile or not and have comments them.
 * A box is considered fragile if it contains either a television or laptop.
 */
public class Box extends Storage implements Packable {

    /**
     * A comment about the contents of the box
     */
    private final String comment;

    /**
     * Creates a medium-sized box with a given width, height, length and comment. A comment
     * is not allowed to be null.
     * @param width width of the box in cm
     * @param height height of the box in cm
     * @param length length of the box in cm
     * @param comment comment regarding the contents of the box
     * @throws IllegalArgumentException if the comment is null
     */
    public Box(
            double width, double height, double length, String comment)
            throws IllegalArgumentException {
        super(width, height, length);
        if (comment == null) {
            throw new IllegalArgumentException("Comment is null");
        }
        this.comment = comment;
    }

    /**
     * Creates a box with a given size, width, height, length and comment. A comment
     * is not allowed to be null.
     * @param width width of the box in cm
     * @param height height of the box in cm
     * @param length length of the box in cm
     * @param size size of the box
     * @param comment comment regarding the contents of the box
     * @throws IllegalArgumentException if the comment is null
     */
    public Box(
            double width, double height, double length, Size size, String comment)
            throws IllegalArgumentException {
        super(width, height, length, size);
        this.comment = comment;
        if (comment == null) {
            throw new IllegalArgumentException("Comment is null");
        }
    }

    /**
     * Returns true if the box contains any fragile items (Laptop, Television)
     * @return whether the box has fragile items or not
     */
    public boolean isFragile() {
        for (Packable item : getElements()) {
            // Iterate through each element and check if it's fragile
            if (item instanceof Furniture) {
                if (((Furniture) item).getType().equals(FurnitureType.TELEVISION)) {
                    return true;
                }
            } else if (item instanceof Laptop) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the comment about the box's contents
     * @return comment about the box
     */
    public String getComment() {
        return comment;
    }

    /**
     * Returns the human-readable representation of the box in the format:
     * <p>'class' ('width', 'height', 'length') size - 'comment'</p>
     * Where:
     * <ul>
     *     <li>'class' - simple name of the storage instance</li>
     *     <li>'width' - width of the storage object</li>
     *     <li>'height' - height of the storage object</li>
     *     <li>'length' - length of the storage object</li>
     *     <li>'size' - size of the storage object</li>
     *     <li>'comment' - comment about the box</li>
     * </ul>
     * @return string representation of the box
     */
    @Override
    public String toString() {
        String comment = "";
        if (getComment().isEmpty()) {
            comment = "'\0";
        } else if (isFragile()) {
            comment = getComment() + " FRAGILE";
        }
        return String.format("%s - %s",  super.toString(), comment);
    }

    /**
     * Returns the multiplier associated with boxes (2)
     * @return storage multiplier of boxes
     */
    @Override
    protected int getMultiplier() {
        return 2;
    }

    @Override
    public void pack(Packable item) throws PackingException {
        super.pack(item);
    }

    @Override
    public Packable unpack() {
        return super.unpack();
    }
}