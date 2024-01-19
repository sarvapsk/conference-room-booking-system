package com.mashreq.confbooking.model;

import com.mashreq.confbooking.enums.RoomType;
import com.mashreq.confbooking.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "room_details")
public class RoomDetails  extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="name")
    private String name;

    @Column(name ="max_capacity")
    private int maxCapacity;

    @Column(name ="room_type")
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @Column(name ="is_active")
    private boolean isActive;

    @Column(name = "available_from")
    private String availableFrom;

    @Column(name = "available_to")
    private String availableTo;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "fk_room_id")
    private List<RoomMaintanenceDetails> maintanenceDetailsList;


}
