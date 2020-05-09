package com.cmuhatia.icube.question.one;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Cornelius M.
 * @version 1.0.0, 09/05/2020
 */
class DartsTest {

    @Test
    void score() {
        assertEquals(1, Darts.score(0, 10));
        assertEquals(10, Darts.score(0, 0));
        assertEquals(10, Darts.score(1, 0));
        assertEquals(10, Darts.score(0, 1));
        assertEquals(5, Darts.score(1, 1));

    }

    @Test
    void isInsideCircle() {
        assertTrue(Darts.isInsideCircle(10, 0, 10));
        assertFalse(Darts.isInsideCircle(10, 11, 0));
    }
}