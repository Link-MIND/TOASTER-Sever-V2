package com.app.toaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = "com.app.toaster")
public class ToasterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ToasterApplication.class, args);
    }
}