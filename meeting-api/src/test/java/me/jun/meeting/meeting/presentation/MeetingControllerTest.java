package me.jun.meeting.meeting.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.jun.meeting.meeting.application.MeetingService;
import me.jun.meeting.meeting.application.exception.MeetingNotFoundException;
import me.jun.meeting.meeting.domain.exception.LimitInterviewerCountException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static me.jun.meeting.common.ErrorFixture.*;
import static me.jun.meeting.meeting.MeetingFixture.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
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
    void retrieveMeetingTest() throws Exception {
        String expected = objectMapper.writeValueAsString(meetingResponse());

        given(meetingService.retrieveMeeting(any()))
                .willReturn(meetingResponse());

        mockMvc.perform(
                get("/api/v1/meetings/?url=" + MEETING_URL)
                .accept(APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(expected));
    }

    @Test
    void noMeeting_retrieveMeetingFailTest() throws Exception {
        String expected = objectMapper.writeValueAsString(meetingNotFoundErrorResponse());

        given(meetingService.retrieveMeeting(any()))
                .willThrow(new MeetingNotFoundException());

        mockMvc.perform(
                get("/api/v1/meetings/?url=" + MEETING_URL)
                .accept(APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(expected));
    }

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

    @Test
    void meetingNotFound_joinMeetingFailTest() throws Exception {
        given(meetingService.joinMeeting(any(), any()))
                .willThrow(new MeetingNotFoundException());

        String expected = objectMapper.writeValueAsString(meetingNotFoundErrorResponse());

        String content = objectMapper.writeValueAsString(joinMeetingRequest());

        mockMvc.perform(
                patch("/api/v1/meetings")
                .header(I_USER, INTERVIEWER_EMAIL)
                .contentType(APPLICATION_JSON)
                .content(content)
                .accept(APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(expected));
    }

    @Test
    void meetingNotFound_leaveMeetingFailTest() throws Exception {
        doThrow(new MeetingNotFoundException())
                .when(meetingService)
                        .leaveMeeting(any(), any());

        String expected = objectMapper.writeValueAsString(meetingNotFoundErrorResponse());

        String content = objectMapper.writeValueAsString(leaveMeetingRequest());

        mockMvc.perform(
                delete("/api/v1/meetings")
                .contentType(APPLICATION_JSON)
                .content(content)
                .accept(APPLICATION_JSON)
                .header(I_USER, INTERVIEWER_EMAIL)
        )
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(expected));
    }

    @Test
    void limitInterviewerCount_joinFailTest() throws Exception {
        String expected = objectMapper.writeValueAsString(limitInterviewerCountErrorResponse());

        String content = objectMapper.writeValueAsString(joinMeetingRequest());

        given(meetingService.joinMeeting(any(), any()))
                .willThrow(new LimitInterviewerCountException());

        mockMvc.perform(patch("/api/v1/meetings")
                .contentType(APPLICATION_JSON)
                .content(content)
                .header(I_USER, INTERVIEWER_EMAIL)
                .accept(APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(expected));
    }

    @Test
    void invalidContent_failTest() throws Exception {
        String expected = objectMapper.writeValueAsString(invalidContentErrorResponse());

        String content = objectMapper.writeValueAsString(invalidCreateMeetingRequest());

        mockMvc.perform(post("/api/v1/meetings")
                .header(I_USER, INTERVIEWER_EMAIL)
                .contentType(APPLICATION_JSON)
                .content(content)
                .accept(APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(expected));
    }

    @Test
    void emptyEmailHeader_createMeetingFailTest() throws Exception {
        String expected = objectMapper.writeValueAsString(invalidEmailErrorResponse());

        String content = objectMapper.writeValueAsString(createMeetingRequest());

        mockMvc.perform(post("/api/v1/meetings")
                        .contentType(APPLICATION_JSON)
                        .content(content)
                        .accept(APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(expected));
    }

    @Test
    void invalidEmail_failTest() throws Exception {
        String expected = objectMapper.writeValueAsString(invalidEmailErrorResponse());

        String content = objectMapper.writeValueAsString(createMeetingRequest());

        mockMvc.perform(post("/api/v1/meetings")
                .contentType(APPLICATION_JSON)
                .content(content)
                .header(I_USER, "wrong email pattern")
                .accept(APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(expected));
    }

    @Test
    void emptyHttpBody_failTest() throws Exception {
        String expected = objectMapper.writeValueAsString(invalidContentErrorResponse());

        mockMvc.perform(post("/api/v1/meetings")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .header(I_USER, INTERVIEWER_EMAIL)
        )
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(expected));
    }
}