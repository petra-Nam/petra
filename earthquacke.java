import java.util.Scanner;

class Earthquake{

    public static String printAdvice (double magnitude){
        int category_of_earthquake = (magnitude < 2.0) ? 0 :
                       (magnitude < 4.0) ? 1 :
                       (magnitude < 5.0) ? 2 :
                       (magnitude < 6.0) ? 3 :
                       (magnitude < 7.0) ? 4 :
                       (magnitude < 8.0) ? 5 : 6;

        switch (category_of_earthquake) {
            case 0:
                return "Micro: Detected only by seismographs.";
            case 1:
                return "Minor: Rarely felt, but recorded.";
            case 2:
                return "Light: Felt by people, minor damage.";
            case 3:
                return "Moderate: Can cause damage in populated areas.";
            case 4:
                return "Strong: Serious damage in areas up to 100 miles.";
            case 5:
                return "Major: Severe damage over large areas.";
            case 6:
                return "Great: Massive destruction.";
            default:
                return "Unknown magnitude.";
        }



    }

 public static void main(String[] args) {


    Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter earthquake magnitude: ");
            double magnitude = scanner.nextDouble();
            System.out.print(printAdvice(magnitude));
            scanner.close();


        } catch (Exception e) {
            System.out.println("Magnitude is not a number.");

        }
    }


}
