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

import com.cmuhatia.icube.question.three.dto.CreateIou;
import com.cmuhatia.icube.question.three.dto.ResponseWrapper;
import com.cmuhatia.icube.question.three.entities.Iou;
import com.cmuhatia.icube.question.three.entities.User;
import com.cmuhatia.icube.question.three.repository.IouRepository;
import com.cmuhatia.icube.question.three.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Manages IOU requests. Methods includes:
 * <ul>
 *     <li>{@link IouResource#addIou(CreateIou)}</li>
 * </ul>
 * @author Cornelius M.
 * @version 1.0.0, 12/05/2020
 */
@RestController
@ApiOperation(value = "IOU Resource")
public class IouResource {
    /**
     * Event logs handler
     */
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * {@link IouRepository} bean
     */
    private final IouRepository iouRepo;
    /**
     * {@link UserRepository} bean
     */
    private final UserRepository userRepo;

    /**
     * Default constructor
     *
     * @param iouRepo {@link IouRepository}
     * @param userRepo {@link UserRepository}
     */
    public IouResource(IouRepository iouRepo, UserRepository userRepo) {
        this.iouRepo = iouRepo;
        this.userRepo = userRepo;
    }

    /**
     * Creates new IOU.
     *
     * @param payload {@link CreateIou}
     * @return {@link ResponseEntity} with code 201 on success, 400 for validation errors and 424 if borrower/lender id doesn't exist
     */
    @ApiOperation(value = "Add IOU")
    @ApiResponses(
            @ApiResponse(code = 424, message = "Either borrow/lender id doesn't exist")
    )
    @PostMapping("/iou")
    public ResponseEntity<ResponseWrapper<CreateIou>> addIou(@Valid @RequestBody CreateIou payload){
        log.info("Creating new IOU: {}", payload);
        //validate user details
        Optional<User> borrower = userRepo.findById(payload.borrower);
        if(borrower.isEmpty()){
            log.error("Failed to create IOU. Borrower doesn't exist");
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(
                    ResponseWrapper.status(HttpStatus.FAILED_DEPENDENCY,
                            "Failed to create IOU. Borrower doesn't exist, please ensure you provide a valid borrower id"));
        }
        Optional<User> lender = userRepo.findById(payload.lender);
        if(lender.isEmpty()){
            log.error("Failed to create IOU. Lender doesn't exist");
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(
                    ResponseWrapper.status(HttpStatus.FAILED_DEPENDENCY,
                            "Failed to create IOU. Lender doesn't exist, please ensure you provide a valid lender id"));
        }
        Iou iou = new Iou();
        iou.setLender(lender.get());
        iou.setBorrower(borrower.get());
        iou.setAmount(payload.amount);
        this.iouRepo.save(iou);
        log.info("Created IOU successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.created(null));

    }
}
