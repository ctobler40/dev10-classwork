package TemporalTypes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Formatting
{
    public static void main(String[] args)
    {
        // Dates and times are incredibly useful, but before they can reach their full potential
        // we must address two shortcomings.

        // Our users don't want to view dates and times in ISO format.
        // Depending on where they live, our users have different date and time format expectations.
        // In the US, dates are usually formatted with slashes, month/day/year.
        // Times are typically displayed as a twelve-hour clock differentiated with AM or PM.

        //When a user enters a date or time, asking them to enter time units one at a time is a bad user experience.
        // They should be able to enter values in the format they understand.
        // We need a way to convert the string "8/12/2021" to a date and the string "10:15AM" to a time.

        //The java.time package has solutions to these problems.

        // A format pattern is a sequence of characters with special meaning that determine
        // both what is shown and where it is shown.
        // For example, the following patterns produce the year and month in various formats for the 8th of July, 2020.
        LocalDateTime now = LocalDateTime.of(2020, 7, 8, 17, 15);

        DateTimeFormatter fourDigitYear = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter twoDigitYear = DateTimeFormatter.ofPattern("yy");

        DateTimeFormatter oneDigitMonth = DateTimeFormatter.ofPattern("M");
        DateTimeFormatter twoDigitMonth = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter monthAbbr = DateTimeFormatter.ofPattern("MMM");
        DateTimeFormatter monthName = DateTimeFormatter.ofPattern("MMMM");

        System.out.println(now.format(fourDigitYear)); // 2020
        System.out.println(now.format(twoDigitYear));  // 20
        System.out.println(now.format(oneDigitMonth)); // 7
        System.out.println(now.format(twoDigitMonth)); // 07
        System.out.println(now.format(monthAbbr));     // Jul
        System.out.println(now.format(monthName));     // July

        // Days have many formatting options
        now = LocalDateTime.of(2020, 7, 8, 17, 15);

        DateTimeFormatter oneDigitDayOfMonth = DateTimeFormatter.ofPattern("d");
        DateTimeFormatter twoDigitDayOfMonth = DateTimeFormatter.ofPattern("dd");

        DateTimeFormatter dayOfYear = DateTimeFormatter.ofPattern("D");
        DateTimeFormatter dayOfWeekAbbr = DateTimeFormatter.ofPattern("eee");
        DateTimeFormatter dayOfWeekName = DateTimeFormatter.ofPattern("eeee");

        System.out.println(now.format(oneDigitDayOfMonth)); // 8
        System.out.println(now.format(twoDigitDayOfMonth)); // 08
        System.out.println(now.format(dayOfYear));          // 190
        System.out.println(now.format(dayOfWeekAbbr));      // Wed
        System.out.println(now.format(dayOfWeekName));      // Wednesday

        // If a temporal type includes time units, hour, minute, second, millisecond, and nanosecond can be formatted.
        now = LocalDateTime.of(2020, 7, 8, 17, 15);

        DateTimeFormatter twelveHourOneDigit = DateTimeFormatter.ofPattern("h");
        DateTimeFormatter twelveHourTwoDigit = DateTimeFormatter.ofPattern("hh");
        DateTimeFormatter twentyFourHour = DateTimeFormatter.ofPattern("kk");
        DateTimeFormatter twelveHourTwoDigitAMPM = DateTimeFormatter.ofPattern("hha");

        DateTimeFormatter minutesTwoDigits = DateTimeFormatter.ofPattern("mm");
        DateTimeFormatter secondsTwoDigits = DateTimeFormatter.ofPattern("ss");

        System.out.println(now.format(twelveHourOneDigit));     // 5
        System.out.println(now.format(twelveHourTwoDigit));     // 05
        System.out.println(now.format(twentyFourHour));         // 17
        System.out.println(now.format(twelveHourTwoDigitAMPM)); // 05PM
        System.out.println(now.format(minutesTwoDigits));       // 15
        System.out.println(now.format(secondsTwoDigits));       // 00

        // Symbol sequences can be combined to build complex patterns.
        // Non-symbol characters like /, -, and . are embedded directly in the output.
        // To embed literal symbols, surround them with a single quote.
        now = LocalDateTime.of(2020, 7, 8, 17, 15);

        DateTimeFormatter usDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter usTime = DateTimeFormatter.ofPattern("h:mma");
        DateTimeFormatter usDateTime = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mma");
        DateTimeFormatter monthAbbrDate = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

        DateTimeFormatter dotDate = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        DateTimeFormatter formalDate = DateTimeFormatter.ofPattern("d MMMM, yyyy");
        // The first two M's have meaning. The third M, surrounded
        // by single quotes is embedded directly in the output.
        DateTimeFormatter withLiteral = DateTimeFormatter.ofPattern("yy.MM'M'.dd");

        System.out.println(now.format(usDate));        // 07/08/2020
        System.out.println(now.format(usTime));        // 5:15PM
        System.out.println(now.format(usDateTime));    // 07/08/2020 5:15PM
        System.out.println(now.format(monthAbbrDate)); // 08-Jul-2020
        System.out.println(now.format(dotDate));       // 2020.07.08
        System.out.println(now.format(formalDate));    // 8 July, 2020
        System.out.println(now.format(withLiteral));   // 20.07M.08

        // Parsing
        // Once we have a formatter, we can use it to parse a String into a LocalTime, LocalDate, or LocalDateTime.
        // The string must match the format exactly or the parse method will throw an exception.
        Scanner console = new Scanner(System.in);

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("h:mma");

        System.out.print("Enter a date in month/day/year format:");
        LocalDate date = LocalDate.parse(console.nextLine(), dateFormat);
        System.out.println(date); // default format

        System.out.print("Enter a time in twelve hour:minute format:");
        LocalTime time = LocalTime.parse(console.nextLine(), timeFormat);
        System.out.println(time); // default format

        // To prevent an application crash, catch the unchecked DateTimeParseException.
        console = new Scanner(System.in);
        dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
        String value = console.nextLine();
        try
        {
            date = LocalDate.parse(value, dateFormat);
            System.out.println(date);
        }
        catch (DateTimeParseException ex)
        {
            System.out.printf("'%s' is not a valid date.%n", value);
        }
    }
}
