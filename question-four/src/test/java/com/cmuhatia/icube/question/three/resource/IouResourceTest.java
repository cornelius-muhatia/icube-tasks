package com.cmuhatia.icube.question.three.resource;

import com.cmuhatia.icube.question.three.IouTrackerApplicationTests;
import com.cmuhatia.icube.question.three.dto.CreateIou;
import com.cmuhatia.icube.question.three.dto.CreateUser;
import com.cmuhatia.icube.question.three.entities.User;
import com.cmuhatia.icube.question.three.repository.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Cornelius M.
 * @version 1.0.0, 13/05/2020
 */
class IouResourceTest extends IouTrackerApplicationTests {

//    private User borrower;
//    private User lender;
    @Autowired
    UserRepository userRepo;

//    @BeforeEach
//    @Transactional
//    public  void initialize(){
//        borrower = userRepo.save(new User("User 1"));
//        lender = userRepo.save(new User("User 2"));
//    }

    @Test
    public void createUserTest(){
        CreateIou payload = new CreateIou();
        this.createIou(payload).expectStatus().isBadRequest();

        payload.amount = new BigDecimal(100);
        payload.borrower = Long.MAX_VALUE;
        payload.lender = Long.MAX_VALUE;
        this.createIou(payload).expectStatus().isEqualTo(HttpStatus.FAILED_DEPENDENCY);

        User borrower = userRepo.save(new User("User 1"));
        User lender = userRepo.save(new User("User 2"));
        payload.borrower = borrower.getId();
        payload.lender = lender.getId();
        this.createIou(payload).expectStatus().isCreated();

    }

    /**
     * Handles create iou request
     *
     * @param payload {@link CreateIou}
     * @return {@link org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec}
     */
    protected WebTestClient.ResponseSpec createIou(CreateIou payload) {
        return this.webClient.post()
                .uri("/iou")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(payload), CreateIou.class)
                .exchange();
    }

}