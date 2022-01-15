package com.antherd.pubyun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author geyiwei
 */
@EnableScheduling
@SpringBootApplication
public class PubyunApplication {

    public static void main(String[] args) {
        SpringApplication.run(PubyunApplication.class, args);
    }
}
