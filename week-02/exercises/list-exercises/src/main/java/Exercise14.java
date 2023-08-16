import learn.VideoGame;
import java.util.ArrayList;

/*
# Exercise14

1. Create a class to represent something that interests you. If you're a collector, maybe the thing you collect?
Ideas: garden plants, video game characters, novelists, Studio Ghibli movies, the tallest buildings in the world...
Add appropriate fields, getters and setters, and constructors.
2. Create a second class, `Exercise14`, and add a `main` method.
3. Create an `ArrayList<T>` to hold instances of your new class.
4. Instantiate several objects and add them to the list.
5. Loop through the instances and print their values.
 */

public class Exercise14
{
    public static void main(String[] args)
    {
        ArrayList<VideoGame> myGames = new ArrayList<VideoGame>();
        myGames.add(new VideoGame("BioShock Infinite", 1, 1, 76));
        myGames.add(new VideoGame("Apex Legends", 1, 3, 2000));
        myGames.add(new VideoGame("Assassin's Creed: Black Flag", 1, 1, 155));
        myGames.add(new VideoGame("Persona 5", 1, 1, 206));
        for (int i = 0; i < myGames.size(); i++)
        {
            VideoGame videoGame =  myGames.get(i);
            System.out.println("Game: " + videoGame.getName() + " is for " + videoGame.getMinPlayers() + " to " + videoGame.getMaxPlayers() + " players and has " + videoGame.getHoursPlayed() + " hours played.");
        }
    }
}
