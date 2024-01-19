package com.mashreq.confbooking.service.room;

import com.mashreq.confbooking.exception.handler.BookingSystemException;
import com.mashreq.confbooking.response.MeetingRoomResponse;
import com.mashreq.confbooking.service.room.MeetingRoomService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class MeetingRoomServiceTest {

    @Autowired
    MeetingRoomService meetingRoomService;

    @Test
    void getAvailableRooms() {
        MeetingRoomResponse availableMeetingRooms = meetingRoomService.getAvailableMeetingRooms("17:15", "17:30");
        assertNotNull(availableMeetingRooms);
        assertEquals(8, availableMeetingRooms.getAvailableMeetingRoomList().size());
    }

    @Test
    void invalidTimeRequestRoomBookingTest() {
        BookingSystemException bookingSystemException = Assertions.assertThrows(BookingSystemException.class, () -> meetingRoomService.getAvailableMeetingRooms("17:30", "17:15"));
        assertNotNull(bookingSystemException.getMessage());
    }

}
