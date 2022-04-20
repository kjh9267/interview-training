package me.jun.meeting.presentation;

import lombok.RequiredArgsConstructor;
import me.jun.meeting.application.MeetingApiService;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class MeetingHandler {

    private final MeetingApiService meetingApiService;

    public Mono<ServerResponse> joinMeeting(ServerRequest request) {
        return meetingApiService.retrieveInterviewers("test email");
    }
}
