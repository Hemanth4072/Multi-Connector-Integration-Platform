package com.hemanth.connectorplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class ConnectorPlatformApplication {
    public static void main(String[] args) { SpringApplication.run(ConnectorPlatformApplication.class, args); }
}
