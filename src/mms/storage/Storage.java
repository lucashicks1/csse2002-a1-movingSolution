package mms.storage;

import mms.exceptions.PackingException;
import mms.exceptions.StorageFullException;
import mms.utility.Packable;
import mms.utility.Size;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents some form of storage entity.
 * A storage class contains and manages an internal inventory of Packable items.
 * A storage entity can only contain so much before the storage unit becomes full.
 */
public abstract class Storage {

    /**
     * The size of the storage object
     */
    private Size size;

    /**
     * Width of the storage object (cm)
     */
    private final double width;

    /**
     * Height of the storage object (cm)
     */
    private final double height;

    /**
     * Length of the storage object (cm)
     */
    private final double length;

    /**
     * Total width of all the items in the storage object
     */
    private double occupiedWidth;

    /**
     * Total height of all the items in the storage object
     */
    private double occupiedHeight;

    /**
     * Total length of all the items in the storage object
     */
    private double occupiedLength;

    /**
     * Records the items placed in this storage object
     */
    private ArrayList<Packable> internalStorage;

    /**
     * Creates an empty medium-sized storage object with a given width, height and length.
     * @param width width of the storage in cm
     * @param height height of the storage in cm
     * @param length length of the storage in cm
     * @throws IllegalArgumentException if width, height, or length <= 0
     */
    public Storage(double width, double height, double length) throws IllegalArgumentException {
        if (width <= 0 || height <= 0 || length <= 0) {
            throw new IllegalArgumentException("Width, height or length <= 0");
        }
        this.size = Size.MEDIUM;
        this.width = width;
        this.height = height;
        this.length = length;
        internalStorage = new ArrayList<>();
        occupiedWidth = occupiedHeight = occupiedLength = 0;
    }

    /**
     * Creates an empty storage object with a given width, height, length and size.
     * @param width width of the storage in cm
     * @param height height of the storage in cm
     * @param length length of the storage in cm
     * @param size size of the storage object
     * @throws IllegalArgumentException if width, height, or length <= 0
     */
    public Storage(
            double width, double height, double length, Size size)
            throws IllegalArgumentException {
        this(width, height, length);
        this.size = size;
    }

    /**
     * Gets the width of the object in cm
     * @return width of this storage object
     */
    public double getWidth() {
        return width;
    }

    /**
     * Gets the height of the object in cm
     * @return height of this storage object
     */
    public double getHeight() {
        return height;
    }

    /**
     * Gets the length of the object in cm
     * @return length of this storage object
     */
    public double getLength() {
        return length;
    }

    /**
     * Returns a list containing all the items in the storage object
     * @return new list containing all items in the storage object
     */
    public List<Packable> getElements() {
        return new ArrayList<>(internalStorage);
    }

    /**
     * Returns a list containing all the items in storage that are of the same class as the
     * reference parameter.
     * @param reference reference object to get the type of
     * @return new list containing all the items in storage that are the same class as the
     * reference parameter
     */
    public List<Packable> getElementsOfType(Packable reference) {
        ArrayList<Packable> internalStorageRefined = new ArrayList<>();

        for (Packable item : internalStorage) {
            if (item.getClass().equals(reference.getClass())) {
                internalStorageRefined.add(item);
            }
        }
        return internalStorageRefined;
    }

    /**
     * Gets the size of the object
     * @return size of the storage
     */
    public Size getSize() {
        return size;
    }

    /**
     * Returns the capacity of the storage.
     * This capacity is determined by both the size and multiplier set by the storage type.
     * The associated capacities for each storage size are:
     * <ul>
     *     <li>Small - 3x multiplier</li>
     *     <li>Medium - 5x multiplier</li>
     *     <li>Large - 10x multiplier</li>
     * </ul>
     * @return capacity of this storage
     */
    public int getCapacity() {
        return switch (size) {
            case SMALL -> 3 * getMultiplier();
            case MEDIUM -> 5 * getMultiplier();
            case LARGE -> 10 * getMultiplier();
        };
    }

    /**
     * Returns the multiplier of this storage object
     * @return multiplier of this storage object
     */
    protected abstract int getMultiplier();

