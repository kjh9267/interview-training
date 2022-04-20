package me.jun.meeting.common.config;

import lombok.RequiredArgsConstructor;
import me.jun.meeting.infra.MeetingApiServiceImpl;
import me.jun.meeting.presentation.MeetingHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@EnableWebFlux
@RequiredArgsConstructor
public class WebConfig implements WebFluxConfigurer {

    private final MeetingHandler meetingHandler;

    @Bean
    public RouterFunction<ServerResponse> meetingRouter() {
        return route()
                .GET("/api/v1/meetings", accept(APPLICATION_JSON), meetingHandler::joinMeeting)
                .build();
    }
}
