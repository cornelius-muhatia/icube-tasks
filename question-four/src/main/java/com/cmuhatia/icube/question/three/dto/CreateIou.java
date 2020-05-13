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

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * New IOU payload wrapper
 *
 * @author Cornelius M.
 * @version 1.0.0, 12/05/2020
 */
public class CreateIou {
    /**
     * Lender id
     */
    @NotNull(message = "Lender id is required")
    @ApiModelProperty(notes = "Lender id")
    public Long lender;
    /**
     * Borrower id
     */
    @NotNull(message = "Borrower id is required")
    @ApiModelProperty(notes = "borrower id")
    public Long borrower;
    /**
     * Amount borrowed and should be greater 1
     */
    @NotNull
    @ApiModelProperty(notes = "Amount borrowed. Should be greater than 1")
    @Min(value = 1L)
    public BigDecimal amount;

    @Override
    public String toString() {
        return "CreateIou{" +
                "lender=" + lender +
                ", borrower=" + borrower +
                ", amount=" + amount +
                '}';
    }
}
