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

import com.cmuhatia.icube.question.three.dto.UserResponse;
import com.cmuhatia.icube.question.three.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Handles user database operations
 *
 * @author Cornelius M.
 * @version 1.0.0, 12/05/2020
 */
public interface UserRepository extends CrudRepository<User, Long> {
    /**
     * Used to fetch users filtering with pagination object
     * @param pg {@link Pageable}
     * @return {@link Page} of {@link User}
     */
    Page<User> findAll(Pageable pg);

    /**
     * Fetch user details parsing to {@link UserResponse} object
     * @param id user id
     * @return {@link UserResponse}
     */
    @Query("SELECT new com.cmuhatia.icube.question.three.dto.UserResponse(u) FROM #{#entityName} u WHERE id = ?1")
    UserResponse findOne(Long id);
}
