package me.jun.meeting.infra;

import lombok.extern.slf4j.Slf4j;
import me.jun.meeting.application.MeetingApiService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class MeetingApiServiceImpl implements MeetingApiService {

    @Override
    public Mono<ServerResponse> retrieveInterviewers(String email) {
        log.info(email);
//        return WebClient.create("http://localhost:8080/" + email)
//                .get()
//                .accept(APPLICATION_JSON)
//                .retrieve()
//                .bodyToMono(ServerResponse.class);

        return ServerResponse.ok()
                        .build();
    }
}
