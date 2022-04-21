package me.jun.meeting.meeting.presentation;

import lombok.RequiredArgsConstructor;
import me.jun.meeting.common.UserInfo;
import me.jun.meeting.meeting.application.MeetingService;
import me.jun.meeting.meeting.application.dto.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.HttpStatus.SEE_OTHER;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/meetings")
public class MeetingController {

    private final MeetingService meetingService;

    @GetMapping
    public ResponseEntity<MeetingResponse> retrieveMeeting(@RequestParam String url) {
        RetrieveMeetingRequest request = RetrieveMeetingRequest.from(url);
        MeetingResponse response = meetingService.retrieveMeeting(request);
        return ResponseEntity.ok()
                .body(response);
    }

    @PostMapping
    public ResponseEntity<MeetingResponse> createMeeting(@RequestBody @Valid CreateMeetingRequest request,
                                                         @UserInfo RequestUser user) {

        URI uri = WebMvcLinkBuilder
                .linkTo(getClass())
                .toUri();

        MeetingResponse response = meetingService.createMeeting(request, user);

        return ResponseEntity.created(uri)
                .body(response);
    }

    @PatchMapping
    public ResponseEntity<MeetingResponse> joinMeeting(@RequestBody @Valid JoinMeetingRequest request,
                                                       @UserInfo RequestUser user) {

        MeetingResponse response = meetingService.joinMeeting(request, user);

        return ResponseEntity.ok()
                .body(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> leaveMeeting(@RequestBody @Valid LeaveMeetingRequest request,
                                             @UserInfo RequestUser user) {

        meetingService.leaveMeeting(request, user);

        return ResponseEntity.status(SEE_OTHER)
                .build();
    }
}
