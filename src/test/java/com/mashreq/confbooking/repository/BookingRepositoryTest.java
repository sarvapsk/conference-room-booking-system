package com.mashreq.confbooking.repository;

import com.mashreq.confbooking.enums.BookingStatus;
import com.mashreq.confbooking.model.BookingDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class BookingRepositoryTest {

    @Autowired
    BookingRepository bookingRepository;

    @Test
    void bookConferenceRoomTest() {
        BookingDetails bookingDetails = new BookingDetails();
        bookingDetails.setRoomId(100l);
        bookingDetails.setBookingId("123");
        bookingDetails.setStatus(BookingStatus.BOOKED);
        bookingDetails.setAttendeesCount(3);
        bookingDetails.setStartTime("14:45");
        bookingDetails.setEndTime("15:00");
        BookingDetails savedBooking = bookingRepository.save(bookingDetails);

        assertNotNull(savedBooking);
    }
}
