 import java.util.*;

enum PizzaSize {
    SMALL(8.99), MEDIUM(12.99), LARGE(15.99);

    private final double price;

    PizzaSize(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}

enum Topping {
    CHEESE(1.50), PEPPERONI(2.00), MUSHROOMS(1.00), OLIVES(1.20), BACON(2.50);

    private final double price;

    Topping(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}

class PizzaOrder {
    private PizzaSize size;
    private List<Topping> toppings;

    public PizzaOrder(PizzaSize size) {
        this.size = size;
        this.toppings = new ArrayList<>();
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public double calculateTotal() {
        double total = size.getPrice();
        total += toppings.stream().mapToDouble(Topping::getPrice).sum();
        return total;
    }

    public void displayOrder() {
        System.out.println("\n🍕 Pizza Order Summary 🍕");
        System.out.println("Size: " + size + " - $" + size.getPrice());
        if (!toppings.isEmpty()) {
            System.out.println("Toppings:");
            toppings.forEach(topping -> System.out.println("  - " + topping + " ($" + topping.getPrice() + ")"));
        }
        System.out.println("Total Price: $" + calculateTotal());
    }
}

public class PizzaOrderingSystem {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

        // Select pizza size
        System.out.println("Choose your pizza size: SMALL, MEDIUM, LARGE");
        PizzaSize size = PizzaSize.valueOf(scanner.next().toUpperCase());

        PizzaOrder order = new PizzaOrder(size);

        // Add toppings
        System.out.println("Add toppings (CHEESE, PEPPERONI, MUSHROOMS, OLIVES, BACON), type 'done' to finish:");
        while (true) {
            String input = scanner.next().toUpperCase();
            if (input.equals("DONE")) break;
            try {
                order.addTopping(Topping.valueOf(input));
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid topping! Try again.");
            }
        }

        // Display the order summary
        order.displayOrder();
    }

 {
    
}
}
}
