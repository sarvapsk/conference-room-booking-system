package com.mashreq.confbooking.service.queue;

import com.mashreq.confbooking.request.booking.BookingRequest;
import com.mashreq.confbooking.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class QueueServiceImplTest {

    @Autowired
    QueueService queueService;

    @Test
    void pushObjectToQueue() throws Exception {
        queueService.pushItemToQueue("test");
    }


    @Test
    void getObjectFromQueue() throws Exception {
        queueService.pushItemToQueue("test");
        Object itemFromQueue = queueService.getItemFromQueue();
        assertNotNull(itemFromQueue);
    }



}
