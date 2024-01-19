package com.mashreq.confbooking.service.queue;

import org.springframework.stereotype.Service;

@Service
public interface QueueService {

    public void pushItemToQueue(Object item) ;
    public Object getItemFromQueue();
}
