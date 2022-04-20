package me.jun.meeting.meeting.domain.repository;

import me.jun.meeting.meeting.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    Optional<Meeting> findByUrl(String url);
}
