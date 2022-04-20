package me.jun.interviewtraining.meeting.domain.repository;

import me.jun.interviewtraining.meeting.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    Optional<Meeting> findByUrl(String url);
}
