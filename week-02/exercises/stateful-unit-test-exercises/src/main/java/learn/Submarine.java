package learn;

/**
 * An underwater, submersible vehicle.
 * Includes two behaviors.
 * dive: go down a little deeper under water to a maximum depth
 * surface: come up a little shallower to a minimum depth of sea level
 * <p>
 * The submarine's current depth and pressure are available via getters.
 */
public class Submarine {

    private final double maxDepth;
    private double depthInMeters;

    public Submarine(double maxDepth)
    {
        this.maxDepth = maxDepth;
    }

    public double getDepthInMeters()
    {
        return depthInMeters;
    }

    /*
    1. Open `learn.Submarine` from src/main and `learn.SubmarineTest` from src/test.
    2. To understand `Submarine`, read its JavaDocs.
    3. Implement the numbered changes in each class. For each change, drive your development by writing tests.
    Your process should be:
    - Write some code. Confirm it's correct with a test.
    - Write some code. Confirm it's correct with a test.
    - Repeat until complete.
     */

    public void dive()
    {
        // 1. Each dive should increase the depth by 3 meters.
        depthInMeters += 3;
        // Depth cannot exceed maxDepth.
        if (depthInMeters > maxDepth)
            depthInMeters = maxDepth;
    }

    public void surface()
    {
        // 2. Each surface should decrease the depth by 5 meters.
        depthInMeters -= 5;
        // Minimum depth is 0.0 (sea level).
        if (depthInMeters < 0.0f)
            depthInMeters = 0.0f;
    }

    public double getPressureInAtmospheres()
    {
        // 3. At sea level, pressure is 1 atmosphere.
        // Pressure increases by 1 atmosphere for every 10 meters.
        return 1 + (depthInMeters / 10);
    }
}
