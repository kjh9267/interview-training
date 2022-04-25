package me.jun.interview.common.config;

import lombok.RequiredArgsConstructor;
import me.jun.interview.common.handler.PreHandler;
import me.jun.interview.interview.presentation.InterviewHandler;
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

    private final InterviewHandler interviewHandler;

    private final PreHandler preHandler;

    @Bean
    public RouterFunction<ServerResponse> interviewRouter() {
        return route()
                .before(preHandler::filterInterviewer)
                .POST("/api/v1/interviews", accept(APPLICATION_JSON), interviewHandler::joinInterview)
                .build();
    }
}
