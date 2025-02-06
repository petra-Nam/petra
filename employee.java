 import java.util.*;
import java.util.stream.Collectors;

class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{ID=" + id + ", Name='" + name + "', Dept='" + department + "', Salary=" + salary + "}";
    }
}

class EmployeeManagementSystem {
    private Map<Integer, Employee> employees = new HashMap<>();

    // Add employee
    public void addEmployee(Employee emp) {
        employees.put(emp.getId(), emp);
    }

    // Remove employee
    public void removeEmployee(int id) {
        employees.remove(id);
    }

    // Get employee by ID
    public Employee getEmployee(int id) {
        return employees.get(id);
    }

    // Display all employees
    public void displayAllEmployees() {
        employees.values().forEach(System.out::println);
    }

    // Get employees by department using streams
    public List<Employee> getEmployeesByDepartment(String department) {
        return employees.values().stream()
                .filter(emp -> emp.getDepartment().equalsIgnoreCase(department))
                .collect(Collectors.toList());
    }

    // Get employees with salary greater than a value
    public List<Employee> getHighSalaryEmployees(double minSalary) {
        return employees.values().stream()
                .filter(emp -> emp.getSalary() > minSalary)
                .collect(Collectors.toList());
    }

    // Get the highest-paid employee
    public Optional<Employee> getHighestPaidEmployee() {
        return employees.values().stream()
                .max(Comparator.comparingDouble(Employee::getSalary));
    }
}

public class employee {
    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem();

        // Adding employees
        ems.addEmployee(new Employee(1, "Alice", "IT", 70000));
        ems.addEmployee(new Employee(2, "Bob", "HR", 50000));
        ems.addEmployee(new Employee(3, "Charlie", "IT", 80000));
        ems.addEmployee(new Employee(4, "David", "Finance", 90000));

        // Display all employees
        System.out.println("All Employees:");
        ems.displayAllEmployees();

        // Get employees in IT department
        System.out.println("\nEmployees in IT Department:");
        ems.getEmployeesByDepartment("IT").forEach(System.out::println);

        // Get employees with salary greater than 60,000
        System.out.println("\nEmployees earning more than 60,000:");
        ems.getHighSalaryEmployees(60000).forEach(System.out::println);

        // Get highest-paid employee
        System.out.println("\nHighest Paid Employee:");
        ems.getHighestPaidEmployee().ifPresent(System.out::println);
    }


 {
    
}
 }
