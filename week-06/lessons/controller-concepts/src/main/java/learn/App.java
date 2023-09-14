package learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 1
public class App {

    public static void main(String[] args)
    {
        // The beauty of a Spring Boot application is that it is launched with a main method, just like a console application.
        SpringApplication.run(App.class, args); // 2
    }
}
