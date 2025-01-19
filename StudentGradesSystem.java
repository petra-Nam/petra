import java.util.HashMap;

class Student {
    String studentId;
    double grade;

    public Student(String studentId, double grade) {
        this.studentId = studentId;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId + ", Grade: " + grade;
    }
}

public class StudentGradesSystem {

    private HashMap<String, Student> students;

    public StudentGradesSystem() {
        students = new HashMap<>();
    }

    // Add a student with their grade
    public void addStudent(String studentId, double grade) {
        students.put(studentId, new Student(studentId, grade));
    }

    // Remove a student by their student ID
    public void removeStudent(String studentId) {
        students.remove(studentId);
    }

    // Update a student's grade
    public void updateGrade(String studentId, double newGrade) {
        Student student = students.get(studentId);
        if (student != null) {
            student.grade = newGrade;
        } else {
            System.out.println("Student not found.");
        }
    }

    // Get a student's grade by their student ID
    public Student getStudent(String studentId) {
        return students.get(studentId);
    }

    // Print all students and their grades
    public void listStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
        } else {
            for (String studentId : students.keySet()) {
                System.out.println(students.get(studentId));
            }
        }
    }

    public static void main(String[] args) {
        StudentGradesSystem system = new StudentGradesSystem();

        // Adding students
        system.addStudent("S001", 89.5);
        system.addStudent("S002", 76.4);
        system.addStudent("S003", 92.3);

        // List all students and grades
        System.out.println("All students:");
        system.listStudents();

        // Searching for a student's grade
        System.out.println("\nSearching for student with ID S002: " + system.getStudent("S002"));

        // Update a student's grade
        system.updateGrade("S001", 95.0);
        System.out.println("\nAfter updating grade:");
        system.listStudents();

        // Remove a student
        system.removeStudent("S003");
        System.out.println("\nAfter removing a student:");
        system.listStudents();
    }
}
