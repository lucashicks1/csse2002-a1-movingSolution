package mms.personal;

import mms.utility.Packable;

/**
 * Represents a personal item, which come in many sizes but always have an owner
 */
public abstract class Personal implements Packable {

    /**
     * Width of the personal item in cm
     */
    private double width;

    /**
     * Length of the personal item in cm
     */
    private double length;

    /**
     * Height of the personal item in cm
     */
    private double height;

    /**
     * The owner of the personal item
     */
    private final String owner;

    /**
     * Creates a personal item with a given owner and a width, length and height of 0
     * @param owner owner of the personal item
     * @throws IllegalArgumentException if the given owner is null or an empty string
     */
    public Personal(String owner) throws IllegalArgumentException {
        if (owner == null || owner.isEmpty()) {
            throw new IllegalArgumentException("Owner is null or empty");
        }
        this.owner = owner;
        width = 0;
        height = 0;
        length = 0;
    }

    /**
     * Creates a personal item with a given owner, width, length and height
     * @param owner owner of the personal item
     * @param width width of the personal item in cm
     * @param height height of the personal item in cm
     * @param length length of the personal item in cm
     * @throws IllegalArgumentException if the given owner is null or an empty string or if the
     * length, width or height is < 0
     */
    public Personal(
            String owner, double width, double height, double length)
            throws IllegalArgumentException {

        this(owner);
        if (width < 0 || height < 0 || length < 0) {
            throw new IllegalArgumentException();
        }
        this.width = width;
        this.height = height;
        this.length = length;
    }

    /**
     * Returns the weight of the personal item (250)
     * @return base weight of a personal item
     */
    public static int getBaseWeight() {
        return 250;
    }

    /**
     * Returns the width of the personal item in cm
     * @return width of the item
     */
    @Override
    public double getWidth() {
        return width;
    }

    /**
     * Returns the length of the personal item in cm
     * @return length of the item
     */
    @Override
    public double getLength() {
        return length;
    }

    /**
     * Returns the height of the personal item in cm
     * @return height of the item
     */
    @Override
    public double getHeight() {
        return height;
    }

    /**
     * Updates the length, width and height of a personal item using the given values
     * @param width new width of the item in cm
     * @param height new height of the item in cm
     * @param length new length of the item in cm
     */
    protected void setDimensions(double width, double height, double length) {
        this.width = width;
        this.height = height;
        this.length = length;
    }

    /**
     * Returns the owner of the personal item
     * @return owner of item
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Returns the human-readable representation of the item in the format:
     * <p>'class' ('owner')</p>
     * Where:
     * <ul>
     *     <li>'class' - item's instance class simple name</li>
     *     <li>'owner' - owner of the personal item</li>
     * </ul>
     * @return string representation of this personal item
     */
    @Override
    public String toString() {
        return String.format("%s (%s)", this.getClass().getSimpleName(), getOwner());
    }
}