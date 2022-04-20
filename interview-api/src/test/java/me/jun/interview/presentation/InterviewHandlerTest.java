package me.jun.interview.presentation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@AutoConfigureWebTestClient
@SpringBootTest
class InterviewHandlerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void joinMeetingTest() throws Exception {

        webTestClient.get()
                .uri("/api/v1/interviews")
                .accept(APPLICATION_JSON)
                .exchange()
                .returnResult(String.class)
                .consumeWith(System.out::println);

    }
}