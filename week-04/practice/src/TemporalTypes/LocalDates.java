package TemporalTypes;

import java.time.LocalDate;
import java.time.Month;

public class LocalDates
{
    public static void main(String[] args)
    {
        // LocalDate represents a year, month, and day without time.
        // It doesn't expose a public constructor. Instead, it uses static builder methods to create an instance.
        // Use the class name, LocalDate, to access builder methods.
        LocalDate juneFirst = LocalDate.of(2020, 6, 1);             // numeric year, month, day
        LocalDate julyTwelfth = LocalDate.of(2020, Month.JULY, 12); // month enum
        LocalDate today = LocalDate.now();                          // current year, month, day

        System.out.println(juneFirst);   // 2020-06-01
        System.out.println(julyTwelfth); // 2020-07-12
        System.out.println(today);       // 2020-05-26

        // LocalDate contains getter methods for time units like year, month, day of month, and day of year.
        // Some getters return numeric values and some return enums.
        System.out.println(julyTwelfth.getYear());       // 2020
        System.out.println(julyTwelfth.getMonth());      // JULY (Month enum)
        System.out.println(julyTwelfth.getMonthValue()); // 7
        System.out.println(julyTwelfth.getDayOfMonth()); // 12
        System.out.println(julyTwelfth.getDayOfYear());  // 194
        System.out.println(julyTwelfth.getDayOfWeek());  // SUNDAY (DayOfWeek enum)

        // Temporal types are immutable. They can't be changed once they're created.
        // Any method that returns the result of a calculation, returns a new instance instead of changing the existing instance.
        today = LocalDate.now();
        System.out.println(today);                    // 2020-05-26

        LocalDate weekFromToday = today.plusWeeks(1); // new instance
        System.out.println(today);                    // 2020-05-26 <-- `today` didn't change
        System.out.println(weekFromToday);            // 2020-06-02

        // LocalDate has several plus* and minus* methods to create a new LocalDate that's shifted
        // forward or backward in time, using years, months, weeks, or days.
        // All plus/minus methods can accept negative numbers, so plus doesn't necessarily mean in the future and
        // minus doesn't necessarily mean in the past.
        today = LocalDate.now();
        System.out.println(today);                  // 2020-05-26
        System.out.println(today.plusYears(12));    // 2032-05-26
        System.out.println(today.plusMonths(100));  // 2028-09-26
        System.out.println(today.plusWeeks(27));    // 2020-12-01
        System.out.println(today.plusDays(10000));  // 2047-10-12

        System.out.println(today.minusYears(12));   // 2008-05-26
        System.out.println(today.minusMonths(100)); // 2012-01-26
        System.out.println(today.minusWeeks(27));   // 2019-11-19
        System.out.println(today.minusDays(10000)); // 1993-01-08

        // Negative arguments
        System.out.println(today.plusDays(-100));   // 2020-02-16
        System.out.println(today.minusDays(-100));  // 2020-09-03

    }
}
