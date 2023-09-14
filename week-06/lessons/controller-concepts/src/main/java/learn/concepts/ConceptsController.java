package learn.concepts;

import org.springframework.web.bind.annotation.*;

@RestController                   // 1
public class ConceptsController
{
    @GetMapping("/")              // 2
    public String helloWorld()
    {
        return "Hello world.";    // 3
    }

    // Spring provides four specialized annotations that map the GET, POST, PUT, and DELETE HTTP methods.
    @GetMapping("/get")
    public void doGet() {
    }

    @PostMapping("/post")
    public void doPost() {
    }

    @PutMapping("/put")
    public void doPut() {
    }

    @DeleteMapping("/delete")
    public void doDelete() {
    }

    // URL-encoded Request Body
    // JSON is an extremely convenient format. It should be our go-to format for HTTP services.
    // However, it's possible to convert other formats to method parameters.
    // HTML forms submit their data in a URL-encoded format. We can use a URL-encoded request body.
    @PostMapping("/urlencoded")
    public void readFromBody(String name, int age, boolean likesCookies) {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Likes Cookies?: " + likesCookies);
    }
}