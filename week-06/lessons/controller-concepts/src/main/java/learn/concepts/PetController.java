package learn.concepts;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")
// Add a PetController to the project.
// It demonstrates the use of one foundation path to handle repository-like CRUD operations.
public class PetController
{
    @GetMapping
    public void findAll() {
    }

    @GetMapping("/{petId}")
    public void findById(@PathVariable int petId) {
    }

    // Update the create and update handlers in PetController to include a Pet parameter.
    // Then annotate the parameter with @RequestBody.
    // The @RequestBody annotation tells Spring MVC to look in the request body for the content in the same way
    // @PathVariable tells Spring MVC to look in the request path.
    @PostMapping
    public void create(@RequestBody Pet pet) {
        System.out.println(pet);
    }

    @PutMapping("/{petId}")
    public void update(@PathVariable int petId, @RequestBody Pet pet) {
        System.out.println(pet);
    }


    @DeleteMapping("/{petId}")
    public void delete(@PathVariable int petId) {
    }
}
