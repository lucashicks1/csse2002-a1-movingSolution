package mms.personal;

import mms.exceptions.PackingOrderException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {

    private Book book1;
    private Book book2;

    @Before
    public void setUp(){
        book1 = new Book("Bob", "Best Book", true);
        book2 = new Book("Jim", "Worst Book", false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullTitle(){
        Book testBook = new Book("Owner", null, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyTitle(){
        Book testBook = new Book("Owner", "", false);
    }

    @Test
    public void testGetTitle(){
        assertEquals("Best Book", book1.getTitle());
    }

    @Test
    public void testToStringFiction(){
        assertEquals("Book (Bob) Title: Best Book (Fiction)", book1.toString());
    }

    @Test
    public void testToStringNonFiction(){
        assertEquals("Book (Jim) Title: Worst Book (Non-Fiction)", book2.toString());
    }
}