    /**
     * Packs an item into the storage's internal list.
     * @param item the item to add to the list
     * @throws PackingException if current number of items in the storage is >= storage capacity
     * or if two of the following are true:
     * <ul>
     * <li>width of items in storage + new item width > storage width</li>
     * <li>height of items in storage + new item height > storage height</li>
     * <li>length of items in storage + new item length > storage length</li>
     * </ul>
     */
    public void pack(Packable item) throws PackingException {
        boolean tooWide = occupiedWidth + item.getWidth() > width;
        boolean tooHigh = occupiedHeight + item.getHeight() > height;
        boolean tooLong = occupiedLength + item.getLength() > length;

        if (getOccupiedCapacity() >= getCapacity()
                || (tooWide ? (tooHigh || tooLong) : (tooHigh && tooLong))) {
            throw new StorageFullException();
        } else {
            internalStorage.add(item);
            occupiedWidth += item.getWidth();
            occupiedHeight += item.getHeight();
            occupiedLength += item.getLength();
        }
    }

    /**
     * Removes an item from the storage in a first in, first out format.
     * @return item at first index of storage; null if it's empty
     */
    public Packable unpack() {
        if (internalStorage.isEmpty()) {
            return null;
        } else {
            Packable item = internalStorage.remove(0);
            occupiedWidth -= item.getWidth();
            occupiedLength -= item.getLength();
            occupiedHeight -= item.getHeight();
            return item;
        }
    }

    /**
     * Returns how many elements exist in the storage list
     * @return number of elements in storage
     */
    public int getOccupiedCapacity() {
        int numItems = 0;
        for (Packable item : getElements()) {
            if (item instanceof Storage) {
                numItems += ((Storage) item).getOccupiedCapacity();
            } else {
                numItems += 1;
            }
        }
        return numItems;
    }

    /**
     * Returns the human-readable representation of the storage object in the format:
     * <p>'class' ('width', 'height', 'length') size</p>
     * Where:
     * <ul>
     *     <li>'class' - simple name of the storage instance</li>
     *     <li>'width' - width of the storage object</li>
     *     <li>'height' - height of the storage object</li>
     *     <li>'length' - length of the storage object</li>
     *     <li>'size' - size of the storage object</li>
     * </ul>
     * @return string representation of the storage object
     */
    @Override
    public String toString() {
        return String.format("%s (%s, %s, %s) %s",
                this.getClass().getSimpleName(), String.format("%.2f", width),
                String.format("%.2f", height), String.format("%.2f", length), size);
    }

    /**
     * Returns the human-readable representation of the storage object in the format:
     * <p>'tabs''class' ('width', 'height', 'length') size</p>
     * <p>'elementTabs''element1'</p>
     * <p>'elementTabs''element2'</p>
     * <p>.......................</p>
     * <p>'elementTabs''elementN'</p>
     * Where:
     * <ul>
     *     <li>'tabs' - level (parameter) number of tab character(s)</li>
     *     <li>'class' - simple name of the storage instance</li>
     *     <li>'width' - width of the storage object</li>
     *     <li>'height' - height of the storage object</li>
     *     <li>'length' - length of the storage object</li>
     *     <li>'size' - size of the storage object</li>
     *     <li>'elementX - string representation of the storages Xth element. If this element is of
     *     the storage class, its elements are printed with an additional tab (level + 1) </li>
     *     <li>'elementTabs' - 'level' + 1 number of tab character(s)</li>
     * </ul>
     * @param level the number of tabs to indent with
     * @return string representation of the storage object
     */
    public String toString(int level) throws IllegalArgumentException {
        if (level < 0) {
            throw new IllegalArgumentException("Level is less than zero");
        }
        String storageDesc = String.format("%s (%s, %s, %s) %s",
                this.getClass().getSimpleName(), String.format("%.2f", width),
                String.format("%.2f", height), String.format("%.2f", length), size);

        StringBuilder result = new StringBuilder(
                String.format("%s%s", "\t".repeat(level), storageDesc));

        for (Packable item : internalStorage) {
            result.append(System.lineSeparator());

            if (item instanceof Storage) {
                result.append(((Storage) item).toString(level + 1));
            } else {
                result.append("\t".repeat(level + 1)).append(item.toString());
            }
        }
        return result.toString();
    }
}