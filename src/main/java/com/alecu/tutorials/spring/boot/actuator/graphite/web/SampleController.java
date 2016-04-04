package com.alecu.tutorials.spring.boot.actuator.graphite.web;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class SampleController {

    @Autowired SampleAsync sampleAsync;

    @RequestMapping("/greeting")
    public ResponseEntity<Map<Object, Object>> getTime() throws InterruptedException, ExecutionException {
        Future<Map<Object, Object>> src = sampleAsync.getData();

        while (!src.isDone()) {
            Thread.sleep(10);
        }

        return new ResponseEntity<Map<Object, Object>>(src.get(), HttpStatus.OK);
    }

}
