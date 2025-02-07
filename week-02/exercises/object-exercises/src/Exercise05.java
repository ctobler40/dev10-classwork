import java.util.Scanner;

public class Exercise05
{
    public static void main(String[] args)
    {
        Scanner console = new Scanner(System.in);

        Musician[] musicians = new Musician[5];
        musicians[0] = new Musician("Frank Ocean", 10);

        // 1. Use a loop to populate the `musicians` array with your top 5 favorite musicians.
        // (Replace Frank Ocean.)
        // Create musicians from user input. (See Exercise04.)
        for (int i = 0; i < musicians.length; i++)
        {
            musicians[i] = new Musician();
            System.out.print("Musician #" + (i + 1) + " name: ");
            musicians[i].setName(console.nextLine());
        }

        // 2. Use a second loop to print details about each musician.
        for (int i = 0; i < musicians.length; i++)
        {
            Musician musician = musicians[i];
            System.out.println((i + 1) + ". " + musician.getName() + " - " + musician.getRating());
        }
    }
}
