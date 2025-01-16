import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int numberToGuess = random.nextInt(100) + 1; // Random number between 1 and 100
        int numberOfTries = 0;
        int userGuess = 0;
        boolean guessedCorrectly = false;

        System.out.println("Welcome to the Guess the Number game!");
        System.out.println("I have picked a number between 1 and 100. Can you guess it?");
        
        while (!guessedCorrectly) {
            System.out.print("Enter your guess: ");
            try {
                userGuess = scanner.nextInt();
                numberOfTries++;

                if (userGuess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else if (userGuess > numberToGuess) {
                    System.out.println("Too high! Try again.");
                } else {
                    guessedCorrectly = true;
                    System.out.println("Congratulations! You guessed the number in " + numberOfTries + " tries.");
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                scanner.next(); // Clear invalid input
            }
        }
        System.out.println("Thanks for playing!");
        scanner.close();
    }
}

