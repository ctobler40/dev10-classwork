import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Exercise05 {

    // THE GODMOTHER
    // ========================
    // Complete the numbered tasks below.
    // Open and execute the accompanying tests to confirm your solution is correct.
    public static void main(String[] args)
    {
        Exercise05 exercise = new Exercise05();
        System.out.println("Today...\n" +
                           "You will make $" + exercise.calculateGiftsTilEndOfYear(LocalDate.now()) + " from Grandma the rest of this year!");
        System.out.println("Starting 6 Months ago...\n" +
                "You will make $" + exercise.calculateGiftsTilEndOfYear(LocalDate.now().minusMonths(6)) + " from Grandma the rest of this year!");
        System.out.println("Starting in 2 Months...\n" +
                "You will make $" + exercise.calculateGiftsTilEndOfYear(LocalDate.now().plusMonths(2)) + " from Grandma the rest of this year!");

        System.out.println("\nFeeling Quirky now...");
        System.out.println("Today...\n" +
                "You will make $" + exercise.calculateQuirkyGiftsTilEndOfYear(LocalDate.now()) + " from Grandma the rest of this year!");
        System.out.println("Starting 6 Months ago...\n" +
                "You will make $" + exercise.calculateQuirkyGiftsTilEndOfYear(LocalDate.now().minusMonths(6)) + " from Grandma the rest of this year!");
        System.out.println("Starting in 2 Months...\n" +
                "You will make $" + exercise.calculateQuirkyGiftsTilEndOfYear(LocalDate.now().plusMonths(2)) + " from Grandma the rest of this year!");

        System.out.println("\nIf she was feeling quirky 5 years ago...");
        System.out.println("Today...\n" +
                "You will make $" + exercise.calculateQuirkyGiftsTilEndOfYear(LocalDate.now().minusYears(5)) + " from Grandma the rest of this year!");
        System.out.println("Starting 6 Months ago...\n" +
                "You will make $" + exercise.calculateQuirkyGiftsTilEndOfYear(LocalDate.now().minusYears(5).minusMonths(6)) + " from Grandma the rest of this year!");
        System.out.println("Starting in 2 Months...\n" +
                "You will make $" + exercise.calculateQuirkyGiftsTilEndOfYear(LocalDate.now().minusYears(5).plusMonths(2)) + " from Grandma the rest of this year!");
    }

    // 1. Your Godmother gives you $10 every other Friday throughout the year.
    // Payments start on the first Friday of the year.
    // Given a date, calculate payments expected from that date until the end of the year.
    BigDecimal calculateGiftsTilEndOfYear(LocalDate date)
    {
        BigDecimal totalMoneySum = BigDecimal.valueOf(0);
        DateTimeFormatter dayOfWeekName = DateTimeFormatter.ofPattern("eeee");
        if (!date.format(dayOfWeekName).equalsIgnoreCase("Friday"))
        {
            switch (date.format(dayOfWeekName))
            {
                case "Saturday" -> date = date.plusDays(6);
                case "Sunday" -> date = date.plusDays(5);
                case "Monday" -> date = date.plusDays(4);
                case "Tuesday" -> date = date.plusDays(3);
                case "Wednesday" -> date = date.plusDays(2);
                case "Thursday" -> date = date.plusDays(1);
            }
        }
        int currentYear = date.getYear();

        // Key: Payments start on the first Friday of the year.
        if ((int)(date.getDayOfYear() / 7) % 2 == 1)
            date = date.plusWeeks(1);

        while (currentYear == date.getYear())
        {
            totalMoneySum = totalMoneySum.add(BigDecimal.valueOf(10));
            date = date.plusWeeks(2);
        }
        return totalMoneySum;
    }

    // 2. Your Godmother is getting quirky. She adjusted her payment schedule.
    // She still pays every other Friday throughout the year, starting on the first Friday of the year.
    // But now, she pays a number of dollars equal to the day of the month.
    // Examples
    // Jan 31 == $31
    // Mar 1 == $1
    // July 12 == $12
    // Given a date, calculate payments expected from that date until the end of the year.
    BigDecimal calculateQuirkyGiftsTilEndOfYear(LocalDate date)
    {
        BigDecimal totalMoneySum = BigDecimal.valueOf(0);
        DateTimeFormatter dayOfWeekName = DateTimeFormatter.ofPattern("eeee");
        if (!date.format(dayOfWeekName).equalsIgnoreCase("Friday"))
        {
            switch (date.format(dayOfWeekName))
            {
                case "Saturday" -> date = date.plusDays(6);
                case "Sunday" -> date = date.plusDays(5);
                case "Monday" -> date = date.plusDays(4);
                case "Tuesday" -> date = date.plusDays(3);
                case "Wednesday" -> date = date.plusDays(2);
                case "Thursday" -> date = date.plusDays(1);
            }
        }
        int currentYear = date.getYear();
        while (currentYear == date.getYear())
        {
            totalMoneySum = totalMoneySum.add(BigDecimal.valueOf(date.getDayOfMonth()));
            date = date.plusWeeks(2);
        }
        return totalMoneySum;
    }

}
