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

/**
 * @author Cornelius M.
 * @version 1.0.0, 09/05/2020
 */
public class Item implements Comparable<Item> {
    /**
     * Item weight
     */
    public double weight;
    /**
     * Item value
     */
    public double value;

    /**
     * Default constructor
     *
     * @param weight item weight
     * @param value  item value
     */
    public Item(double weight, double value) {
        this.weight = weight;
        this.value = value;
    }

    /**
     * Compares items based on their value per unit weight
     *
     * @param counterPart {@link Item} counter part item
     * @return 1 if the current item is greater that counter part item,
     * 0 if they are equal and -1 otherwise
     */
    @Override
    public int compareTo(Item counterPart) {
        double currentWeightValue = this.value / this.weight;
        double cWeightValue = counterPart.value / counterPart.weight;
        return Double.compare(currentWeightValue, cWeightValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (Double.compare(item.weight, weight) != 0) return false;
        return Double.compare(item.value, value) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(weight);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "weight=" + weight +
                ", value=" + value +
                '}';
    }
}
