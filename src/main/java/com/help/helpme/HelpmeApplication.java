package com.help.helpme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
public class HelpmeApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelpmeApplication.class, args);
    }

}

