public class Exercise01
{
    /*
    Open the following types and read their comments.
    - MoneyStorage (interface)
    - Wallet (class)
    - Mortgage (class)
     */
    public static void main(String[] args)
    {
        Wallet wallet = new Wallet(0.0, "Brown Wallet");
        wallet.deposit(5.55);
        print(wallet);

        Mortgage mortgage = new Mortgage(250000.0, "abcd-1234-hdre-9898");
        print(mortgage);

        // 1. Declare a third MoneyStore variable here, either Wallet or Mortgage.
        Wallet myWallet = new Wallet(0.0, "Clown Wallet");

        // 2. Pass it to the `print` method.
        print(myWallet);

        Person me = new Person("CJ", "Tobler");
        print(me);
    }

    static void print(MoneyStorage storage)
    {
        System.out.println(storage.getDescription());
        System.out.printf("Current balance: $%.2f%n", storage.getBalance());
    }
}
