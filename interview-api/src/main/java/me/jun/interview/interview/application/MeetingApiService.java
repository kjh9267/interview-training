package me.jun.interview.interview.application;

import reactor.core.publisher.Mono;

public interface MeetingApiService {

    Mono<Boolean> isValidInterviewer(String email);
}
