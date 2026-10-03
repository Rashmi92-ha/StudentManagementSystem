import java.util.InputMismatchException;
import java.util.Scanner;

public class Main{
    static int readInt(Scanner sc , String prompt){
        while (true){
            System.out.print(prompt);
            try{
                int value = sc.nextInt();
                sc.nextLine();
                return value;
            }catch (InputMismatchException e){
                System.out.println("Please Enter a number");
                sc.nextLine();
            }
        }
    }
    static int readId(Scanner sc, String prompt) {
        while (true) {
            int id = readInt(sc, prompt);
            if (Student.isValidId(id)) {
                return id;
            }
            System.out.println("ID must be a positive number.");
        }
    }

    static int readAge(Scanner sc, String prompt) {
        while (true) {
            int age = readInt(sc, prompt);
            if (Student.isValidAge(age)) {
                return age;
            }
            System.out.println("Age must be between " + Student.MIN_AGE + " and " + Student.MAX_AGE + ".");
        }
    }

    static String readText(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = sc.nextLine().trim();
            if (Student.isValidText(text)) {
                return text;
            }
            System.out.println("Value cannot be empty or contain a comma.");
        }
    }
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();

        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n--- Student Management ---");
                System.out.println("1. Add Student");
                System.out.println("2. View All Students");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");

                int choice = readInt(sc, "Enter your choice: ");

                switch (choice) {
                    case 1: {
                        int id = readId(sc, "Enter id: ");
                        if (manager.exists(id)) {
                            System.out.println("A student with ID " + id + " already exists.");
                            break;
                        }
                        String name = readText(sc, "Enter name: ");
                        int age = readAge(sc, "Enter age: ");
                        String course = readText(sc, "Enter course: ");
                        manager.addStudent(new Student(id, name, age, course));
                        System.out.println("Student added.");
                        break;
                    }
                    case 2:
                        manager.viewAllStudents();
                        break;
                    case 3: {
                        int id = readId(sc, "Enter id to update: ");
                        if (!manager.exists(id)) {
                            System.out.println("No student found with ID " + id + ".");
                            break;
                        }
                        String name = readText(sc, "Enter updated name: ");
                        int age = readAge(sc, "Enter updated age: ");
                        String course = readText(sc, "Enter updated course: ");
                        manager.updateStudent(id, name, age, course);
                        System.out.println("Student updated.");
                        break;
                    }
                    case 4: {
                        int id = readId(sc, "Enter id to delete: ");
                        if (manager.deleteStudent(id)) {
                            System.out.println("Student deleted.");
                        } else {
                            System.out.println("No student found with ID " + id + ".");
                        }
                        break;
                    }
                    case 5:
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Choice not found.");
                }
            }
        }
    }
}