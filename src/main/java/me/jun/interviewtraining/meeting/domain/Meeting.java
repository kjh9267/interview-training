package me.jun.interviewtraining.meeting.domain;

import lombok.*;
import me.jun.interviewtraining.meeting.domain.exception.LimitInterviewerCountException;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static me.jun.interviewtraining.support.UrlUtils.MEETING_URL;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@EqualsAndHashCode(of = "id")
@Getter
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String url;

    @Column
    private Long limitInterviewerCount;

    @ElementCollection
    private Set<Interviewer> interviewers;

    public void join(Interviewer interviewer) {
        if (!canJoin()) {
            throw new LimitInterviewerCountException();
        }
        interviewers.add(interviewer);
    }

    public void leave(Interviewer interviewer) {
        interviewers.remove(interviewer);
    }

    boolean canJoin() {
        return interviewers.size() < limitInterviewerCount;
    }

    public static Meeting of(Long limitInterviewerCount, Interviewer creator) {
        String url = createUrl(creator);

        Meeting meeting = Meeting.builder()
                .interviewers(new HashSet<>())
                .limitInterviewerCount(limitInterviewerCount)
                .url(url)
                .build();

        meeting.interviewers.add(creator);

        return meeting;
    }

    private static String createUrl(Interviewer creator) {
        return MEETING_URL + "/" + creator.getEmail();
    }
}
