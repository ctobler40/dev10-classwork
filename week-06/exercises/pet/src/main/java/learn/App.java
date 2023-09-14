package learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 1
public class App {

    public static void main(String[] args)
    {
        // Create a main method and configure it to launch as a Spring Boot application.
        SpringApplication.run(App.class, args); // 2
    }
}
