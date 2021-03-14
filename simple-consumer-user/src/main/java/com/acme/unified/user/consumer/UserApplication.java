package com.acme.unified.user.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Class SpringBoot Application.
 *
 * @author Jim.DelloStritto
 * @project simple-consumer-user
 * @created Mar 13, 2021
 * @references
 * @credits
 */
@SpringBootApplication(scanBasePackages = {"com.acme.unified.user.consumer"})
public class UserApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserApplication.class);
    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        
        SpringApplication.run(UserApplication.class, args);
        LOGGER.info("Application Started: {} ", LocalDateTime.now());
        
    }

}