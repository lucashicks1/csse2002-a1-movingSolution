package mms.storage;

import mms.exceptions.PackingException;
import mms.exceptions.PackingOrderException;
import mms.exceptions.StorageFullException;
import mms.furniture.Furniture;
import mms.furniture.FurnitureType;
import mms.personal.Book;
import mms.personal.ClotheType;
import mms.personal.Clothes;
import mms.personal.Laptop;
import mms.utility.Packable;
import mms.utility.Size;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MovingTruckTest {

    private MovingTruck truck1;

    @Before
    public void setUp() {
        truck1 = new MovingTruck(500, 1500, 2000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidLength1(){
        MovingTruck testTruck = new MovingTruck(500, 2000, 1499);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidLength2() {
        MovingTruck testTruck = new MovingTruck(100, 100, 100, Size.MEDIUM);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeNumbers(){
        MovingTruck testTruck = new MovingTruck(-100, -1000, 100);
    }

    @Test
    public void testConstructorEmpty() {
        MovingTruck testTruck = new MovingTruck(1000, 2000, 2000);
        assertTrue(testTruck.getElements().isEmpty());
    }

    @Test
    public void testGetSize(){
        assertEquals(Size.LARGE, truck1.getSize());
    }

    @Test
    public void testGetVolume(){
        assertEquals(500 * 1500 * 500, truck1.getVolume(), 0.001);
    }

    @Test
    public void testToString1() throws PackingException {
        truck1.pack(new Clothes("Jim", Size.MEDIUM, ClotheType.SOCKS));
        assertEquals("MovingTruck (1/40)", truck1.toString());
    }

    @Test
    public void testToString2() throws PackingException {
        Bag bag = new Bag(100,100,100);
        bag.pack(new Clothes("Jim", Size.MEDIUM, ClotheType.PANTS));
        bag.pack(new Laptop("Owner", 2));
        truck1.pack(bag);

        assertEquals("MovingTruck (2/40)", truck1.toString());
    }

    @Test
    public void testGetMultiplier(){
        assertEquals(4, truck1.getMultiplier());
    }

    @Test
    public void testPack() throws PackingException {
        ArrayList<Packable> testStorage = new ArrayList<>();
        Book book = new Book("Bob", "Book Title", false);

        testStorage.add(book);
        truck1.pack(book);
        assertEquals(testStorage, truck1.getElements());
    }

    @Test(expected = PackingOrderException.class)
    public void testPackOrder() throws PackingException {
        truck1.pack(new Furniture(FurnitureType.TABLE));
        truck1.pack(new Clothes("Owner", Size.LARGE, ClotheType.SOCKS));
    }

    @Test(expected = StorageFullException.class)
    public void testPackFull() throws PackingException {
        truck1.pack(new Bag(1000, 1000, 1000));
        truck1.pack(new Furniture(FurnitureType.TELEVISION));
        truck1.pack(new Furniture(FurnitureType.TABLE));

    }

    @Test
    public void testUnpackPersonal() throws PackingException {
        Laptop laptop = new Laptop("Steve", 2);
        truck1.pack(laptop);
        Packable unpackedItem = truck1.unpack();

        assertEquals(new ArrayList<Packable>(), truck1.getElements());
        assertEquals(laptop, unpackedItem);
    }

    @Test
    public void testUnpackFurniture() throws PackingException {
        Laptop laptop = new Laptop("Owner", 1);
        Furniture television = new Furniture(FurnitureType.TELEVISION);
        truck1.pack(laptop);
        truck1.pack(television);

        Packable unpackedItem = truck1.unpack();

        assertEquals(television, unpackedItem);
    }

    @Test
    public void testUnpackEmpty() {
        assertNull(truck1.unpack());
    }

}