/*
# Exercise08
 */

// 1. Add a new class to the project named `StringMethods`.
public class StringMethods
{
    /*
    2. Add a method
    ```
    Name: startsWithDayOfWeek
    Inputs: String
    Output: boolean
    Description: return true if the parameters starts with a day of week abbreviation:
    Mon, Tues, Weds, Thurs, Fri, Sat, Sun
    or false if it doesn't
    ```
     */
    public boolean startsWithDayOfWeek(String day)
    {
        // 3. Create tests for startsWithDayOfWeek and confirm the method is correct.
        if (day.charAt(0) == 'M' && day.charAt(1) == 'o' && day.charAt(2) == 'n')
            return true;
        else  if (day.charAt(0) == 'T' && day.charAt(1) == 'u' && day.charAt(2) == 'e' && day.charAt(3) == 's')
            return true;
        else  if (day.charAt(0) == 'W' && day.charAt(1) == 'e' && day.charAt(2) == 'd' && day.charAt(3) == 's')
            return true;
        else  if (day.charAt(0) == 'T' && day.charAt(1) == 'h' && day.charAt(2) == 'u' && day.charAt(3) == 'r' && day.charAt(4) == 's')
            return true;
        else  if (day.charAt(0) == 'F' && day.charAt(1) == 'R' && day.charAt(2) == 'i')
            return true;
        else  if (day.charAt(0) == 'S' && day.charAt(1) == 'a' && day.charAt(2) == 't')
            return true;
        else  if (day.charAt(0) == 'S' && day.charAt(1) == 'u' && day.charAt(2) == 'n')
            return true;
        return false;
    }

    /*
    4. Add a method
    ```
    Name: containsDayOfWeek
    Inputs: String
    Output: boolean
    Description: return true if a day of week abbreviation occurs anywhere in the string
    or false if it doesn't
    ```
     */
    public boolean containsDayOfWeek(String day)
    {
        // 5. Create tests for containsDayOfWeek and confirm the method is correct.
        if (day.length() >= 3)
        {
            for (int x = 0; x < day.length(); x ++)
            {
                if (day.charAt(x) == 'M' && day.charAt(x + 1) == 'o' && day.charAt(x + 2) == 'n')
                    return true;
                else  if (day.charAt(x) == 'T' && day.charAt(x + 1) == 'u' && day.charAt(x + 2) == 'e' && day.charAt(x + 3) == 's')
                    return true;
                else  if (day.charAt(x) == 'W' && day.charAt(x + 1) == 'e' && day.charAt(x + 2) == 'd' && day.charAt(x + 3) == 's')
                    return true;
                else  if (day.charAt(x) == 'T' && day.charAt(x + 1) == 'h' && day.charAt(x + 2) == 'u' && day.charAt(x + 3) == 'r' && day.charAt(x + 4) == 's')
                    return true;
                else  if (day.charAt(x) == 'F' && day.charAt(x + 1) == 'R' && day.charAt(x + 2) == 'i')
                    return true;
                else  if (day.charAt(x) == 'S' && day.charAt(x + 1) == 'a' && day.charAt(x + 2) == 't')
                    return true;
                else  if (day.charAt(x) == 'S' && day.charAt(x + 1) == 'u' && day.charAt(x + 2) == 'n')
                    return true;
            }
        }
        return false;
    }

    /*
    6. Add a method (stretch goal)
    ```
    Name: removeVowelFromBetweenX
    Inputs: String
    Output: String
    Description: Look for the pattern "x[any vowel]x" in a string. If you find it, remove the vowel.
    Return a new string with all the vowels between x removed.
    Examples:
    xox -> xx
    onexexx -> onexxx
    xerrex -> xerrex
    xuxxuxxux -> xxxxxx
    ```
     */
    public String removeVowelFromBetweenX(String string)
    {
        // 7. Create tests for removeVowelFromBetweenX and confirm the method is correct.
        String[] splitString = string.split("");
        for (int x = 1; x < splitString.length - 1; x ++)
        {
            if (isVowel(splitString[x]) && splitString[x - 1].equals("x") && splitString[x + 1].equals("x"))
            {
                splitString[x] = "";
            }
        }
        string = String.join("", splitString);
        return string;
    }

    public boolean isVowel(String c)
    {
        return (c.equals("a") || c.equals("e") || c.equals("i") || c.equals("o") || c.equals("u"));
    }
}