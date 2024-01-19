package com.mashreq.confbooking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashreq.confbooking.constants.AppConstants;
import com.mashreq.confbooking.request.booking.BookingRequest;
import com.mashreq.confbooking.service.booking.BookingService;
import com.mashreq.confbooking.util.TestUtil;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    BookingService bookingService;

    TestUtil testUtil = new TestUtil();

    @Test
    void bookMeetingRoomTest() throws Exception {

        BookingRequest bookingRequest = testUtil.loadTestJson("successBookingRequest.json", BookingRequest.class);
        bookingRequest.setBookingDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        mockMvc.perform(post("/api/v1/book-conf-room")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(bookingRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("status" , is(AppConstants.SUCCESS)));
    }

    @Test
    void invalidBookingMeetingRoomTest() throws Exception {

        BookingRequest bookingRequest = testUtil.loadTestJson("invalidBookingRequest.json", BookingRequest.class);

        mockMvc.perform(post("/api/v1/book-conf-room")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(bookingRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", Matchers.hasKey("startTime")))
                .andExpect(jsonPath("$.errors", Matchers.hasKey("endTime")))
                .andExpect(jsonPath("$.errors", Matchers.hasKey("attendeesCount")))
                .andExpect(jsonPath("$.errors", Matchers.hasKey("bookingDate")));
    }
}
