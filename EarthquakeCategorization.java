import java.util.Scanner;

public class EarthquakeCategorization {
    public static void main(String[] args) {
        // Create a scanner to read the user input
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user to enter the magnitude
        System.out.print("Enter earthquake magnitude: ");
        double magnitude = scanner.nextDouble();
        
        // Classify the earthquake based on magnitude using switch
        String advice;
        int category = (int) Math.floor(magnitude); // We use floor to handle magnitude ranges
        
        switch (category) {
            case 0: case 1:
                advice = "Micro: Detected only by seismographs. No immediate action required.";
                break;
            case 2: case 3:
                advice = "Minor: Felt, but no damage. Stay alert.";
                break;
            case 4:
                advice = "Light: May cause minor damage indoors. Be cautious.";
                break;
            case 5:
                advice = "Moderate: Minor damage may occur. Be cautious of falling debris.";
                break;
            case 6:
                advice = "Strong: Serious damage may occur within 100 miles. Stay safe.";
                break;
            case 7: case 8:
                advice = "Major: Severe damage over large areas. Evacuate if needed.";
                break;
            default:
                if (magnitude >= 8.0) {
                    advice = "Great: Massive destruction. Seek shelter immediately.";
                } else {
                    advice = "Magnitude out of range.";
                }
                break;
        }
        
        // Output the classification and advice
        System.out.println(advice);
        
        // Close scanner
        scanner.close();
    }
}

