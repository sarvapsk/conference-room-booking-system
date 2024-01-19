package com.mashreq.confbooking.service;

import com.mashreq.confbooking.enums.BookingStatus;
import com.mashreq.confbooking.exception.errorcode.BookingSystemErrors;
import com.mashreq.confbooking.exception.handler.BookingSystemException;
import com.mashreq.confbooking.model.BookingDetails;
import com.mashreq.confbooking.model.RoomDetails;
import com.mashreq.confbooking.repository.BookingRepository;
import com.mashreq.confbooking.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.mashreq.confbooking.util.DateUtils.getLocalDateTimeFromTime;

public abstract class AbstractRoomService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    RoomRepository roomRepository;

    protected boolean hasMaintanenceOverlapping(String startTime, String endTime, RoomDetails eachRoom) {
        eachRoom.getMaintanenceDetailsList().stream()
                .filter(eachMainDetails -> isOverlapping( eachMainDetails.getStartTime(), eachMainDetails.getEndTime(),
                        startTime, endTime))
                .findAny()
                .ifPresent(a -> {
                    throw new BookingSystemException(BookingSystemErrors.E_OVERLAPPING_WITH_MAINTANENCE_TIME.getErrorCode(),
                            BookingSystemErrors.E_OVERLAPPING_WITH_MAINTANENCE_TIME.getMessage());
                });

        return false;
    }

    protected boolean hasOverlappedWithMaintanence(String startTime, String endTime, RoomDetails eachRoom) {
        return  eachRoom.getMaintanenceDetailsList().stream()
                .anyMatch(eachMainDetails -> isOverlapping( eachMainDetails.getStartTime(), eachMainDetails.getEndTime(),
                        startTime, endTime));
    }

    protected boolean isRoomAvailable(String startTime, String endTime, long roomId) {
        // Checking overlapping time with exising booking ...
        List<BookingDetails> currentBookingsByRoom = bookingRepository
                .findByBookingDateAndRoomIdAndStatus(LocalDate.now(), roomId, BookingStatus.BOOKED);

        boolean isOverlappingWithExistingBooking = currentBookingsByRoom.stream()
                .anyMatch(eachBooking -> isOverlapping(eachBooking.getStartTime(), eachBooking.getEndTime(),
                        startTime, endTime));

        return !isOverlappingWithExistingBooking;
    }

    protected boolean hasSpecifiedInterval(String startTime, String endTime, int interval) {
        LocalDateTime startDateTime = getLocalDateTimeFromTime(startTime);
        LocalDateTime endDateTime = getLocalDateTimeFromTime(endTime);

        long minsDifference = startDateTime.until( endDateTime, ChronoUnit.MINUTES );

        return minsDifference == Long.valueOf(interval);
    }

    protected static boolean isOverlapping(String existingStartTime, String existingEndTime,
                                        String requestedStartTime, String requestedEndTime) {
        LocalDateTime exstStartTime = getLocalDateTimeFromTime(existingStartTime);
        LocalDateTime exstEndTime = getLocalDateTimeFromTime(existingEndTime);

        LocalDateTime reqStartTime = getLocalDateTimeFromTime(requestedStartTime);
        LocalDateTime reqEndTime = getLocalDateTimeFromTime(requestedEndTime);

        return (exstStartTime.isBefore(reqEndTime) && exstEndTime.isAfter(reqStartTime)) ||
                (reqStartTime.isBefore(exstEndTime) && reqEndTime.isAfter(exstStartTime)) ||
                (exstStartTime.isEqual(reqStartTime) && exstEndTime.isEqual(reqEndTime));
    }
}
