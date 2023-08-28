package TemporalTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class LocalDateTimes
{
    public static void main(String[] args)
    {
        // The LocalDateTime class combines features from LocalDate and adds a time element.
        // It represents a moment in time on a specific date. Time includes hours, minutes, seconds, and nanoseconds.
        LocalDateTime elevenFifteenAM = LocalDateTime.of(2025, 1, 1, 11, 15); // AM
        LocalDateTime elevenFifteenPM = LocalDateTime.of(2025, 1, 1, 23, 15); // PM
        LocalDateTime now = LocalDateTime.now();
        // PM values are expressed using 24-hour values (also called Military Time): add twelve to all PM hours,
        // e.g., 8:00PM equals 20:00, 09:27PM equals 21:27, Midnight equals 00:00.

        // By default, a LocalDateTime's string representation is in ISO format,
        // year-month-day'T'hour:minute:second.nanosecond, "yyyy-MM-dd'T'HH:mm:ss.n".
        // It contains getter methods for hour, minute, second, and nanoseconds.
        System.out.println(elevenFifteenAM); // 2025-01-01T11:15
        System.out.println(elevenFifteenPM); // 2025-01-01T23:15
        System.out.println(now);             // 2023-06-20T09:08:14.135824800

        System.out.println(elevenFifteenPM.getHour());   // 23
        System.out.println(elevenFifteenPM.getMinute()); // 15
        System.out.println(elevenFifteenPM.getSecond()); // 0
        System.out.println(elevenFifteenPM.getNano());   // 0

        System.out.println(now.getHour());   // 9
        System.out.println(now.getMinute()); // 8
        System.out.println(now.getSecond()); // 14
        System.out.println(now.getNano());   // 135824800

        // Time units can be added using plus* methods and subtracted using minus* methods.
        // Plus and minus methods create a new LocalDateTime. They don't alter the existing time.
        now = LocalDateTime.now();

        System.out.println(now);                   // 2023-06-20T09:22:30.906073600
        System.out.println(now.plusHours(2));      // 2023-06-20T11:22:30.906073600
        System.out.println(now.plusMinutes(64));   // 2023-06-20T10:26:30.906073600
        System.out.println(now.plusSeconds(35));   // 2023-06-20T09:23:05.906073600
        System.out.println(now.plusNanos(100123)); // 2023-06-20T09:22:30.906173723
        System.out.println(now);                   // 2023-06-20T09:22:30.906073600
    }
}
