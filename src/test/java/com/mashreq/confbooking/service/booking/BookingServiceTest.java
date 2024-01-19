package com.mashreq.confbooking.service.booking;

import com.mashreq.confbooking.constants.AppConstants;
import com.mashreq.confbooking.enums.BookingStatus;
import com.mashreq.confbooking.exception.errorcode.BookingSystemErrors;
import com.mashreq.confbooking.exception.handler.BookingSystemException;
import com.mashreq.confbooking.request.booking.BookingRequest;
import com.mashreq.confbooking.request.queue.BookingQueueRequest;
import com.mashreq.confbooking.response.BookingConfirmationResponse;
import com.mashreq.confbooking.response.BookingResponse;
import com.mashreq.confbooking.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class BookingServiceTest {

    @Autowired
    BookingService bookingService;

    TestUtil testUtil = new TestUtil();

    @BeforeEach
    public void setUp() throws IOException {

    }

    @Test
    void bookConferenceRoomTest() throws IOException {
        BookingRequest bookingRequest = testUtil.loadTestJson("bookingRequest.json", BookingRequest.class);
        bookingRequest.setBookingDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        BookingConfirmationResponse bookingResponse = bookingService.bookConferenceRoom(bookingRequest);
        assertEquals(bookingResponse.getStatus(), AppConstants.SUCCESS);
    }

    @Test
    void invalidTimeRequestRoomBookingTest() throws IOException {
        BookingRequest bookingRequest = testUtil.loadTestJson("invalidBookingTimeRequest.json", BookingRequest.class);
        bookingRequest.setBookingDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        BookingSystemException bookingSystemException = Assertions.assertThrows(BookingSystemException.class, () -> bookingService.bookConferenceRoom(bookingRequest));
        assertNotNull(bookingSystemException.getMessage());
    }

    @Test
    void overlappingMaintanenceBookingTest() throws IOException {
        BookingRequest bookingRequest = testUtil.loadTestJson("maintanenceTimeOverlappingRequest.json", BookingRequest.class);
        bookingRequest.setBookingDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        BookingSystemException bookingSystemException = Assertions.assertThrows(BookingSystemException.class,
                () -> bookingService.bookConferenceRoom(bookingRequest));
        assertNotNull(bookingSystemException.getMessage());
        assertEquals(BookingSystemErrors.E_OVERLAPPING_WITH_MAINTANENCE_TIME.getErrorCode(), bookingSystemException.getErrorCode());
    }

    @Test
    void successBookingQueueTest() throws IOException {
        BookingQueueRequest bookingRequest = testUtil.loadTestJson("successBookingQueueRequest.json", BookingQueueRequest.class);
        bookingRequest.getBookingRequest().setBookingDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        BookingResponse bookingResponse = bookingService.processBookingRequest(bookingRequest);
        assertNotNull(bookingResponse);
        assertEquals(bookingRequest.getRoomName(), bookingResponse.getRoomName());
        assertEquals(BookingStatus.BOOKED.name(), bookingResponse.getStatus());
    }

}
