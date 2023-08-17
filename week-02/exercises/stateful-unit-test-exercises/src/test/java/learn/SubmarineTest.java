package learn;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubmarineTest
{
    Submarine submarine = new Submarine(100.0);

    @Test
    void shouldHaveCorrectDepthAfter3Dives()
    {
        submarine.dive();
        submarine.dive();
        submarine.dive();
        assertEquals(9.0, submarine.getDepthInMeters(), 0.001);
    }

    @Test
    void shouldHaveCorrectPressureAfter3Dives()
    {
        submarine.dive();
        submarine.dive();
        submarine.dive();
        // 1.0 at sea level plus 1.0 * 0.9
        assertEquals(1.9, submarine.getPressureInAtmospheres(), 0.001);
    }

    // 1. Create one or more tests to confirm `dive` is working properly.
    @Test
    void shouldHaveCorrectDepthAfter15Dives()
    {
        for (int x = 0; x < 15; x ++)
            submarine.dive();
        assertEquals(45.0, submarine.getDepthInMeters(), 0.001);
    }

    // 2. Create a test to assert the submarine can't go deeper than the max depth.
    // (Be sure to use more than one max depth.)
    @Test
    void shouldHaveCorrectDepthAfterMaxDives()
    {
        for (int x = 0; x < 50; x ++)
            submarine.dive();
        assertEquals(100.0, submarine.getDepthInMeters(), 0.001);
    }

    // 3. Create one or more tests to confirm `surface` is working properly.
    @Test
    void shouldHaveCorrectSurface()
    {
        for (int x = 0; x < 15; x ++)
            submarine.dive();
        for (int x = 0; x < 5; x ++)
            submarine.surface();
        assertEquals(20.0, submarine.getDepthInMeters(), 0.001);
    }
    // 4. Create a test to assert the submarine can't go above sea level.
    // (Depth can never be negative.)
    @Test
    void shouldHaveCorrectMaxSurface()
    {
        for (int x = 0; x < 15; x ++)
            submarine.dive();
        for (int x = 0; x < 15; x ++)
            submarine.surface();
        assertEquals(0.0, submarine.getDepthInMeters(), 0.001);
    }

    // 5. Create one or more tests to confirm `getPressureInAtmospheres` is working properly.
    @Test
    void shouldHaveCorrectPressureAfterVariousDives()
    {
        for (int x = 0; x < 16; x ++)
            submarine.dive();
        for (int x = 0; x < 5; x ++)
            submarine.surface();
        // 1.0 at sea level plus 1.0 * 2.3
        assertEquals(3.3, submarine.getPressureInAtmospheres(), 0.001);
    }
}