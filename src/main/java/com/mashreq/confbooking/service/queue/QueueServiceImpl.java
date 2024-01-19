package com.mashreq.confbooking.service.queue;

import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class QueueServiceImpl implements QueueService {
    private Queue<Object> queue = new ConcurrentLinkedQueue<>();

    @Override
    public void pushItemToQueue(Object item) {
        queue.add(item);
    }

    @Override
    public Object getItemFromQueue() {
        return queue.poll();
    }
}
