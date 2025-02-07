import java.util.Scanner;

public class Exercise07
{
    static Scanner console = new Scanner(System.in);

    public static void main(String[] args)
    {
        MoneyStorage storage = getStorage();
        String input;
        do
        {
            print(storage);

            System.out.println("Choose an action:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Exit");
            System.out.print("Select [1-3]:");

            input = console.nextLine();

            if (input.equals("1"))
            {
                makeDeposit(storage);
            }
            else if (input.equals("2"))
            {
                makeWithdrawal(storage);
            }

        }
        while (!input.equals("3"));

        System.out.println("Goodbye.");
    }

    static MoneyStorage getStorage()
    {
        System.out.println("Choose a place to store your money:");
        System.out.println("1. Wallet");
        System.out.println("2. Mortgage");
        System.out.println("3. Vault");
        System.out.println("4. Bank Account");

        String input;
        do
        {
            System.out.print("Select [1-4]:");
            input = console.nextLine();
        }
        while (!(input.length() == 1 && input.charAt(0) >= '1' && input.charAt(0) <= '4'));

        MoneyStorage myMoneyStorage;

        // 1. Add a switch statement to handle options 1 - 4.
        switch (Integer.parseInt(input))
        {
            // 2. For each option, create a method that returns a MoneyStorage of the appropriate type:
            // 1 == Wallet, 2 == Mortgage, 3 == Vault, 4 == Bank Account
            case 1:
                myMoneyStorage = makeWallet();
                break;
            case 2:
                myMoneyStorage = makeMortgage();
                break;
            case 3:
                myMoneyStorage = makeVault();
                break;
            case 4:
                myMoneyStorage = makeBankAccount();
                break;
            default:
                myMoneyStorage = null;
                break;
        }


        // Prompt the user for data. Data should be specific to the type:
        // - a starting balance
        // - description for a Wallet
        // - accountNumber for Mortgage and BankAccount
        // - for Vault?

        // 3. Return the Wallet, Mortgage, Vault, or Bank Account instead of null.
        return myMoneyStorage;
    }

    static MoneyStorage makeWallet()
    {
        Scanner input = new Scanner(System.in);
        int startingBalance = 0;
        String description = "";

        System.out.print("Starting Balance: ");
        startingBalance = input.nextInt();
        System.out.print("Description: ");
        description = input.nextLine();

        return new Wallet(startingBalance, description);
    }

    static MoneyStorage makeMortgage()
    {
        Scanner input = new Scanner(System.in);
        int startingBalance = 0;
        String accountNum = "";

        System.out.print("Starting Balance: ");
        startingBalance = input.nextInt();
        System.out.print("Account Number: ");
        accountNum = input.nextLine();

        return new Mortgage(startingBalance, accountNum);
    }

    static MoneyStorage makeVault()
    {
        Scanner input = new Scanner(System.in);
        int startingBalance = 0;
        String description = "";

        System.out.print("Starting Balance: ");
        startingBalance = input.nextInt();
        System.out.print("Description: ");
        description = input.nextLine();

        return new Vault(startingBalance, description);
    }

    static MoneyStorage makeBankAccount()
    {
        Scanner input = new Scanner(System.in);
        int startingBalance = 0;
        String accountNum = "";

        System.out.print("Starting Balance: ");
        startingBalance = input.nextInt();
        System.out.print("Account Number: ");
        accountNum = input.nextLine();

        return new BankAccount(startingBalance, accountNum);
    }

    static void print(MoneyStorage storage)
    {
        System.out.println();
        System.out.println(storage.getDescription());
        System.out.println("=".repeat(storage.getDescription().length()));
        System.out.printf("Current balance: $%.2f%n", storage.getBalance());
        System.out.println();
    }

    static void makeDeposit(MoneyStorage storage)
    {
        System.out.print("Amount to deposit:");
        double amt = Double.parseDouble(console.nextLine());
        storage.deposit(amt);
    }

    static void makeWithdrawal(MoneyStorage storage)
    {
        System.out.print("Amount to withdraw:");
        double amt = Double.parseDouble(console.nextLine());
        double withdrawn = storage.withdraw(amt);
        System.out.printf("Withdrawn: $%.2f%n", withdrawn);
    }
}
