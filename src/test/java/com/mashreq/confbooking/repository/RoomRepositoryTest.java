package com.mashreq.confbooking.repository;

import com.mashreq.confbooking.model.RoomDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class RoomRepositoryTest {

    @Autowired
    RoomRepository roomRepository;

    @Test
    void retrieveRoomTest() {
        List<RoomDetails> all = roomRepository.findAll();
        assertTrue(all.size() > 0);
    }
}
