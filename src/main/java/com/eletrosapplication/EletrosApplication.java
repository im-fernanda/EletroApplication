package com.eletrosapplication;

import com.eletrosapplication.domain.Eletro;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EletrosApplication {

    public static void main(String[] args) {
        SpringApplication.run(EletrosApplication.class, args);
    }

    Eletro e = new Eletro();
}
