package com.cmuhatia.icube.question.three.resource;

import com.cmuhatia.icube.question.three.IouTrackerApplicationTests;
import com.cmuhatia.icube.question.three.dto.CreateUser;
import com.cmuhatia.icube.question.three.dto.ResponseWrapper;
import com.cmuhatia.icube.question.three.dto.UserResponse;
import com.cmuhatia.icube.question.three.dto.UsersList;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Cornelius M.
 * @version 1.0.0, 13/05/2020
 */
class UserResourceTest extends IouTrackerApplicationTests {

    /**
     * Runs all tests related to the following user operations:
     * <ul>
     *     <li>User creation</li>
     *     <li>Fetch users</li>
     *     <li>Delete users</li>
     * </ul>
     */
    @Test
    public void allUserResourceTests(){
        UserResponse user = this.testCreateUser();
        this.testFetchUsers();
        this.testDeleteUser(user.id);
    }

    /**
     * Handles create user request
     *
     * @param payload {@link CreateUser}
     * @return {@link org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec}
     */
    protected WebTestClient.ResponseSpec createUser(CreateUser payload) {
        return this.webClient.post()
                .uri("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(payload), CreateUser.class)
                .exchange();
    }

    /**
     * User creation tests. Test cases include:
     * <ul>
     *     <li>Fields Validation</li>
     *     <li>Existing records validation</li>
     * </ul>
     * @return {@link UserResponse}
     */
    protected UserResponse testCreateUser(){
        CreateUser payload = new CreateUser();
        createUser(payload).expectStatus().isBadRequest();
        payload.name = "Mr. Test";
        FluxExchangeResult<ResponseWrapper<UserResponse>> result = createUser(payload)
                .expectStatus().isCreated().returnResult(
                        new ParameterizedTypeReference<>() {});
        createUser(payload).expectStatus().isEqualTo(HttpStatus.CONFLICT);
        return result.getResponseBody().blockFirst().getData();
    }

    /**
     * Handles users get request
     *
     * @param params get parameters
     * @return {@link WebTestClient.ResponseSpec}
     */
    protected WebTestClient.ResponseSpec fetchUsers(Map<String, String> params) {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.setAll(params);
        return this.webClient.get().uri(builder -> builder.queryParams(requestParams)
                .path("/users")
                .build())
                .exchange();
    }

    /**
     * Tests  fetch user successfully response
     */
    protected void testFetchUsers(){
        WebTestClient.ResponseSpec response = fetchUsers(new HashMap<>());
        response.expectStatus().isOk();
        FluxExchangeResult<UsersList> result = response.returnResult(new ParameterizedTypeReference<>() {});
        assertTrue(result.getResponseBody().blockFirst().totalElements > 0);
    }

    /**
     * Handles user delete requests
     * @param id user id
     * @return {@link org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec}
     */
    protected WebTestClient.ResponseSpec  deleteUser(Long id){
        return this.webClient.delete()
                .uri("/user/" + id)
                .exchange();
    }

    /**
     * Tests delete user. Test cases include:
     * <ul>
     *     <li>Failed dependency for non existing user</li>
     *     <li>Successful request</li>
     * </ul>
     * @param id user id
     */
    protected void testDeleteUser(Long id){
        deleteUser(Long.MAX_VALUE).expectStatus().isEqualTo(HttpStatus.FAILED_DEPENDENCY);
        deleteUser(id).expectStatus().isOk();
    }

}