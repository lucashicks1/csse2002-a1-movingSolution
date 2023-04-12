package mms.furniture;

import mms.utility.Packable;

/**
 * Represents some household furniture that needs to be moved.
 */
public class Furniture implements Packable {

    /**
     * Type of the furniture
     */
    private final FurnitureType type;

    /**
     * Creates a piece of furniture with the given type.
     * @param type furniture type
     */
    public Furniture(FurnitureType type) {
        this.type = type;
    }

    /**
     * Returns the type of the furniture object
     * @return furniture type
     */
    public FurnitureType getType() {
        return type;
    }

    /**
     * Returns the human-readable string representation of the furniture object in the format:
     * <p> Furniture ('type')</p>
     * @return human-readable string representation of the furniture
     */
    @Override
    public String toString() {
        return String.format("Furniture (%s)", type.name());
    }

    @Override
    public double getWidth() {
        return type.width * 100;
    }

    @Override
    public double getHeight() {
        return type.height * 100;
    }

    @Override
    public double getLength() {
        return type.length * 100;
    }
}