package mms.furniture;

/**
 * Enum to represent the size of different types of furniture with a width, length and height
 */
public enum FurnitureType {
    /**
     * A chair that you sit on with dimensions of 0.5x1.5x0.5m (WxHxL)
     */
    CHAIR(0.5, 1.5, 0.5),

    /**
     * A dining room table with dimensions of 3x5x1m (WxHxL)
     */
    TABLE(3, 5, 1),

    /**
     * A bed with dimensions of 1.5x2x0.5  (WxHxL)
     */
    BED(1.5, 2, 0.5),

    /**
     * A desk with dimensions of 1.2x2x1 (WxHxL)
     */
    DESK(1.2, 2, 1),

    /**
     * A TV with dimensions of 1.3x0.75x0.1 (WxHxL)
     */
    TELEVISION(1.3, 0.75, 0.1);

    /**
     * Width of the furniture in metres
     */
    public final double width;

    /**
     * Height of the furniture in metres
     */
    public final double height;

    /**
     * Length of the furniture in metres
     */
    public final double length;

    private FurnitureType(double width, double height, double length) {
        this.width = width;
        this.height = height;
        this.length = length;
    }
}