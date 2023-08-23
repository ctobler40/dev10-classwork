package org.example;
import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        // The BufferedReader class provides the ability to read a line at a time similar to the Scanner class.
        // Other readers use byte-oriented reading, so the BufferedReader is a good choice when we're using text-based,
        // line-based file processing.

        //region 1. Find how many lines contain the word "Alice". Print it to the screen.
        int total = numberOfLines("Alice", false);
        System.out.println("Number of Lines with \"Alice\": " + total);
        //endregion
        //region 2. Find how many lines contain the word "Alice" and write it to a file: Alice,38.
        total = numberOfLines("Alice", false);
        writeToFile("Alice,38.txt", ("Number of Lines with \"Alice\": " + total));
        //endregion
        //region 3. Count all occurrences of "Alice", not just lines containing "Alice". Write it to a file.
        total = numberOfLines("Alice", true);
        writeToFile("Alice,39.txt", ("Number of Occurrences with \"Alice\": " + total));
        //endregion
        //region 4. Count all occurrences of "Mad Hatter". Write it to a file.
        total = numberOfLines("Mad Hatter", true);
        writeToFile("MadHatter,40.txt", ("Number of Occurrences with \"Mad Hatter\": " + total));
        //endregion
        //region 5. Count all occurrences of "croquet". Write it to a file.
        total = numberOfLines("croquet", true);
        writeToFile("croquet,41.txt", ("Number of Occurrences with \"croquet\": " + total));
        //endregion

        // Advanced
        //region 1. Split all words and count them.
        // A HashMap<String, Integer> might be useful for this. (String == word, Integer == count)
        HashMap<String, Integer> wordCount = new HashMap<>();
        wordCount = setHashMapOfWordCount(wordCount, "AIW_ReadMeFile.txt");
        //endregion
        //region 2. Write all words and their counts to a file.
        writeToFile("totalWordCount.txt", wordCount);
        //endregion
        //region 3. Sort words alphabetically with their counts and write to a file.
        ArrayList<String> orderByKey = new ArrayList<>();
        HashMap<String, Integer> sortedByKey = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> stringWordEntry : wordCount.entrySet())
            orderByKey.add(stringWordEntry.getKey());
        // Sort the Words
        Collections.sort(orderByKey);
        // Redistribute in Hash Map
        for (String string : orderByKey)
        {
            for (Map.Entry<String, Integer> stringWordEntry : wordCount.entrySet())
                if (stringWordEntry.getKey().equals(string))
                    sortedByKey.put(string, stringWordEntry.getValue());
        }
        writeToFile("totalWordCountByAlphabet.txt", sortedByKey);
        //endregion
        //region 4. Sort words by count descending and write to a file.
        ArrayList<Integer> orderByValue = new ArrayList<>();
        HashMap<String, Integer> sortedByValue = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> stringWordEntry : wordCount.entrySet())
            orderByValue.add(stringWordEntry.getValue());
        // Sort the Numbers
        Collections.sort(orderByValue);
        // Redistribute in Hash Map
        for (int num : orderByValue)
        {
            for (Map.Entry<String, Integer> stringWordEntry : wordCount.entrySet())
                if (stringWordEntry.getValue().equals(num))
                    sortedByValue.put(stringWordEntry.getKey(), num);
        }
        writeToFile("totalWordCountByNumber.txt", sortedByValue);
        //endregion
        //region 5. Scan for quotes (very difficult):
        // '“' and '”'

        //endregion
    }

    public static HashMap<String, Integer> setHashMapOfWordCount(HashMap<String, Integer> wordCount, String fileName)
    {
        List<String> allWords;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            // Traverse through the every line in the file
            for (String line = reader.readLine(); line != null; line = reader.readLine())
            {
                allWords = Arrays.asList(line.split(" "));
                for (int y = 0; y < allWords.size(); y ++)
                {
                    // Before we put them in the hash table, we want to lowercase and remove all punctuation
                    allWords.set(y, removePunctuationAndCaps(allWords.get(y)));

                    // We also want to check, if there is a "", we keep the full phrase
                    keepQuotedLines(allWords, y);

                    // If that word is not in the HashMap, we add it. Otherwise, we change it's count
                    // The HashMap getOrDefault method lets us get the current key value, or givesa default if there is no key
                    wordCount.put(allWords.get(y), wordCount.getOrDefault(allWords.get(y), 0) + 1);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            // Oh well, nothing is there yet
        }
        catch (IOException e)
        {
            System.out.println("IOException Error: ");
        }
        return wordCount;
    }
    public static void keepQuotedLines(List<String> allWords, int y)
    {
        ArrayList<String> fullQuote = new ArrayList<>();
        int firstQuote = -1, secondQuote = -1;
        for (int x = 0; x < allWords.get(y).length(); x ++)
        {
            if (Character.toString(allWords.get(y).charAt(x)).equalsIgnoreCase("“") && firstQuote == -1)
                firstQuote = y;
            else if (Character.toString(allWords.get(y).charAt(x)).equalsIgnoreCase("”") && secondQuote == -1)
                secondQuote = y;
        }

        if (firstQuote != -1 && secondQuote != -1)
        {
            for (int i = firstQuote; i < secondQuote; i ++)
                fullQuote.add(allWords.get(i));
            System.out.println(firstQuote + "   " + secondQuote + "   " + String.join(" ", fullQuote));
            allWords.set(firstQuote, String.join(" ", fullQuote));
            //for (int i = firstQuote + 1; i < secondQuote; i ++)
            //    allWords.remove(i);
        }
    }
    public static String removePunctuationAndCaps(String string)
    {
        ArrayList<String> newWordList = new ArrayList<>();
        for (int x = 0; x < string.length(); x ++)
        {
            if (isNotPunctuation(string.charAt(x)))
                newWordList.add(Character.toString(string.charAt(x)));
        }
        return String.join("", newWordList).toLowerCase();
    }
    public static boolean isNotPunctuation(char c)
    {
        return (c != '.' && c != ',' && c != '!' && c != '?' && c != ')' && c != '(' && c != ':' &&
                c != '/' && c != '•' && c != '*' && c != '_' && c != '-' && c != '=' &&
                c != '+' && c != '[' && c != ']' && c != '{' && c != '}' && c != ' ' && c != '—' && c != ';');
    }
    public static void writeToFile(String fileName, HashMap<String, Integer> itemsToWrite)
    {
        try (PrintWriter writer = new PrintWriter(fileName))
        {
            writer.println("Number of Occurrences");
            for (Map.Entry<String, Integer> stringWordCount : itemsToWrite.entrySet())
            {
                int numTimes = stringWordCount.getValue();
                writer.println(stringWordCount.getKey() + ": " + numTimes + " times!");
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    public static void writeToFile(String fileName, String itemToWrite)
    {
        PrintWriter writer = null;
        try
        {
            writer = new PrintWriter(fileName);
            writer.println(itemToWrite);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (writer != null)
                writer.close();
        }
    }
    public static int numberOfLines(String value, boolean countOccurances)
    {
        int num = 0;
        String[] words = null, valueWords = value.split(" ");

        try (BufferedReader reader = new BufferedReader(new FileReader("AIW_ReadMeFile.txt")))
        {
            if (valueWords.length > 1)
            {
                // Inspect this for loop! New way I've seen it work!
                for (String line = reader.readLine(); line != null; line = reader.readLine())
                {
                    words = line.split(" ");
                    for (int x = 0; x < words.length; x ++)
                    {
                        for (int y = 0; y < valueWords.length; y ++)
                        {
                            if (words[x + y].equalsIgnoreCase(valueWords[y]))
                            {
                                num += 1;
                                if (!countOccurances)
                                    break;
                            }
                            else
                                break;
                        }
                    }
                }
            }
            else
            {
                // Inspect this for loop! New way I've seen it work!
                for (String line = reader.readLine(); line != null; line = reader.readLine())
                {
                    words = line.split(" ");
                    for (int x = 0; x < words.length; x ++)
                    {
                        if (words[x].equalsIgnoreCase(value))
                        {
                            num += 1;
                            if (!countOccurances)
                                break;
                        }
                    }
                }
            }
        }
        catch (FileNotFoundException e)
        {
            // Oh well, nothing is there yet
        }
        catch (IOException e)
        {
            System.out.println("IOException Error: ");
        }

        return num;
    }
}