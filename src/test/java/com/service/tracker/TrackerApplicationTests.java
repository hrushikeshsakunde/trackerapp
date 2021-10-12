package com.service.tracker;

import com.service.tracker.controller.SessionController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TrackerApplicationTests {

    @Autowired
    private SessionController controller;

    @Test
    void contextLoads() {

    }

}
