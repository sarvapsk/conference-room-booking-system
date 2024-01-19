package com.mashreq.confbooking.repository;

import com.mashreq.confbooking.model.RoomDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<RoomDetails, Long> {
    List<RoomDetails> findByIsActiveAndMaxCapacityGreaterThanEqualOrderByMaxCapacityAsc(boolean isActive, int attendeesCount);

    List<RoomDetails> findByIsActiveOrderByMaxCapacityAsc(boolean isActive);

}
