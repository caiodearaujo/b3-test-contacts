package br.com.caiodearaujo.b3test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class B3TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(B3TestApplication.class, args);
    }

}
