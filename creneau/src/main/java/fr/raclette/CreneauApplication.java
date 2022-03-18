package fr.raclette;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class CreneauApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreneauApplication.class, args);
    }

}
