package org.example;

/*
# Exercise09

1. Create a new Maven project. You may want to open this file with a different editor so IntelliJ can do its thing.
2. Add the latest version of JUnit 5 as a Maven dependency.
3. Add a class named `ArrayMethods`.
4. Add a method:
    ```
    Name: removeZero
    Inputs: int[]
    Output: int[]
    Description: Finds all non-zero elements and copies them to a new array.
    Don't over allocated.
    You'll need to count non-zeros first.
    ```
5. Create tests for removeZero and confirm it is correct.
 */

public class Exercise09
{
    public int[] removeZero(int[] numbers)
    {
        int numNonZeros = 0;
        for (int i = 0; i < numbers.length; i++)
        {
            if (numbers[i] != 0)
                numNonZeros += 1;
        }
        int[] newNumbers = new int[numNonZeros];
        int y = 0;
        for (int x = 0; x < numbers.length; x++)
        {
            if (numbers[x] != 0)
            {
                newNumbers[y] = numbers[x];
                y += 1;
            }
        }
        return newNumbers;
    }
}