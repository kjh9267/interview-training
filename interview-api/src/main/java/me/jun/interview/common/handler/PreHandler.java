package me.jun.interview.common.handler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jun.interview.interview.application.MeetingApiService;
import org.springframework.web.reactive.function.server.ServerRequest;

import static me.jun.interview.support.Utils.I_USER;

@Slf4j
@AllArgsConstructor
public class PreHandler {

    private MeetingApiService meetingApiService;

    public ServerRequest filterInterviewer(ServerRequest request) {
        String iUser = request.headers()
                .firstHeader(I_USER);

        log.info("I-USER: {}", iUser);

        meetingApiService.isValidInterviewer(iUser);

        return request;
    }
}
