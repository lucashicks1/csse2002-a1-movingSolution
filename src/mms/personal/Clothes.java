package mms.personal;

import mms.utility.Size;

/**
 * Represent clothes which are owned by someone and come in many types and sizes
 */
public class Clothes extends Personal {

    /**
     * Type of clothing
     */
    private final ClotheType type;

    /**
     * Size of clothing
     */
    private final Size size;

    /**
     * Creates clothes with a given owner, size and type. The dimensions are determined by the size
     * of the clothing where:
     * <ul>
     *     <li>Small - 40x65x10cm (WxHxL)</li>
     *     <li>Medium - 50x70x10cm (WxHxL)</li>
     *     <li>Large - 55x75x10cm (WxHxL)</li>
     * </ul>
     * @param owner owner of the clothing
     * @param size size of the clothing
     * @param type type of clothing
     */
    public Clothes(String owner, Size size, ClotheType type) {
        super(owner);
        switch (size) {
            case SMALL -> super.setDimensions(40, 65, 10);
            case MEDIUM -> super.setDimensions(50, 70, 10);
            case LARGE -> super.setDimensions(55, 75, 10);
        }
        this.size = size;
        this.type = type;
    }

    /**
     * Returns the type of the clothing
     * @return clothing type
     */
    public ClotheType getType() {
        return type;
    }

    /**
     * Returns the size of the clothing
     * @return clothing size
     */
    public Size getSize() {
        return size;
    }

    /**
     * Returns the human-readable representation of the piece of clothing in the format:
     * <p>Clothes ('owner') ('size', 'type')</p>
     * Where:
     * <ul>
     *     <li>'owner' - owner of the clothing</li>
     *     <li>'size' - size of the clothing</li>
     *     <li>'type' - type of clothing</li>
     * </ul>
     * @return string representation of this piece of clothing
     */
    @Override
    public String toString() {
        return String.format("%s (%s, %s)", super.toString(), getSize(), getType());
    }
}