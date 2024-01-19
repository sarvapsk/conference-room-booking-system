package com.mashreq.confbooking.repository;

import com.mashreq.confbooking.enums.BookingStatus;
import com.mashreq.confbooking.model.BookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<BookingDetails, Long> {

    List<BookingDetails> findByBookingDateAndRoomIdAndStatus(LocalDate bookingDate, Long roomDetailsId, BookingStatus status);
}
