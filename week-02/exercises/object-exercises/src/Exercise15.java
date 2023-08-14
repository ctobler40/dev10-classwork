public class Exercise15
{
    // 1. Create a new method in the Hero class.
    // Name: toLine
    // Inputs: none
    // Output: String
    // Description: returns the Hero's name and powers as a single line of text.

    public static void main(String[] args)
    {
        // 2. Instantiate your three favorite super heroes with appropriate powers.
        Hero ironMan = new Hero("Iron Man", new Power[]{new Power("Iron")});
        Hero batMan = new Hero("Batman", new Power[]{new Power("Money"), new Power("Alfred")});
        Hero superMan = new Hero("Superman", new Power[]{new Power("Everything"), new Power("And More")});

        // 3. Use the `toLine` method to print each hero's details to the console.
        System.out.println(ironMan.ToLine());
        System.out.println(batMan.ToLine());
        System.out.println(superMan.ToLine());
    }
}
