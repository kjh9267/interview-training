package me.jun.interview.presentation;

import lombok.RequiredArgsConstructor;
import me.jun.interview.application.MeetingApiService;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class InterviewHandler {

    private final MeetingApiService meetingApiService;

    public Mono<ServerResponse> joinInterview(ServerRequest request) {
        return meetingApiService.retrieveInterviewers("test email");
    }
}
