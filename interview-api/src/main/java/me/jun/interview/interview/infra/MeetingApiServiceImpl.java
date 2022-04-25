package me.jun.interview.interview.infra;

import lombok.extern.slf4j.Slf4j;
import me.jun.interview.interview.application.MeetingApiService;
import me.jun.interview.interview.domain.Interviewer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@Component
public class MeetingApiServiceImpl implements MeetingApiService {

    @Override
    public Mono<Boolean> isValidInterviewer(String email) {
        log.info("meeting service");

        return WebClient.create("http://localhost:8080/api/v1/meetings/?url=" + "localhost:8081/creator@email.com")
                .get()
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Interviewer.class)
                .log()
                .any(interviewer -> interviewer.equals(Interviewer.from(email)));
    }
}
