import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Exercise08Test
{
    StringMethods instance = new StringMethods();

    @Test
    void findWeekdaysAtStart()
    {
        String[] values =
        {
            "Mondays aren't fun",
            "Chewsday",
            "Wedsday is how it's spelled right?",
            "Thurday is a good day",
            "asdfasdf",
            "Saturday = yay"
        };
        boolean[] expected =
        {
            true,
            false,
            true,
            false,
            false,
            true
        };
        for (int x = 0; x < values.length; x ++)
            assertEquals(expected[x], instance.startsWithDayOfWeek(values[x]));
    }

    @Test
    void findWeekdaysAnywhere()
    {
        String[] values =
        {
            "aren't Mondays fun",
            "It's Chewsday",
            "spelled Wedsday is how it's right?",
            "is Thurday a good day?",
            "asdfasdfasdf",
            "yay = Saturday"
        };
        boolean[] expected =
        {
            true,
            false,
            true,
            false,
            false,
            true
        };
        for (int x = 0; x < values.length; x ++)
            assertEquals(expected[x], instance.containsDayOfWeek(values[x]));
    }

    @Test
    void findXSplitStrings()
    {
        String[] values =
        {
            "xox",
            "onexexx",
            "xerrex",
            "xuxxuxxux"
        };
        String[] expected =
        {
            "xx",
            "onexxx",
            "xerrex",
            "xxxxxx"
        };
        for (int x = 0; x < values.length; x ++)
            assertEquals(expected[x], instance.removeVowelFromBetweenX(values[x]));
    }
}