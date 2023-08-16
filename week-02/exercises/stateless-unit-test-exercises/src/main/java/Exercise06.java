import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class Exercise06
{
    // 1. Read the capitalizeAll JavaDocs.
    // 2. Implement capitalizeAll.
    // 3. Implement suggestions in Exercise06Test.
    // 4. Confirm implementation correctness by running tests.
    // 5. Stretch Goal: instead of capitalizing the first character of each element, capitalize the first character
    // of each word in each element.

    /**
     * Capitalizes the first character of each element in a String[]
     * and returns the result in a new array.
     * A null argument should return null.
     * An empty array should return a new empty array.
     * Null or empty array elements should be ignored.
     *
     * @param values an array containing elements to capitalize.
     * @return a new String[] with each element capitalized.
     */
    public String[] capitalizeAll(String[] values)
    {
        ArrayList<String[]> listOfLetterArrays = new ArrayList<String[]>();
        for (int i = 0; i < values.length; i++)
        {
            listOfLetterArrays.add(values[i].split(""));

            // Uppercase the first letter
            listOfLetterArrays.get(i)[0] = listOfLetterArrays.get(i)[0].toUpperCase();

            // Stretch Goal: Capitalize the first char of each word within each element
            for (int j = 1; j < values[i].length(); j ++)
                if (listOfLetterArrays.get(i)[j - 1].equals(" "))
                    listOfLetterArrays.get(i)[j] = listOfLetterArrays.get(i)[j].toUpperCase();

            // Now stitch the array back into one string
            values[i] = String.join("", listOfLetterArrays.get(i));
            System.out.println(values[i]);
        }
        return values;
    }
}
