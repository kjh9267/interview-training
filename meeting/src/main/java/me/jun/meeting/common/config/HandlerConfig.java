package me.jun.meeting.common.config;

import me.jun.meeting.infra.MeetingApiServiceImpl;
import me.jun.meeting.presentation.MeetingHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlerConfig {

    @Bean
    public MeetingHandler meetingHandler() {
        return new MeetingHandler(new MeetingApiServiceImpl());
    }
}
