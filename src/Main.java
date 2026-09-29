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
    public static void main(String[] args){
       StudentManager manager = new StudentManager();
       manager.loadFromFile();
       Scanner sc = new Scanner(System.in);
        
        while (true){
            System.out.println("\n --- Student Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. View All Student");
            System.out.println("3. Update student");
            System.out.println("4. Delete student");
            System.out.println("5. Exit");

            int choice = readInt(sc , "Enter the Choice: ") ;
            if(choice == 1){
                int id = readInt(sc , "Enter id: ");

                System.out.println("Enter Name: ");
                String name = sc.nextLine();

                int age = readInt(sc, "Enter age: ");

                System.out.println("Enter course: ");
                String course = sc.nextLine();
                
                Student s = new Student(id,name,age,course);
                manager.addStudent(s);
                System.out.println("Student added");
            } else if (choice == 4) {
                int id = readInt(sc, "Enter the id to delete: ");
                manager.deleteStudent(id);
            } else if (choice == 3) {
                int id = readInt(sc , "Enter id to update: ");

                System.out.println("Enter updated name: ");
                String name = sc.nextLine();

                int age = readInt(sc, "Enter updated age: ");
                System.out.println("Enter updated course: ");
                String course = sc.nextLine();
                manager.updateStudent(id,name,age,course);
            } else if (choice == 2) {
                manager.viewAllStudents();
            } else if(choice == 5){
                manager.saveToFile();
                System.out.println("Good Bye");
                break;
            } else{
                System.out.println("Choice not found");
            }
        }
    }
}