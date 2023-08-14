/*
# Exercise13

1. Add a class to the project named `Hero`. It represents a super hero.
2. Add two fields:
    - `String name`
    - `Power[] powers`
3. Add a constructor that accepts a `String` and `Power[]` and sets the appropriate field.
4. Add getters for both the `name` and `powers` fields.
 */

import java.util.Arrays;

public class Hero
{
    private String name;
    private Power[] powers;
    /*
    Params
    name: Name of Hero
    powers: Set of powers from Hero
     */

    public Hero(String name, Power[] powers)
    {
        this.name = name;
        this.powers = powers;
    }

    public String GetName()
    {
        return this.name;
    }

    public Power[] GetPowers()
    {
        return this.powers;
    }

    public String ToLine()
    {
        String fullString = this.name + ": ";
        for (int i = 0; i < this.powers.length; i++)
        {
            Power power = this.powers[i];
            fullString = fullString + (i != 0 ? ", " : "") + power.GetName();
        }
        return fullString;
    }
}
