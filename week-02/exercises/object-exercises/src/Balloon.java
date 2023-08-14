public class Balloon
{
    /*
    # Exercise06

    1. Add a class to this project named `Balloon`.
    2. Add two private fields:
        - `String color`: balloon's color
        - `double psi`: balloon's pressure in lbs/sq inch
    3. Add a constructor that accepts a `String` color and sets the field. Do not set psi.
    4. Add getters for both color and psi. (psi will always have its default value 0.0)
     */

    private String color;
    private double psi;

    /**
     * @param color balloon's color
     * @param psi balloon's pressure in lbs/sq inch
     */
    public Balloon(String color)
    {
        this.color = color;
        this.psi = 0.0;
    }

    public String getColor()
    {
        return color;
    }
    public double getPsi()
    {
        // Exercise 9: Edit the getPsi method.
        return isExploded() ? Double.POSITIVE_INFINITY : psi;
    }

    public void setColor(String newColor)
    {
        this.color = newColor;
    }
    public void setPsi(double newPsi)
    {
        this.psi = newPsi;
    }

    // Exercise 8
    public void inflate()
    {
        this.psi = this.psi + Math.random() * 5.0;
    }

    // Exercise 9
    public boolean isExploded()
    {
        return this.psi > 16.0;
    }
}
