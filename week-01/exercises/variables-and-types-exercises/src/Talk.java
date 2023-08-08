public class Talk
{
    public static void main (String [] args)
    {
        // Partner Task
        // Declare an integer variable called numberOfDays
        // Assign your numberOfDays variable
        // If the integer is greater than 5, Concat the variable to a string with the text "greater" after it
        // Otherwise, Concat the variable to a string with the text "lesser" after it
        // Print the result

        // My Task
        // Take a string value that's a name
        String name = "General Kenobi";

        // Compare a boolean if the string length is higher than 10 characters
        boolean stringHigher = (name.length() > 10);

        // Create another string variable that will be an email
        String email = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";

        // Create another boolean to see if the string length is higher than 50 characters
        boolean emailHigher = (email.length() > 50);

        // Print out if the username or password is valid
        boolean valid = !(stringHigher && emailHigher);

        System.out.println("The inputs are " + valid + " and " +
                (valid ? "will work" : "will not work"));

        /*
        // Side note: Another way to display strings in Java
        String limerick = """
        There once was a cat from Peru, 
        Whose dancing was quite a to-do, 
        He'd tango and twirl, 
        With a flip and a whirl,
        And cha-cha with a kangaroo, too!""";
        System.out.print(limerick);
         */
    }
}
