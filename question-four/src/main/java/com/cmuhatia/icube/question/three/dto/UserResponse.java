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
package com.cmuhatia.icube.question.three.dto;

import com.cmuhatia.icube.question.three.entities.Iou;
import com.cmuhatia.icube.question.three.entities.User;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * User details response wrapper
 *
 * @author Cornelius M.
 * @version 1.0.0, 12/05/2020
 */
public class UserResponse {
    /**
     * User id
     */
    public Long id;
    /**
     * User name
     */
    public String name;
    /**
     * A Map of users the current user owes
     */
    public Map<String ,BigDecimal> owes;
    /**
     * A Map of users that owes the user
     */
    public Map<String, BigDecimal> owed_by;
    /**
     * Account balance after subtracting amount owed
     */
    public BigDecimal balance;

    /**
     * Default constructor used by JacksonMapper
     */
    public UserResponse(){

    }

    /**
     * Initializes:
     * <ul>
     *     <li>{@link UserResponse#owed_by} from {@link User#getOwedBy()}</li>
     *     <li>{@link UserResponse#owes} from {@link User#getOwes()}</li>
     * </ul>
     * @param user {@link User}
     */
    public UserResponse(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.owes = new HashMap<>();
        BigDecimal totalOwes = BigDecimal.ZERO;
        for (Iou debt : user.getOwes()) {
            totalOwes = debt.getAmount().add(totalOwes);
            this.owes.put(debt.getLender().getName(), debt.getAmount());
        }
        this.owed_by = new HashMap<>();
        BigDecimal totalOwed = BigDecimal.ZERO;
        for (Iou borrower : user.getOwedBy()) {
            totalOwed = borrower.getAmount().add(totalOwed);
            this.owed_by.put(borrower.getBorrower().getName(), borrower.getAmount());
        }
        this.balance = totalOwed.subtract(totalOwes);
    }
}
