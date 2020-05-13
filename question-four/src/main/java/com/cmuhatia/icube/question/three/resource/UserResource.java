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
package com.cmuhatia.icube.question.three.resource;

import com.cmuhatia.icube.question.three.dto.CreateUser;
import com.cmuhatia.icube.question.three.dto.ResponseWrapper;
import com.cmuhatia.icube.question.three.dto.UserResponse;
import com.cmuhatia.icube.question.three.dto.UsersList;
import com.cmuhatia.icube.question.three.entities.User;
import com.cmuhatia.icube.question.three.repository.IouRepository;
import com.cmuhatia.icube.question.three.repository.UserRepository;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

/**
 * User controller. Methods include:
 * <ul>
 *     <li>{@link UserResource#addUser(CreateUser)}</li>
 *     <li>{@link UserResource#getUsers(Pageable)}</li>
 *     <li>{@link UserResource#deleteUser(Long)}</li>
 * </ul>
 *
 * @author Cornelius M.
 * @version 1.0.0, 12/05/2020
 */
@RestController
@Api(value = "User Resource")
public class UserResource {
    /**
     * Event logs handler
     */
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * User repository bean
     */
    private final UserRepository userRepo;
    /**
     * IOU Repository
     */
    private final IouRepository iouRepo;

    /**
     * Default constructor
     * @param userRepo {@link UserRepository} bean
     * @param iouRepo {@link IouRepository} bean
     */
    public UserResource(UserRepository userRepo, IouRepository iouRepo) {
        this.userRepo = userRepo;
        this.iouRepo = iouRepo;
    }

    /**
     * Used to create new user
     *
     * @param payload {@link CreateUser}
     * @return {@link ResponseEntity} of {@link ResponseWrapper}
     */
    @ApiOperation(value = "Create User", notes = "Creates new user. " +
            "<p>" +
            "<strong>Note:</strong><br />" +
            "Multiple users can share the same name" +
            "</p>")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created user successfully", response = ResponseWrapper.class),
            @ApiResponse(code = 409, message = "User already exists", response = ResponseWrapper.class)
    })
    @PostMapping("/add")
    public ResponseEntity<ResponseWrapper<UserResponse>> addUser(@Valid @RequestBody CreateUser payload) {
        log.info("Creating new user: {}", payload);
        User user = new User(payload.name);
        try {
            user = this.userRepo.save(user);
        } catch(DataIntegrityViolationException ex){
            log.warn("Failed to create user: {}. User with similar name already exists. \nDetails: {}", user, ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(ResponseWrapper.status(HttpStatus.CONFLICT,
                            "User with a similar name already exists"));
        }
        log.info("Created user successfully: {}", user);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.created(new UserResponse(user)));
    }

    /**
     * Fetch users filtering by pagination object
     * @param pg {@link Page}
     * @return {@link ResponseEntity} of {@link UsersList}
     */
    @ApiOperation(value = "User List", notes = "User list page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", dataType = "int", value = "Page size default is 20"),
            @ApiImplicitParam(name = "page", dataType = "int", value = "Page number default is 0"),
            @ApiImplicitParam(name = "sort", dataType = "string", value = "Field name e.g status,asc/desc",
                    paramType = "query", examples = @Example(value = {
                    @ExampleProperty(value = "'property': 'status,asc/desc'", mediaType = "application/json")}))
    })
    @GetMapping("/users")
    public ResponseEntity<UsersList> getUsers(@ApiIgnore Pageable pg){
        log.info("Get user list, filter by {}", pg);
        Page<User> userPage = this.userRepo.findAll(pg);
        UsersList userList = new UsersList(userPage.getContent());
        userList.pageNumber = userPage.getNumber() + 1;
        userList.pageSize = userPage.getSize();
        userList.totalElements = userPage.getNumberOfElements();
        userList.totalPages = userPage.getTotalPages();
        userList.firstPage = userPage.isFirst();
        userList.lastPage = userPage.isLast();
        return ResponseEntity.ok(userList);
    }

    /**
     * Deletes user with all their IOUs
     *
     * @param id user id
     * @return {@link ResponseEntity}
     */
    @DeleteMapping("/user/{id}")
    @ApiOperation(value = "Delete User", notes = "Deletes users with all their IOUs")
    @ApiResponses(
            @ApiResponse(code = 424, message = "If user doesn't exist", response = ResponseWrapper.class)
    )
    @Transactional
    public ResponseEntity<ResponseWrapper<Void>> deleteUser(@PathVariable("id") Long id){
        log.info("Deleting user(id: {})", id);
        Optional<User> user = this.userRepo.findById(id);
        if(user.isEmpty()){
            log.error("Failed to delete user (id: {}). User doesn't exist", id);
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(ResponseWrapper.status(HttpStatus.FAILED_DEPENDENCY, "User doesn't exist"));
        }
        this.iouRepo.deleteBylenderOrBorrower(user.get(), user.get());
        this.userRepo.delete(user.get());
        log.info("Deleted user(id: {}) successfully", id);
        return ResponseEntity.ok(ResponseWrapper.ok(null));
    }
}
