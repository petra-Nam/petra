 import java.util.Scanner;

public class TicketPriceCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input for age and student/senior status
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        
        System.out.print("Are you a student? (true/false): ");
        boolean isStudent = scanner.nextBoolean();
        
        System.out.print("Are you a senior? (true/false): ");
        boolean isSenior = scanner.nextBoolean();
        
        // Variable to hold the ticket price
        double ticketPrice = 0;

        // Calculate ticket price based on age and student/senior status
        if (age < 0) {
            System.out.println("Invalid age entered.");
        } else {
            if (isStudent) {
                if (age <= 30) {
                    ticketPrice = 10; // Discounted ticket for students under 30
                    System.out.println("You are a student under 30. Your ticket price is: $" + ticketPrice);
                } else {
                    ticketPrice = 15; // Regular ticket for students over 30
                    System.out.println("You are a student over 30. Your ticket price is: $" + ticketPrice);
                }
            } else if (isSenior) {
                if (age >= 65) {
                    ticketPrice = 7; // Discounted ticket for seniors 65 or older
                    System.out.println("You are a senior. Your ticket price is: $" + ticketPrice);
                } else {
                    ticketPrice = 15; // Regular ticket for non-senior adults
                    System.out.println("You are not eligible for senior discount. Your ticket price is: $" + ticketPrice);
                }
            } else {
                if (age < 18) {
                    ticketPrice = 5; // Discounted ticket for children
                    System.out.println("You are under 18. Your ticket price is: $" + ticketPrice);
                } else {
                    ticketPrice = 15; // Regular adult ticket price
                    System.out.println("You are an adult. Your ticket price is: $" + ticketPrice);
                }
            }
        }

        scanner.close();
    }

 {
    
}
 }