import java.util.ArrayList;
import java.io.*;

public class StudentManager {
    ArrayList<Student> students = new ArrayList<>();
    private static final String FILE_NAME = "Student.txt";
    public StudentManager(){
        loadFromFile();
    }

    private Student findById(int id){
        for( Student s: students){
            if(s.getId() == id){
                return s;
            }
        }
        return null;
    }
    public boolean exists(int id){
        return findById(id) != null;
    }

    public  boolean addStudent (Student student){
        if(exists(student.getId())){
            return false;
        }
        students.add(student);
        saveToFile();
        return true;
    }
    public boolean updateStudent(int id, String name, int age, String course){
        Student s = findById(id);
        if(s == null){
            return false;
        }
        s.setName(name);
        s.setAge(age);
        s.setCourse(course);
        saveToFile();
        return true;
    }

    public boolean deleteStudent(int id){
        Student s = findById(id);
        if(s == null){
            return false;
        }
        students.remove(s);
        saveToFile();
        return true;
    }
    void viewAllStudents(){
        if(students.isEmpty()){
            System.out.println("No Students found");
            return;
        }
        System.out.println(String.format("%-6s %-20s %-5s %-20s", "ID", "Name", "Age", "Course"));
        System.out.println("-".repeat(54));
        for(Student s : students){
            System.out.println(s);
        }
    }

    void loadFromFile(){
        File file = new File(FILE_NAME);
        if(!file.exists()){
            return;
        }
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null){
                lineNumber++;
                if(line.trim().isEmpty()){
                    continue;
                }
                String[] parts = line.split(" , ", -1);
                if(parts.length != 4){
                    System.out.println("Skipping line " + lineNumber + ": invalid number.");
                } try{
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    int age = Integer.parseInt(parts[2].trim());
                    String course = parts[3].trim();
                    students.add(new Student(id, name, age, course));
                    if (!Student.isValidId(id)) {
                        System.out.println("Skipping line " + lineNumber + ": invalid ID.");
                    } else if (!Student.isValidText(name) || !Student.isValidText(course)) {
                        System.out.println("Skipping line " + lineNumber + ": empty name or course.");
                    } else if (!Student.isValidAge(age)) {
                        System.out.println("Skipping line " + lineNumber + ": age out of range.");
                    } else if (exists(id)) {
                        System.out.println("Skipping line " + lineNumber + ": duplicate ID " + id + ".");
                    } else {
                        students.add(new Student(id, name, age, course));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Skipping line " + lineNumber + ": invalid number.");
                }
            }
        }
        catch (IOException e){
            System.out.println("Error loading file" + e.getMessage());
        }
    }
    void saveToFile(){
        try(PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))){
            for (Student s: students){
                writer.println(s.toFileString());
            }
        }catch (IOException e){
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
