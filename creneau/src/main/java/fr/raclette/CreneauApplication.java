package fr.raclette;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CreneauApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreneauApplication.class, args);
    }
}
