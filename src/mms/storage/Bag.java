package mms.storage;

import mms.exceptions.BadItemException;
import mms.exceptions.PackingException;
import mms.exceptions.StorageFullException;
import mms.personal.Personal;
import mms.utility.Packable;
import mms.utility.Size;

/**
 * A bag to store different personal items
 */
public class Bag extends Storage implements Packable {

    /**
     * Creates an empty medium-sized bag with a given width, height and length.
     * @param width width of the bag in cm
     * @param height height of the bag in cm
     * @param length length of the bag in cm
     */
    public Bag(double width, double height, double length) {
        super(width, height, length);
    }

    /**
     * Creates an empty bag with a given width, height, length and size.
     * @param width width of the bag in cm
     * @param height height of the bag in cm
     * @param length length of the bag in cm
     * @param size size of the bag
     */
    public Bag(double width, double height, double length, Size size) {
        super(width, height, length, size);
    }

    /**
     * Returns the multiplier associated with bags (1)
     * @return storage multiplier of bags
     */
    @Override
    protected int getMultiplier() {
        return 1;
    }

    /**
     * Adds an item to the bag's internal list. If the bag is at capacity, or the item doesn't
     * conform to the bag's dimensions an exception is thrown. If the item is not of the personal
     * class an exception is also thrown.
     * @param item the item to add to the list
     * @throws BadItemException if the item being added is not of the personal class
     * @throws StorageFullException if the total weight of the items + the weight of the item being
     * added > bag's max weight (1.5kg)
     * @throws StorageFullException  if the current number of items currently inside this storage
     * (previously packed items) >= the capacity of this storage or if two of the following are
     * true:
     * <ul>
     *     <li>∑ widths of items in the storage + width of new item > storage width,</li>
     *     <li>∑ heights of items in the storage + height of new item > storage height,</li>
     *     <li>∑ lengths of items in the storage + length of new item > storage length</li>
     * </ul>
     */
    @Override
    public void pack(Packable item) throws PackingException {
        if (!(item instanceof Personal)) {
            throw new BadItemException("Not an instance of the personal class");
        } else if (getOccupiedCapacity() * Personal.getBaseWeight() > 1500) {
            throw new StorageFullException("Exceeding bag's maximum weight");
        } else {
            super.pack(item);
        }
    }
}