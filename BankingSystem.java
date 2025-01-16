import java.util.Scanner;

class Account {
    private String accountHolderName;
    private double balance;

    // Constructor
    public Account(String accountHolderName, double initialBalance) {
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
    }

    // Deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited $" + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew $" + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
    }

    // Display account details
    public void displayAccountInfo() {
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Current Balance: $" + balance);
    }
}

public class BankingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create an account
        System.out.println("Welcome to the Banking System!");
        System.out.print("Enter account holder's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter initial deposit: ");
        double initialDeposit = scanner.nextDouble();
        Account account = new Account(name, initialDeposit);

        // Menu-driven system
        int choice;
        do {
            System.out.println("\nBanking Menu:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Display Account Info");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                }
                case 2 -> {
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                }
                case 3 -> account.displayAccountInfo();
                case 4 -> System.out.println("Thank you for using the Banking System. Goodbye!");
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
