package com.mashreq.confbooking.model;

import com.mashreq.confbooking.enums.BookingStatus;
import com.mashreq.confbooking.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "booking_details", uniqueConstraints={@UniqueConstraint(columnNames={"bookingDate", "fk_room_id", "startTime", "endTime"})})
public class BookingDetails extends BaseEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="booking_id")
    private String bookingId;

    @Column(name ="fk_room_id")
    private Long roomId;

    private LocalDate bookingDate;

    private String startTime;

    private String endTime;

    private int attendeesCount;

    @Column(name ="status")
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

}
