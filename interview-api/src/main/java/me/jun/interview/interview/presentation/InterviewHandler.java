package me.jun.interview.interview.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class InterviewHandler {

    public Mono<ServerResponse> joinInterview(ServerRequest request) {

        return ServerResponse.ok()
                .bodyValue("result!!!!");
    }
}
