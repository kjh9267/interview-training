package me.jun.interviewtraining.meeting.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static me.jun.interviewtraining.support.UrlUtils.MEETING_URL;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Getter
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String url;

    @Column
    private Long limitInterviewerCount;

    @ElementCollection
    private Set<Interviewer> interviewers;

    public boolean canJoin() {
        return interviewers.size() < limitInterviewerCount;
    }

    public static Meeting of(Long limitInterviewerCount, Interviewer creator) {
        String url = MEETING_URL + "/" + creator.getEmail();

        Meeting meeting = Meeting.builder()
                .interviewers(new HashSet<>())
                .limitInterviewerCount(limitInterviewerCount)
                .url(url)
                .build();

        meeting.interviewers.add(creator);

        return meeting;
    }

    public void join(Interviewer email) {
        interviewers.add(email);
    }
}
