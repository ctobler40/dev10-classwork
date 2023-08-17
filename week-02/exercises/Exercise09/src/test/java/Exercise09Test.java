import org.example.Exercise09;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

class Exercise09Test
{
    Exercise09 instance = new Exercise09();

    // Suggested test additions:
    // shouldBeNullForNulLArg
    // shouldCapitalizeMultipleElements (several scenarios)
    // shouldIgnoreNullElements
    // shouldIgnoreEmptyElements

    @Test
    void shouldCapitalizeOneElement()
    {
        int[] values = {6, 5, 6, 5, 6, 5, 0, 0, 0, 6, 2, 3, 4, 2, 0, 8, 0, 1, 1, 2};
        int[] expected = {6, 5, 6, 5, 6, 5, 6, 2, 3, 4, 2, 8, 1, 1, 2};
        int[] actual = instance.removeZero(values);
        assertArrayEquals(expected, actual);
    }
}