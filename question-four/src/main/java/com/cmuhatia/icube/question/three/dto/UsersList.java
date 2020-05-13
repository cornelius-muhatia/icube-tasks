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

import com.cmuhatia.icube.question.three.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Users list payload wrapper
 *
 * @author Cornelius M.
 * @version 1.0.0, 12/05/2020
 */
public class UsersList {
    /**
     * User lists
     */
    public List<UserResponse> users = new ArrayList<>();
    /**
     * Total pages
     */
    public int totalPages;
    /**
     * Users count
     */
    public int totalElements;
    /**
     * Page number
     */
    public int pageNumber;
    /**
     * Elements per page
     */
    public int pageSize;
    /**
     * If current page is the first page
     */
    public boolean firstPage;
    /**
     * If current page is the last page
     */
    public boolean lastPage;

    /**
     * Default constructor used by JPA
     */
    public UsersList(){

    }

    /**
     * Initializes {@link List} of {@link UserResponse} from {@link List} of {@link User}
     *
     * @param users {@link List} of {@link User}
     */
    public UsersList(List<User> users){
        this.users = users.stream().map(UserResponse::new).collect(Collectors.toList());
    }
}
