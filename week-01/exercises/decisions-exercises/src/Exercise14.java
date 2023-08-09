import java.util.Scanner;

public class Exercise14
{
    public static void main(String[] args)
    {
        // DAYS OF THE WEEK
        Scanner console = new Scanner(System.in);

        System.out.println("1. Monday");
        System.out.println("2. Tuesday");
        System.out.println("3. Wednesday");
        System.out.println("4. Thursday");
        System.out.println("5. Friday");
        System.out.println("6. Saturday");
        System.out.println("7. Sunday");
        System.out.print("Select a day [1-7]: ");

        int day = Integer.parseInt(console.nextLine());

        // 1. Add cases for days 2-7. Print a tired cliché for each day.
        switch (day)
        {
            case 1 -> System.out.println("I refuse to say \"a case of the Mondays\".");
            case 2 -> System.out.println("It's chewsday innit?");
            case 3 -> System.out.println("HUMP DAY!");
            case 4 -> System.out.println("Is the week over yet?");
            case 5 -> System.out.println("TGIF!");
            case 6 -> System.out.println("It's a tomorrow issue...");
            case 7 -> System.out.println("NFL Sunday!");
            default -> System.out.println("I don't recognize that day.");
        }
    }
}
