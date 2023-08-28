import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Exercise02 {

    // LocalDateTime
    // ========================
    // Complete the numbered tasks below.
    // Open and execute the accompanying tests to confirm your solution is correct.
    public static void main(String[] args)
    {
        Exercise02 exercise = new Exercise02();
        System.out.println(exercise.getNow());
        System.out.println(exercise.getTeaTime());
        System.out.println(exercise.add12Hours(LocalDateTime.now()));
        System.out.println(exercise.getQuarterHourAppointments(LocalDateTime.now()));
    }

    // 1. return the current date and time as a LocalDateTime
    LocalDateTime getNow()
    {
        return LocalDateTime.now();
    }

    // 2. return the current date and 4PM (tea time!) as a LocalDateTime.
    LocalDateTime getTeaTime()
    {
        return LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth(), 16, 0);
    }

    // 3. add 12 hours to the LocalDateTime parameter and return the value
    LocalDateTime add12Hours(LocalDateTime dateTime)
    {
        return dateTime.plusHours(12);
    }

    // 4. given a LocalDateTime parameter, return a list of the next 4
    // quarter-hour appointments available after the datetime.
    // appointment times should not include seconds even if the time parameter does.
    // ignore seconds and nanoseconds.
    // Examples:
    // time == 16:07:32
    // appointments == 16:15, 16:30, 16:45, 17:00
    //
    // time == 03:00:01
    // appointments == 03:00, 03:15, 03:30, 03:45
    //
    // time == 04:30:00
    // appointments == 04:30, 04:45, 05:00, 05:15
    List<LocalDateTime> getQuarterHourAppointments(LocalDateTime dateTime)
    {
        List<LocalDateTime> quarterlyHour = new ArrayList<>();
        // Set the new date time to whatever the next quarterly minute is
        if (dateTime.getMinute() % 15 != 0)
            dateTime = dateTime.plusMinutes(15 - dateTime.getMinute() % 15);
        // Set the seconds to 0
        dateTime = dateTime.minusSeconds(dateTime.getSecond());
        // Now place the next 4 times of 15 minutes into the array
        for (int i = 0; i < 4; i++)
        {
            quarterlyHour.add(dateTime);
            dateTime = dateTime.plusMinutes(15);
        }
        return quarterlyHour;
    }
}
