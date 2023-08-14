public class Exercise01
{

    public static void main(String[] args)
    {
        Musician ocean = new Musician("Frank Ocean", 10);
        System.out.println(ocean.getName());

        // 1. Find the Musician class in this project.
        // 2. Read its constructor comments.
        // 3. Instantiate two more musicians and assign them to new variables.
        Musician presley = new Musician("Elvis Presley", 7);
        Musician jackson = new Musician("Michael Jackson", 5);

        // 4. Print the musicians' names to the console.
        System.out.println(presley.getName());
        System.out.println(jackson.getName());
    }
}
