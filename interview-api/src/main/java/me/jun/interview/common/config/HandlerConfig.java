package me.jun.interview.common.config;

import me.jun.interview.common.handler.PreHandler;
import me.jun.interview.interview.infra.MeetingApiServiceImpl;
import me.jun.interview.interview.presentation.InterviewHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlerConfig {

    @Bean
    public InterviewHandler interviewHandler() {
        return new InterviewHandler();
    }

    @Bean
    public PreHandler preHandler() {
        return new PreHandler(new MeetingApiServiceImpl());
    }
}
