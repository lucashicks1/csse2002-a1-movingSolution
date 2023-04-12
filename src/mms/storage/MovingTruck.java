package mms.storage;

import mms.exceptions.PackingException;
import mms.exceptions.PackingOrderException;
import mms.furniture.Furniture;
import mms.utility.Packable;
import mms.utility.Size;

import java.util.ArrayList;

/**
 * Represents a moving truck that facilitates the movement of items
 */
public class MovingTruck extends Storage {

    /**
     * Creates an empty large-sized moving truck with a given width, height and length
     * @param width width of the moving truck
     * @param height height of the moving truck
     * @param length length of the moving truck
     * @throws IllegalArgumentException if length < 1500
     */
    public MovingTruck(double width, double height, double length) throws IllegalArgumentException {
        // Truck is given a length of 1500 less than the total length to indicate storage area
        super(width, height, length - 1500, Size.LARGE);
        if (length < 1500) {
            throw new IllegalArgumentException("Given length is less than 1500");
        }
    }

    /**
     * Creates an empty moving truck with a given width, height, length and size
     * @param width width of the moving truck
     * @param height height of the moving truck
     * @param length length of the moving truck
     * @param size size of the moving truck
     * @throws IllegalArgumentException if length < 1500
     */
    public MovingTruck(
            double width, double height, double length, Size size)
            throws IllegalArgumentException {
        super(width, height, length - 1500, size);
        if (length < 1500) {
            throw new IllegalArgumentException("Given length is less than 1500");
        }
    }

    @Override
    public void pack(Packable item) throws PackingException {
        if (!(item instanceof Furniture)) {
            for (Packable packedItem : getElements()) {
                if (packedItem instanceof Furniture) {
                    throw new PackingOrderException("Only furniture can be added now.");
                }
            }
        }
        super.pack(item);
    }

    /**
     * Removes an item from the truck's  internal list. First, all the furniture is removed in a
     * first in, first out format. After, all the other items are removed in a first in, last out
     * format.
     * @return item removed from the list
     */

    @Override
    public Packable unpack() {
        ArrayList<Packable> unpackedFurniture = new ArrayList<>();
        ArrayList<Packable> unpackedItems = new ArrayList<>();
        Packable unpackedItem;

        int j = getElements().size();

        // Unpack each item from the truck
        for (int i = 0; i < j; i++) {
            Packable item = super.unpack();
            // Add to different arraylist depending on whether it's furniture or not
            if (item instanceof Furniture) {
                unpackedFurniture.add(item);
            } else {
                unpackedItems.add(item);
            }
        }

        // Unpack furniture if still in truck (FIFO)
        if (!unpackedFurniture.isEmpty()) {
            unpackedItem = unpackedFurniture.remove(0);
        } else if (!unpackedItems.isEmpty()) {
            // If there is no furniture, unpack that normal items (FILO)
            unpackedItem = unpackedItems.remove(unpackedItems.size() - 1);
        } else {
            unpackedItem = null;
        }

        // Repack all non-furniture items
        for (Packable item : unpackedItems) {
            try {
                pack(item);
            } catch (PackingException e) {
                // As pack method throws packingException and unpack doesn't, this exception is
                // caught.
            }
        }

        // Lastly, repack all other furniture items
        for (Packable item : unpackedFurniture) {
            try {
                pack(item);
            } catch (PackingException e) {
                // As pack method throws packingException and unpack doesn't, this exception is
                // caught.
            }
        }

        return unpackedItem;
    }

    /**
     * Returns the human-readable representation of the moving truck in the format:
     * <p>MovingTruck ('capacity'/'totalCapacity')</p>
     * Where:
     * <ul>
     *     <li>'capacity' - current number of items on board the moving truck</li>
     *     <li>'totalCapacity' - max capacity of the moving truck</li>
     * </ul>
     * @return string representation of the storage object
     */
    @Override
    public String toString() {
        return String.format("MovingTruck (%s/%s)", getOccupiedCapacity(), getCapacity());
    }

    /**
     * Returns the multiplier associated with trucks (4)
     * @return storage multiplier of trucks
     */
    @Override
    protected int getMultiplier() {
        return 4;
    }

    /**
     * Returns the volume of the truck's storage area in cubic centimetres. The area is equal to
     * width x height x (length - 1500).
     * @return volume of the truck's storage area
     */
    public double getVolume() {
        return getWidth() * getHeight() * (getLength() - 1500);
    }

    @Override
    public double getLength() {
        // Added 1500 to length to give total truck length, not just storage length
        return super.getLength() + 1500;
    }
}