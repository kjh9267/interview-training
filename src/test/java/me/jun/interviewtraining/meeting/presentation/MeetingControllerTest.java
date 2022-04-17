package me.jun.interviewtraining.meeting.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.jun.interviewtraining.meeting.application.MeetingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static me.jun.interviewtraining.meeting.MeetingFixture.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class MeetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MeetingService meetingService;

    @Test
    void createMeetingTest() throws Exception {
        String expected = objectMapper.writeValueAsString(meetingResponse());

        String content = objectMapper.writeValueAsString(createMeetingRequest());

        given(meetingService.createMeeting(any(), any()))
                .willReturn(meetingResponse());

        mockMvc.perform(
                post("/api/v1/meetings")
                .accept(APPLICATION_JSON)
                .header(I_USER, CREATOR_EMAIL)
                .content(content)
                .contentType(APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(expected));
    }

    @Test
    void joinMeetingTest() throws Exception {
        String expected = objectMapper.writeValueAsString(meetingResponse());

        String content = objectMapper.writeValueAsString(joinMeetingRequest());

        given(meetingService.joinMeeting(any(), any()))
                .willReturn(meetingResponse());

        mockMvc.perform(
                patch("/api/v1/meetings")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(content)
                .header(I_USER, INTERVIEWER_EMAIL)
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(expected));
    }

    @Test
    void leaveMeetingTest() throws Exception {
        String content = objectMapper.writeValueAsString(leaveMeetingRequest());

        doNothing()
                .when(meetingService)
                        .leaveMeeting(any(), any());

        mockMvc.perform(
                delete("/api/v1/meetings")
                .content(content)
                .contentType(APPLICATION_JSON)
                .header(I_USER, INTERVIEWER_EMAIL)
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }
}