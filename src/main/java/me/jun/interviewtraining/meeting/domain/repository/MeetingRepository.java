package me.jun.interviewtraining.meeting.domain.repository;

import me.jun.interviewtraining.meeting.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}
