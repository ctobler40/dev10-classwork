package learn.concepts;

import learn.SheepValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HodgepodgeController
{
    public static int sheepCount = 0;
    public SheepValue sheepValue= new SheepValue();
    public static ArrayList<String> toDos = new ArrayList<>();

    @GetMapping("/")
    public String home()
    {
        return "Hello world.<br>Beautiful Day!";
    }

    // Return your name as a string.
    @GetMapping("/name")
    public String name()
    {
        return "CJ is my name!";
    }

    // Return the current date and time.
    @GetMapping("/current/time")
    public LocalDate currentTime()
    {
        return LocalDate.now();
    }

    // Return a greeting that's customized for a name passed in the URL.
    // Example: "Hello, Dr. Itch!"
    @GetMapping("/greet/{name}")
    public String greeting(@PathVariable String name)
    {
        return "Good day to you, " + name + "!";
    }

    // Create a static integer named sheepCount in the controller class.
    // Each time the handler method is activated, increment its value by one.
    @PutMapping("/sheep")
    public void countingSheep()
    {
        sheepCount += 1;
    }

    // Return the number of sheep -- sheepCount.
    @GetMapping("/sheep")
    public int howManySheep()
    {
        return sheepCount;
    }

    // Use sheepCount.
    // Each time the handler method is activated, increase its value by the amount provided.
    @PutMapping("/sheep/{amount}")
    public void countingSheep(@PathVariable int amount)
    {
        sheepCount += amount;
    }

    // Each time the handler method is activated, increase sheepCount by the amount in SheepValue.getAmount.
    @PostMapping("/sheep")
    public void countingSheepValue(@RequestBody SheepValue value)
    {
        sheepCount += sheepValue.getAmount();
    }

    // Each time the handler method is activated, decrement sheepCount by one.
    @DeleteMapping("/sheep")
    public void lostSheep()
    {
        sheepCount -= 1;
    }

    // Create a static ArrayList<String> todos in the controller.
    // Populate the list with one or two To-Dos for today.
    @GetMapping("/todo")
    public List<String> viewToDos()
    {
        // For now, we will just add them here
        toDos.add("Stuff 1");
        toDos.add("Stuff 2");
        toDos.add("Stuff 3");
        return toDos;
    }

    // Append a list of To-Dos to todos.
    @PutMapping("/todo")
    public void bulkUpToDoList(@RequestBody List<String> items)
    {
        toDos.addAll(items);
    }

    // Add one To-Do to todos.
    @PutMapping("todo/{item}")
    public void addOneToDo(@PathVariable String item)
    {
        toDos.add(item);
    }

    // TODO: Remove the To-Do at the specified index.
    // If it doesn't exist, return 404 Not Found. Otherwise, return 200 OK.
    @DeleteMapping("todo/{item}")
    public void deleteToDo(@PathVariable int index)
    {

    }

    // TODO: Replace the current todos with the list provided.
    @PostMapping("/todo")
    public void replaceCurrentToDos(@RequestBody List<String> items)
    {

    }
}
