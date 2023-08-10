import java.util.Scanner;

public class Exercise14
{
    /* SHORT SURVEY

    Write a program that asks a user four questions and prints the results:
    - What is your first name?
    - What is your last name?
    - How many towns/cities have you lived in?
    - How many musical instruments can you play?

    Store each answer in a variable with an appropriate type.
    Print the results after the user has answered all four questions.

    Use methods to break the program into reusable blocks of code.
     */
    public static void main (String[] args)
    {
        System.out.println(FirstName() + " " + LastName() + " has been to " + NumCities() + " cities and plays " + NumMusic() + " instruments.");
    }

    public static Scanner input = new Scanner(System.in);

    public static String FirstName()
    {
        System.out.println("First Name: ");
        return input.nextLine();
    }
    public static String LastName()
    {
        System.out.println("Last Name: ");
        return input.nextLine();
    }
    public static int NumCities()
    {
        System.out.println("Num Cities: ");
        return input.nextInt();
    }
    public static int NumMusic()
    {
        System.out.println("Num Musical Instruments: ");
        return input.nextInt();
    }
}
