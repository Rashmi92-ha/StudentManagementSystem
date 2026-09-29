import java.util.ArrayList;
import java.io.*;

public class StudentManager {
    ArrayList<Student> students = new ArrayList<>();

    void addStudent (Student s){
        if(findById(s.id) != null){
            System.out.println("A student with this id already exists");
            return;
        }
        students.add(s);
    }

    void viewAllStudents(){
        if(students.isEmpty()){
            System.out.println("No Students found");
            return;
        }
        for(Student s : students){
            System.out.println(s);
        }
    }
    Student findById(int id){
        for( Student s: students){
            if(s.id == id){
                return s;
            }
        }
        return null;
    }
    void deleteStudent(int id){
        Student s = findById(id);
        if(s == null){
            System.out.println("Student not found");
            return;
        }
        students.remove(s);
        System.out.println("Student Deleted");
    }

    void updateStudent(int id, String name, int age, String course){
        Student s = findById(id);
        if(s == null){
            System.out.println("Student not found");
            return;
        }
        s.name = name;
        s.age = age;
        s.course = course;

        System.out.println("Student Updated");
    }

    void saveToFile(){
        try(PrintWriter writer = new PrintWriter(new FileWriter("Student.txt"))){
            for (Student s: students){
                writer.println(s.id + " , " + s.name + " , " + s.age + " , " + s.course);
            }
        }catch (IOException e){
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
    void loadFromFile(){
        File file = new File("Student.txt");
        if(!file.exists()){
            return;
        }
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null){
                String[] parts = line.split(" , ");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int age = Integer.parseInt(parts[2]);
                String course = parts[3];
                students.add(new Student(id, name, age, course));
            }
        }
        catch (IOException e){
            System.out.println("Error loading file" + e.getMessage());
        }
    }
}
