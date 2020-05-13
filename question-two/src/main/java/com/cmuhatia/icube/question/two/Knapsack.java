/*
 * Copyright 2019 Cornelius M.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cmuhatia.icube.question.two;

import java.util.*;

/**
 *
 * @author Cornelius M.
 * @version 1.0.0, 09/05/2020
 */
public class Knapsack {

    /**
     * Calculates the maximum item value a Knapsack can carry.
     *
     * @param items Items to be considered
     * @param maxSackWeight Maximum Knapsack weight
     * @return Maximum item value
     */
    public static double getMaxWeight(TreeSet<Item> items, double maxSackWeight){
        double totalVal = 0;
        double totalWeight = 0;
        Iterator<Item> iterator = items.descendingIterator();
        while(iterator.hasNext()){
            Item item = iterator.next();
            if((totalWeight + item.weight) <= maxSackWeight) {
                totalVal += item.value;
                totalWeight += item.weight;
            } else{
                break;
            }
        }
        return totalVal;
    }
}
