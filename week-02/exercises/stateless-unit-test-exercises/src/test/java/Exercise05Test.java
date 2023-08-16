import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Exercise05Test
{
    @Test
    void calculateTotalCost()
    {
        Exercise05 instance = new Exercise05();

        // doubles are notoriously hard to test because they use floating-point rounding.
        // The third argument in `assertEquals` is a delta. It represents the margin of error for equality.
        // As long as the expected and actual differ by less than the delta, the test passes.
        assertEquals(true, instance.isWithinFiveOfAHundred(5));
        assertEquals(true, instance.isWithinFiveOfAHundred(102));
        assertEquals(false, instance.isWithinFiveOfAHundred(-6));
        assertEquals(false, instance.isWithinFiveOfAHundred(94));
        assertEquals(true, instance.isWithinFiveOfAHundred(-104));
        assertEquals(false, instance.isWithinFiveOfAHundred(117));
    }
}