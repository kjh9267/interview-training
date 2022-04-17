package me.jun.interviewtraining.meeting.presentation;

import lombok.RequiredArgsConstructor;
import me.jun.interviewtraining.common.UserInfo;
import me.jun.interviewtraining.meeting.application.MeetingService;
import me.jun.interviewtraining.meeting.application.dto.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.http.HttpStatus.SEE_OTHER;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/meetings")
public class MeetingController {

    private final MeetingService meetingService;

    @PostMapping
    public ResponseEntity<MeetingResponse> createMeeting(@RequestBody CreateMeetingRequest request,
                                                         @UserInfo RequestUser user) {

        URI uri = WebMvcLinkBuilder
                .linkTo(getClass())
                .toUri();

        MeetingResponse response = meetingService.createMeeting(request, user);

        return ResponseEntity.created(uri)
                .body(response);
    }

    @PatchMapping
    public ResponseEntity<MeetingResponse> joinMeeting(@RequestBody JoinMeetingRequest request,
                                                       @UserInfo RequestUser user) {

        MeetingResponse response = meetingService.joinMeeting(request, user);

        return ResponseEntity.ok()
                .body(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> leaveMeeting(@RequestBody LeaveMeetingRequest request,
                                             @UserInfo RequestUser user) {

        meetingService.leaveMeeting(request, user);

        return ResponseEntity.status(SEE_OTHER)
                .build();
    }
}
