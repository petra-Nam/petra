import java.util.*;
import java.text.SimpleDateFormat;

public class FinancialTransactionTracker {
    public static void main(String[] args) throws Exception {
        // Initialize HashMap to store transactions
        HashMap<Integer, String> transactions = new HashMap<>();

        // Scanner for user input
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add a new transaction");
            System.out.println("2. Count transactions performed yesterday");
            System.out.println("3. Calculate total income and expenses");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add a new transaction
                    System.out.print("Enter Transaction ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Type (Purchase/Sale): ");
                    String type = scanner.nextLine();
                    System.out.print("Enter Amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Date (yyyy-MM-dd): ");
                    String date = scanner.nextLine();

                    String value = type + "," + amount + "," + date;
                    transactions.put(id, value);
                    System.out.println("Transaction added successfully!");
                    break;

                case 2:
                    // Count transactions performed yesterday
                    int count = countTransactionsYesterday(transactions);
                    System.out.println("Transactions performed yesterday: " + count);
                    break;

                case 3:
                    // Calculate total income and expenses
                    double[] totals = calculateTotals(transactions);
                    System.out.println("Total Income (Sales): " + totals[0]);
                    System.out.println("Total Expenses (Purchases): " + totals[1]);
                    break;

                case 4:
                    // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Method to count transactions performed yesterday
    public static int countTransactionsYesterday(HashMap<Integer, String> transactions) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1); // Yesterday
        String yesterday = sdf.format(cal.getTime());

        int count = 0;
        for (String value : transactions.values()) {
            String[] parts = value.split(",");
            String date = parts[2];
            if (date.equals(yesterday)) {
                count++;
            }
        }
        return count;
    }

    // Method to calculate total income (Sales) and expenses (Purchases)
    public static double[] calculateTotals(HashMap<Integer, String> transactions) {
        double totalIncome = 0.0;
        double totalExpenses = 0.0;

        for (String value : transactions.values()) {
            String[] parts = value.split(",");
            String type = parts[0];
            double amount = Double.parseDouble(parts[1]);

            if (type.equalsIgnoreCase("Sale")) {
                totalIncome += amount;
            } else if (type.equalsIgnoreCase("Purchase")) {
                totalExpenses += amount;
            }
        }

        return new double[]{totalIncome, totalExpenses};
    }
}
