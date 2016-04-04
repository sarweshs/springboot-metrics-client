package com.alecu.tutorials.spring.boot.actuator.graphite.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service
public class SampleAsync {

    @Async
    public Future<Map<Object, Object>> getData() throws InterruptedException {
        Thread.sleep(5000);

        Map<Object, Object> result = new HashMap<Object, Object>();

        result.put("greeting", "Hi there");

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        result.put("Current Time", dateFormat.format(date));

        return new AsyncResult<Map<Object, Object>>(result);
    }

}
