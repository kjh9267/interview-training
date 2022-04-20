package me.jun.meeting.application;

import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface MeetingApiService {

    Mono<ServerResponse> retrieveInterviewers(String email);
}
