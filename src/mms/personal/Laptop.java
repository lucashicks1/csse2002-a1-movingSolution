package mms.personal;

/**
 * Represents a laptop object
 */
public class Laptop extends Personal {

    /**
     * Age of the laptop
     */
    private final int age;

    /**
     * Creates a laptop with a given owner and age, with the dimensions 35x20x2 (WxHxL)
     * @param owner owner of this laptop
     * @param age age of the laptop
     * @throws IllegalArgumentException if the age < 0
     */
    public Laptop(String owner, int age) throws IllegalArgumentException {
        super(owner, 35, 20, 2);
        if (age < 0) {
            throw new IllegalArgumentException("Age provided is less than 0");
        }
        this.age = age;
    }

    /**
     * Returns the age of the laptop
     * @return age of the laptop
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns the human-readable representation of the laptop in the format:
     * <p>Laptop ('owner') - 'age'</p>
     * Where:
     * <ul>
     *     <li>'owner' - owner of the laptop</li>
     *     <li>'age' - age of the laptop</li>
     * </ul>
     * @return string representation of this laptop
     */
    @Override
    public String toString() {
        return String.format("%s - %s", super.toString(), getAge());
    }
}