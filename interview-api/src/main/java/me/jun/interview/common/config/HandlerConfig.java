package me.jun.interview.common.config;

import me.jun.interview.infra.MeetingApiServiceImpl;
import me.jun.interview.presentation.InterviewHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlerConfig {

    @Bean
    public InterviewHandler interviewHandler() {
        return new InterviewHandler(new MeetingApiServiceImpl());
    }
}
