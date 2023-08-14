import java.util.Scanner;

public class Exercise10
{
    public static void main(String[] args)
    {
        // BALLOON GAME
        Scanner console = new Scanner(System.in);

        // 1. Instantiate three balloons of different colors.
        Balloon balloon1 = new Balloon("red");
        Balloon balloon2 = new Balloon("blue");
        Balloon balloon3 = new Balloon("yellow");

        do
        {
            System.out.println("Inflate? [y/n]: ");
            if (console.nextLine().equalsIgnoreCase("y"))
            {
                // 2. If the user confirms an inflate, inflate each balloon.
                balloon1.inflate();
                System.out.println(balloon1.getColor() + " Balloon: " + balloon1.getPsi());
                balloon2.inflate();
                System.out.println(balloon2.getColor() + " Balloon: " + balloon2.getPsi());
                balloon3.inflate();
                System.out.println(balloon3.getColor() + " Balloon: " + balloon3.getPsi());
            }
        }   // 3. When one or more balloons explode, stop the loop.
        while (!balloon1.isExploded() && !balloon2.isExploded() && !balloon3.isExploded());

        // 4. Print the color of the winners (balloons that exploded).
        System.out.println("Winner!");
        if (balloon1.isExploded())
            System.out.println(balloon1.getColor());
        else if (balloon2.isExploded())
            System.out.println(balloon2.getColor());
        else
            System.out.println(balloon3.getColor());
    }
}
