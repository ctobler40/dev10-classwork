package learn;

public class Pet
{
    private int id;
    private String name;
    private String breed;
    private String color;

    public Pet()
    {

    }

    public Pet(int id, String name, String breed, String color)
    {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
