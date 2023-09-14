package learn.solarfarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 2. Remove any manual dependency injection from your App class (if using M03 Solar Farm JDBC).
// 3. Annotate your App class with @SpringBootApplication.
@SpringBootApplication
public class App
{
    // 4. Add SpringApplication.run(App.class, args); to your main method.
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
