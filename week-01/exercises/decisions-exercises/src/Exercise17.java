import java.util.Scanner;

public class Exercise17
{
    public static void main(String[] args)
    {
        // SWITCH HOMEWORK
        Scanner console = new Scanner(System.in);

        System.out.print("Hours of homework: ");
        int hoursOfHomework = Integer.parseInt(console.nextLine());

        System.out.print("Day of the week [1-7]: ");
        int dayOfWeek = Integer.parseInt(console.nextLine());
        String setDay = null;

        // 1. Re-implement Exercise07 using a switch statement.
        // Days 6 and 7 represent Saturday and Sunday.
        // If it's the weekend and Abdi has less than 15 hours of homework, he skips homework for the day.
        // For all other days, or if he has more than 15 hours of homework, he does his homework.
        // ---
        // You may choose to track data -- maybe a boolean for homework yes/no -- instead of printing a message in
        // each case. That's a lot of repeated typing.
        // Then print the detailed message after the switch.
        switch (dayOfWeek)
        {
            case 1 -> setDay = "Monday";
            case 2 -> setDay = "Tuesday";
            case 3 -> setDay = "Wednesday";
            case 4 -> setDay = "Thursday";
            case 5 -> setDay = "Friday";
            case 6 -> setDay = "Saturday";
            case 7 -> setDay = "Sunday";
            default -> System.out.println("I don't recognize that day.");
        }

        if ((setDay.charAt(0) == 'S' || setDay.charAt(0) == 's') && hoursOfHomework < 15)
            System.out.print("I'm taking the day off!");
        else
            System.out.print("Time to do the homework...");
    }
}
