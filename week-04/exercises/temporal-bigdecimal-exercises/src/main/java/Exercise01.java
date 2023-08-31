import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Exercise01 {

    // LocalDate
    // ========================
    // Complete the numbered tasks below.
    // Open and execute the accompanying tests to confirm your solution is correct.
    public static void main(String[] args)
    {
        Exercise01 exercise = new Exercise01();
        System.out.println(exercise.getToday());
        System.out.println(exercise.getFirstFlightDate());
        System.out.println(exercise.makeFutureNullShiftThePast(LocalDate.now()));
        System.out.println(exercise.fiveFridaysFromDate(LocalDate.now()));
        System.out.println(exercise.getNextFridays(LocalDate.now(), 10));
        System.out.println(exercise.getDaysBetween(LocalDate.of(1941, 6, 22), LocalDate.now()) + " Days");
    }

    // 1. return today's date
    LocalDate getToday()
    {
        return LocalDate.now();
    }

    // 2. return December 17, 1903 as a LocalDate
    LocalDate getFirstFlightDate()
    {
        return LocalDate.of(1903, 12, 17);
    }

    // 3. if parameter is in the future, return null.
    // Otherwise, add 5 days to the parameter and return the result.
    LocalDate makeFutureNullShiftThePast(LocalDate date)
    {
        if (ChronoUnit.DAYS.between(LocalDate.now(), date) > 0)
            return null;
        return date.plusDays(5);
    }

    // 4. return the fifth Friday from the parameter date.
    // if the date is Friday, don't count it.
    LocalDate fiveFridaysFromDate(LocalDate date)
    {
        DateTimeFormatter dayOfWeekName = DateTimeFormatter.ofPattern("eeee");
        if (date.format(dayOfWeekName).equalsIgnoreCase("Friday"))
            return date.plusWeeks(5);
        switch (date.format(dayOfWeekName))
        {
            case "Saturday" -> {
                return date.plusDays(6).plusWeeks(4);
            }
            case "Sunday" -> {
                return date.plusDays(5).plusWeeks(4);
            }
            case "Monday" -> {
                return date.plusDays(4).plusWeeks(4);
            }
            case "Tuesday" -> {
                return date.plusDays(3).plusWeeks(4);
            }
            case "Wednesday" -> {
                return date.plusDays(2).plusWeeks(4);
            }
            case "Thursday" -> {
                return date.plusDays(1).plusWeeks(4);
            }
        }
        return null;
    }

    // 5. given a date and a count,
    // return a list of the next `fridayCount` Fridays after the date.
    // if the date is Friday, don't include it.
    List<LocalDate> getNextFridays(LocalDate date, int fridayCount)
    {
        List<LocalDate> fridays = new ArrayList<>();
        DateTimeFormatter dayOfWeekName = DateTimeFormatter.ofPattern("eeee");

        if (date.format(dayOfWeekName).equalsIgnoreCase("Friday"))
            for (int x = 0; x < fridayCount; x ++)
                fridays.add(date.plusWeeks(x));
        else
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
            for (int x = 0; x < fridayCount; x ++)
                fridays.add(date.plusWeeks(x));
        }

        return fridays;
    }

    // 6. return the absolute value of the days between two dates.
    // one may be before two, two may be before one, but neither will be null
    int getDaysBetween(LocalDate one, LocalDate two)
    {
        try
        {
            return (int)Math.abs(ChronoUnit.DAYS.between(one, two));
        }
        catch(Exception e)
        {
            System.out.println("Error: First Date must be before Second Date!");
        }
        return -1;
    }

}
