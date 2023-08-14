/*
# Exercise05

1. Add a new class to the project named `BankAccount`.
2. `BankAccount` must implement `MoneyStorage`.
3. Complete the implementation. Add fields, constructors, and getters as required.
    (Refer to `Mortgage` for inspiration, but with a positive balance.)
5. Rules:
    - Deposits must be positive values.
    - Can overdraw up to -25.00 dollars, but no lower.
    (The balance is allowed to go negative.)
 */

public class BankAccount implements MoneyStorage
{
    private double balance;
    private String accountNumber;

    public BankAccount(double startingBalance, String accountNumber)
    {
        this.balance = startingBalance;
        this.accountNumber = accountNumber;
    }

    @Override
    public double getBalance()
    {
        return balance;
    }

    @Override
    public String getDescription()
    {
        return String.format("Bank Account #%s", accountNumber);
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    @Override
    public boolean deposit(double amount)
    {
        // Must deposit a positive number
        if (amount <= 0)
        {
            return false;
        }
        balance = Math.max(0.0, balance + amount);
        return true;
    }

    @Override
    public double withdraw(double amount)
    {
        // can overdraw up to $25, but nothing more
        if (amount - balance > 25)
        {
            return 0;
        }
        else if (amount > balance)
        {
            // If we overdraw less than $25, our account will go megative
            balance -= amount;
            return amount;
        }
        // Otherwise then, just subtract and return the amount
        balance -= amount;
        return amount;
    }
}
