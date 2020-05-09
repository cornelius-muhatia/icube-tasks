package com.cmuhatia.icube.question.two;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Cornelius M.
 * @version 1.0.0, 09/05/2020
 */
class ItemTest {

    @Test
    void compareTo() {
        assertEquals(0, new Item(10, 3).compareTo(new Item(10, 3)));
        assertEquals(-1, new Item(10, 1).compareTo(new Item(10, 3)));
        assertEquals(1, new Item(10, 10).compareTo(new Item(10, 3)));
    }

    @Test
    void testEquals() {
        assertEquals(new Item(10, 3), new Item(10, 3));
        assertNotEquals(new Item(10, 36), new Item(10, 3));
    }

    @Test
    void testHashCode(){
        assertEquals(new Item(10, 3).hashCode(), new Item(10, 3).hashCode());
        assertNotEquals(new Item(10, 6).hashCode(), new Item(10, 3).hashCode());
    }
}