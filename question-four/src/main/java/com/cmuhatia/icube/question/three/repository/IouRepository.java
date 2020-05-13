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
package com.cmuhatia.icube.question.three.repository;

import com.cmuhatia.icube.question.three.entities.Iou;
import com.cmuhatia.icube.question.three.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Cornelius M.
 * @version 1.0.0, 12/05/2020
 */
public interface IouRepository extends CrudRepository<Iou, Long> {

    /**
     * Delete all user IOUs
     * @param lender {@link User}
     * @param borrower {@link User}
     */
    @Modifying
    void deleteBylenderOrBorrower(User lender, User borrower);
}
