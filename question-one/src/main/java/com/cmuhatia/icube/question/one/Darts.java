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
package com.cmuhatia.icube.question.one;

import java.util.Objects;

/**
 * @author Cornelius M.
 * @version 1.0.0, 09/05/2020
 */
public class Darts {

    /**
     * A representation of darts board with 4 levels (concentric circles):
     * <ul>
     *     <li>Outside target representing 0 points</li>
     *     <li>Outer circle target representing 1 point</li>
     *     <li>Middle circle representing 5 points</li>
     *     <li>Inner circle representing 10 points</li>
     * </ul>
     *
     * @param y Y coordinates
     * @param x X coordinates
     * @return score depending on the coordinates
     */
    public static int score(int y, int x) {
        if (isInsideCircle(1, y, x)) {
            return 10;
        } else if (isInsideCircle(5, y, x)) {
            return 5;
        } else if (isInsideCircle(10, y, x)) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Checks if a point at (x, y) coordinates falls inside a circle
     * given the center of the circle is at (0, 0) coordinates
     *
     * @param circleRadius radius of the circle
     * @param y            Y coordinates
     * @param x            X coordinates
     * @return true if the point is inside the circle and false otherwise.
     */
    public static boolean isInsideCircle(int circleRadius, int y, int x) {
        return ((y * y) + (x * x)) <= (circleRadius * circleRadius);
    }

    public static void main(String[] args) {
        Integer x = null;
        Integer y = null;
        for(String arg: args){
            if(arg.contains("x=")){
                x = Integer.parseInt(arg.substring(arg.lastIndexOf("=") + 1));
            } else if(arg.contains("y=")){
                y = Integer.parseInt(arg.substring(arg.lastIndexOf("=") + 1));
            }
            System.out.println("Processing argument " + arg);
        }
        if(Objects.isNull(x)){
            throw new RuntimeException("Expects argument x to passedw. Example x=2");
        }
        if(Objects.isNull(y)){
            throw new RuntimeException("Expects argument y to passed. Example y=2");
        }
        System.out.println("Score: " + Darts.score(y, x));
    }
}
