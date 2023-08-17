import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exercise06Test
{
    Exercise06 instance = new Exercise06();

    // Suggested test additions:
    // shouldBeNullForNulLArg
    // shouldCapitalizeMultipleElements (several scenarios)
    // shouldIgnoreNullElements
    // shouldIgnoreEmptyElements

    @Test
    void shouldCapitalizeOneElement()
    {
        String[] values =
        {
            "lower and lower",
            "higher and higher",
            "potato"
        };
        String[] expected =
        {
            "Lower And Lower",
            "Higher And Higher",
            "Potato"
        };
        String[] actual = instance.capitalizeAll(values);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldBeEmptyForEmptyArg()
    {
        assertArrayEquals(new String[0], instance.capitalizeAll(new String[0]));
    }
}