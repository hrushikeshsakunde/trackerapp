package com.service.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrackerApplication {

    private static final Logger logger = LoggerFactory.getLogger(TrackerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TrackerApplication.class, args);
        logSystemInformation();
    }

    private static void logSystemInformation() {
        logger.info("SystemInfo - Java version: {}", Runtime.version());
        Runtime runtime = Runtime.getRuntime();
        logger.info("SystemInfo - Number of processors available to the JVM: {}", runtime.availableProcessors());
        logger.info("SystemInfo - The maximum amount of memory that the JVM will attempt to use (bytes): {}", runtime.maxMemory());
        logger.info("SystemInfo - The total amount of memory in the JVM (bytes): {}", runtime.totalMemory());
        logger.info("SystemInfo - The amount of free memory in the JVM (bytes): {}", runtime.freeMemory());
    }

}
