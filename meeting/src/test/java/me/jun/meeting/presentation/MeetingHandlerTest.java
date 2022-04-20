package me.jun.meeting.presentation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@AutoConfigureWebTestClient
@SpringBootTest
class MeetingHandlerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void joinMeetingTest() throws Exception {

        webTestClient.get()
                .uri("/api/v1/meetings")
                .accept(APPLICATION_JSON)
                .exchange()
                .returnResult(String.class)
                .consumeWith(System.out::println);

    }
}