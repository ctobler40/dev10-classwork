public class Exercise02 {

    public static void main(String[] args) {

        // 1. Add a getter for the rating field in Musician.

        Musician ocean = new Musician("Frank Ocean", 10);
        System.out.println(ocean.getName());
        // 2. Uncomment the line below and insure that it compiles and runs.
        System.out.println(ocean.getRating());

        // 3. Instantiate two musicians and assign them to new variables.
        Musician presley = new Musician("Elvis Presley", 7);
        Musician jackson = new Musician("Michael Jackson", 5);

        // 4. Print each musicians' name and rating on a single line.
        System.out.println(presley.getName() + " - " + presley.getRating());
        System.out.println(jackson.getName() + " - " + jackson.getRating());
    }
}
