import java.io.*;
import java.util.*;

class TodoItem {
    private String task;
    private boolean isCompleted;

    public TodoItem(String task) {
        this.task = task;
        this.isCompleted = false;
    }

    public String getTask() {
        return task;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public void markAsPending() {
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        return (isCompleted ? "[Done] " : "[Pending] ") + task;
    }
}

public class TodoListApp {
    private List<TodoItem> tasks;
    private static final String FILE_NAME = "todos.txt";

    public TodoListApp() {
        tasks = new ArrayList<>();
        loadTasksFromFile();
    }

    public void addTask(String task) {
        tasks.add(new TodoItem(task));
        System.out.println("Task added: " + task);
        saveTasksToFile();
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            System.out.println("Task removed.");
            saveTasksToFile();
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void markTaskAsCompleted(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsCompleted();
            System.out.println("Task marked as completed.");
            saveTasksToFile();
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Your Todo List:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    private void saveTasksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (TodoItem task : tasks) {
                writer.write(task.getTask() + "," + task.isCompleted());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private void loadTasksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    TodoItem task = new TodoItem(parts[0]);
                    if (Boolean.parseBoolean(parts[1])) {
                        task.markAsCompleted();
                    }
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("No saved tasks to load.");
        }
    }

    public static void main(String[] args) {
        TodoListApp todoListApp = new TodoListApp();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nTodo List Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. View Tasks");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task description: ");
                    String task = scanner.nextLine();
                    todoListApp.addTask(task);
                    break;
                case 2:
                    todoListApp.viewTasks();
                    System.out.print("Enter task number to remove: ");
                    int removeIndex = scanner.nextInt() - 1;
                    todoListApp.removeTask(removeIndex);
                    break;
                case 3:
                    todoListApp.viewTasks();
                    System.out.print("Enter task number to mark as completed: ");
                    int completeIndex = scanner.nextInt() - 1;
                    todoListApp.markTaskAsCompleted(completeIndex);
                    break;
                case 4:
                    todoListApp.viewTasks();
                    break;
                case 5:
                    System.out.println("Exiting the Todo List App. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
