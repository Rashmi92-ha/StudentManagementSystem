public class Student {
    int id;
    String name;
    int age;
    String course;
    Student(int id, String name, int age,String course){
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }
    @Override
    public String toString(){
        return "Id: " + id + " | Name: " + name + " | Age: " + age + " | Course: " + course;
    }
}
