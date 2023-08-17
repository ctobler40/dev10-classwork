import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Exercise07Test
{
    Exercise07 instance = new Exercise07();

    @Test
    void reverseElements()
    {
        String[] values =
        {
            "lower and lower",
            "higher and higher",
            "potato",
            "beefstick",
            "potato stick",
            "beefcake"
        };
        String[] expected =
        {
            "beefcake",
            "potato stick",
            "beefstick",
            "potato",
            "higher and higher",
            "lower and lower"
        };
        String[] actual = instance.reverse(values);
        assertArrayEquals(expected, actual);
    }
}