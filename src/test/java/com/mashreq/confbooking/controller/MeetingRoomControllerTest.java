package com.mashreq.confbooking.controller;

import com.mashreq.confbooking.service.room.MeetingRoomService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class MeetingRoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MeetingRoomService meetingRoomService;

    @Test
    void fetchAvailableRooms() throws Exception {
        mockMvc.perform(get("/api/v1/retrieve-available-rooms")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                        .param("startTime", "22:00")
                        .param("endTime", "22:15"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("availableMeetingRoomList.[0].meetingRoomName" , is("Amaze")));
    }

    @Test
    void fetchAvailableRoomsInvalidParamTest() throws Exception {
        mockMvc.perform(get("/api/v1/retrieve-available-rooms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("startTime", "22:0000")
                        .param("endTime", "22:15000"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errors", Matchers.hasKey("retrieveAvailableRooms.startTime")))
                .andExpect(jsonPath("$.errors", Matchers.hasKey("retrieveAvailableRooms.endTime")));
    }
}
