package me.jun.meeting.common.config;

import me.jun.meeting.infra.MeetingApiServiceImpl;
import me.jun.meeting.presentation.InterviewHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlerConfig {

    @Bean
    public InterviewHandler interviewHandler() {
        return new InterviewHandler(new MeetingApiServiceImpl());
    }
}
