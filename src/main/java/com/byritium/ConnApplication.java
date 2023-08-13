package com.byritium;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.byritium.persistence.mapper")
public class ConnApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication app = new SpringApplication(ConnApplication.class);
        app.run(args);
        new BootNettyServer().startup();
    }

}
