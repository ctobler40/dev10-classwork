package learn.concepts;

import learn.Pet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PetController
{
    // Just copy the code you missed when it is posted in Github...
    private ArrayList<Pet> pets = new ArrayList<>();
    private int nextId = 1;

    @GetMapping
    public List<Pet> findAll()
    {
        return pets;
    }

    @PostMapping("/pet")
    public ResponseEntity<Pet> add(@RequestBody Pet pet)
    {
        pet.setId(nextId++);
        pets.add(pet);
        return new ResponseEntity<Pet>(pet, HttpStatus.CREATED);
    }

    @PutMapping("/pet/{petId}")
    public ResponseEntity<Object> update(@PathVariable int petId, @RequestBody Pet pet)
    {
        for (Pet p : pets)
        {
            if (p.getId() == petId)
            {
                pets.set(pets.indexOf(p), pet);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
