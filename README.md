# Student Management System (CLI-Based)

A console-based Java application to manage student records — add, view, update, and delete — with data saved to a file so it persists between runs.

**Tags:** Java, OOPs, Collection Framework, File Handling

## Features

- **Add** a student (id, name, age, course)
- **View** all students
- **Update** a student's name, age, and course by id
- **Delete** a student by id
- Data is **saved to `students.txt`** on exit and **loaded back automatically** the next time the program starts

## Project Structure

- `Student.java` — the student record: fields, constructor, and `toString()` for display
- `StudentManager.java` — holds the list of students (`ArrayList<Student>`) and implements add, view, find, update, delete, save, and load
- `Main.java` — the console menu loop that reads user input and calls the matching `StudentManager` method

## How OOP, Collections, and File Handling are used

- **OOP:** `Student` and `StudentManager` are separate classes with their own responsibilities — `Student` models one record, `StudentManager` operates on the collection of records.
- **Collections Framework:** students are stored in an `ArrayList<Student>`, giving dynamic add/remove without a fixed size.
- **File Handling:** `saveToFile()`/`loadFromFile()` use `PrintWriter`/`BufferedReader` to persist students as comma-separated lines (`id,name,age,course`) in `students.txt`.
- **Exception Handling:** the menu's number input is wrapped in a `try/catch` for `InputMismatchException`, so entering a non-numeric choice doesn't crash the program.

## How to Run

1. Open the project in IntelliJ (or compile manually with `javac`).
2. Run `Main.java`.
3. Use the on-screen menu: