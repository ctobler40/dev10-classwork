package TemporalTypes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ChronoUnits
{
    public static void main(String[] args)
    {
        // In the material above, we're working with one LocalDate or LocalDateTime at a time.
        // The java.time package has several classes to compare two date-times and calculate results.

        //Use ChronoUnit to calculate temporal unit differences (years, months, days, hours) between date-times.
        // ChronoUnit is an enum data type. Its values include DAYS, WEEKS, YEARS, and more (even DECADES and ERAS).
        LocalDateTime past = LocalDateTime.of(1972, 7, 25, 20, 13, 33);
        LocalDateTime future = LocalDateTime.of(2051, 4, 9, 3, 47, 5);
        System.out.println(ChronoUnit.YEARS.between(past, future));     // 78
        System.out.println(ChronoUnit.MONTHS.between(past, future));    // 944
        System.out.println(ChronoUnit.DAYS.between(past, future));      // 28746
        System.out.println(ChronoUnit.HOURS.between(past, future));     // 689911
        System.out.println(ChronoUnit.MINUTES.between(past, future));   // 41394693
        System.out.println(ChronoUnit.SECONDS.between(past, future));   // 2483681612
        System.out.println(ChronoUnit.CENTURIES.between(past, future)); // 0
        System.out.println(ChronoUnit.DECADES.between(past, future));   // 7
        System.out.println(ChronoUnit.HALF_DAYS.between(past, future)); // 57492

        // The between method works with any temporal data type.
        // For instance, comparing two LocalDates will work as long as we don't access HOURS, MINUTES, SECONDS, or HALF_DAYS.
        LocalDate pastDate = LocalDate.of(1972, 7, 25);
        LocalDate futureDate = LocalDate.of(2051, 4, 9);
        System.out.println(ChronoUnit.YEARS.between(pastDate, futureDate));        // 78
        System.out.println(ChronoUnit.MONTHS.between(pastDate, futureDate));       // 944
        System.out.println(ChronoUnit.DAYS.between(pastDate, futureDate));         // 28746
        // System.out.println(ChronoUnit.HOURS.between(pastDate, futureDate));     // doesn't work: no time element
        // System.out.println(ChronoUnit.MINUTES.between(pastDate, futureDate));   // doesn't work: no time element
        // System.out.println(ChronoUnit.SECONDS.between(pastDate, futureDate));   // doesn't work: no time element
        System.out.println(ChronoUnit.CENTURIES.between(pastDate, futureDate));    // 0
        System.out.println(ChronoUnit.DECADES.between(pastDate, futureDate));      // 7
        // System.out.println(ChronoUnit.HALF_DAYS.between(pastDate, futureDate)); // doesn't work: no time element

        // We can't mix different temporal types. Comparing a LocalDateTime with a LocalDate won't work.
        past = LocalDateTime.of(1972, 7, 25, 20, 13, 33);
        futureDate = LocalDate.of(2051, 4, 9);
        System.out.println(ChronoUnit.YEARS.between(past, futureDate)); // fails with:
        /*
        Exception in thread "main" java.time.DateTimeException: Unable to obtain LocalDateTime from TemporalAccessor: 2051-04-09 of type java.time.LocalDate
            at java.base/java.time.LocalDateTime.from(LocalDateTime.java:463)
            at java.base/java.time.LocalDateTime.until(LocalDateTime.java:1677)
            at java.base/java.time.temporal.ChronoUnit.between(ChronoUnit.java:272)
            at learn.App.main(App.java:13)
        Caused by: java.time.DateTimeException: Unable to obtain LocalTime from TemporalAccessor: 2051-04-09 of type java.time.LocalDate
            at java.base/java.time.LocalTime.from(LocalTime.java:433)
            at java.base/java.time.LocalDateTime.from(LocalDateTime.java:459)
            ... 3 more
        */
    }
}
