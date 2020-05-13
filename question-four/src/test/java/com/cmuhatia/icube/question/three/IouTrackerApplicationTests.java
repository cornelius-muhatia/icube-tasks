package com.cmuhatia.icube.question.three;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class IouTrackerApplicationTests {

	/**
	 * Rest test client
	 */
	@Autowired
	protected WebTestClient webClient;

	@Test
	void contextLoads() {
	}

}
