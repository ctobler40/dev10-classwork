public class Musician
{
    private String name;
    private int rating;

    /**
     * @param name   The name of the musician.
     * @param rating A number representing how much a musician is loved relative to other musicians.
     */
    public Musician()
    {
        this.name = "Billy Bo";
        this.rating = 8;
    }
    public Musician(String name, int rating)
    {
        this.name = name;
        this.rating = rating;
    }

    public String getName()
    {
        return name;
    }
    public int getRating()
    {
        return rating;
    }

    public void setName(String newName)
    {
        this.name = newName;
    }
    public void setRating(int newRating)
    {
        this.rating = newRating;
    }
}
