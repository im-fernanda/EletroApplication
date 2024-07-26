package com.eletrosapplication;

import com.eletrosapplication.domain.Eletro;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.eletrosapplication.repository")
@EntityScan(basePackages = "com.eletrosapplication.domain")
public class EletrosApplication {

    public static void main(String[] args) {
        SpringApplication.run(EletrosApplication.class, args);
    }

    Eletro e = new Eletro();
}
