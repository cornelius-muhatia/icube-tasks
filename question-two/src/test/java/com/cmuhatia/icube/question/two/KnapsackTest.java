package com.cmuhatia.icube.question.two;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Cornelius M.
 * @version 1.0.0, 09/05/2020
 */
class KnapsackTest {

    public final double MAX_KNAPSACK_WEIGHT = 10;

    @Test
    void getMaxWeight() {
        assertEquals(90,
                Knapsack.getMaxWeight(
                        new TreeSet<>(Set.of(
                                new Item(5, 10),
                                new Item(4, 40),
                                new Item(6, 30),
                                new Item(4, 50))),
                        MAX_KNAPSACK_WEIGHT));
    }
}