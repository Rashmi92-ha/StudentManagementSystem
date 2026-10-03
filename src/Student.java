public class Student {
    public static final int MIN_AGE = 1;
    public static final int MAX_AGE = 120;

    private final int id;          // no setter: an ID must not change after creation
    private String name;
    private int age;
    private String course;

    public Student(int id, String name, int age, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    // Validation lives here so Main and file loading use the same rules
    public static boolean isValidId(int id) {
        return id > 0;
    }

    public static boolean isValidAge(int age) {
        return age >= MIN_AGE && age <= MAX_AGE;
    }

    // Commas are rejected because they would break the comma-separated file
    public static boolean isValidText(String text) {
        return text != null && !text.trim().isEmpty() && !text.contains(",");
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getCourse() { return course; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setCourse(String course) { this.course = course; }

    public String toFileString() {
        return id + "," + name + "," + age + "," + course;
    }

    @Override
    public String toString() {
        return String.format("%-6d %-20s %-5d %-20s", id, name, age, course);
    }
}