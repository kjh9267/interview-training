package me.jun.interview.application;

import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface MeetingApiService {

    Mono<ServerResponse> retrieveInterviewers(String email);
}
