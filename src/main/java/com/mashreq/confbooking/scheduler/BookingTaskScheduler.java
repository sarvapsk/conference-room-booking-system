package com.mashreq.confbooking.scheduler;

import com.mashreq.confbooking.request.queue.BookingQueueRequest;
import com.mashreq.confbooking.service.booking.BookingService;
import com.mashreq.confbooking.service.queue.QueueService;
import com.mashreq.confbooking.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BookingTaskScheduler {

    @Autowired
    QueueService queueService;

    @Autowired
    BookingService bookingService;

    @Scheduled(fixedRate = 5000)
    public void processBookingRequest() {
        log.info("queue process Started .... " );
        Object itemFromQueue = queueService.getItemFromQueue();

        if(ObjectUtils.isNotEmpty(itemFromQueue)) {
            log.info("Getting from queue for proceessing  request: {}", (String) itemFromQueue );
            BookingQueueRequest bookingRequestFromQueue = CommonUtil.fromJson((String) itemFromQueue, BookingQueueRequest.class);

            bookingService.processBookingRequest(bookingRequestFromQueue);
        }
        log.info("Queue process end..");
    }
}
