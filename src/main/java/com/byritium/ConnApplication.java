package com.byritium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConnApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication app = new SpringApplication(ConnApplication.class);
        app.run(args);
        new BootNettyServer().startup();
    }

}
