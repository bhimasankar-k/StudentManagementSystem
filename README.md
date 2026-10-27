# Student Management System

A console-based **Student Management System** built in **Core Java**. It lets users add, view, search, update and analyse student records through a menu-driven interface.

This project is a Core Java integrated assignment, written using only the concepts covered in the course (no collections framework, no external libraries).

---

## Objective

Implement and demonstrate these Core Java concepts in one application:

- Java basics, variables and primitive data types
- Operators (arithmetic, relational, logical)
- `if / else` and `switch`
- Type casting
- `Scanner` for user input
- `for`, `while` and `do-while` loops
- Nested loops
- One-dimensional and two-dimensional arrays
- Methods, method parameters and return values
- Method overloading
- Classes and objects
- Constructors and the `this` keyword

---

## Problem Statement

Create a Java application called **Student Management System** that manages information for multiple students through a menu-driven console interface.

### Each student has

- Student ID
- Name
- Age
- Marks for 3 subjects (**Maths**, **Physics**, **English**)

### The program calculates and displays

- Total Marks
- Average Marks
- Percentage
- Grade
- Pass / Fail Status

---

## Application Menu

The menu keeps appearing until the user selects **8. Exit**.

```text
========================================
        STUDENT MANAGEMENT SYSTEM
========================================

1. Add Student
2. Display All Students
3. Search Student
4. Display Student Result
5. Update Student Marks
6. Class Statistics
7. Subject-wise Marks
8. Exit

Enter your choice:
```

---

## Features

### 1. Add Student
Prompts for Student ID, Name, Age and marks for each subject, then creates a `Student` object and stores it in the array.

### 2. Display All Students
Shows all student records in a table, using a loop to go through the array.

```text
---------------------------------------------------------
ID      Name          Age     Maths    Physics  English
---------------------------------------------------------
101     Ravi          20      78       85       91
102     Priya         21      88       76       95
103     Arun          20      65       72       69
---------------------------------------------------------
```

### 3. Search Student
Asks for a Student ID, searches the array and shows the student's details. If no student matches, it prints `Student not found.`

### 4. Display Student Result
Asks for a Student ID and shows the complete result.

```text
====================================
          STUDENT RESULT
====================================

Student ID     : 101
Name           : Ravi
Age            : 20

Maths          : 78.0
Physics        : 85.0
English        : 91.0

Total Marks    : 254.0
Average        : 84.67
Percentage     : 84.67%
Grade          : A
Status         : PASS

====================================
```

### 5. Update Student Marks
Asks for a Student ID. If the student exists, the user enters new marks for each subject. The marks are validated before they are saved, and the program prints `Student marks updated successfully.`

### 6. Class Statistics
Displays the number of students, average percentage, highest percentage, lowest percentage, pass count and fail count.

```text
====================================
          CLASS STATISTICS
====================================

Number of Students : 10
Average Percentage : 76.45%
Highest Percentage : 94.20%
Lowest Percentage  : 45.30%
Pass Count         : 8
Fail Count         : 2

====================================
```

### 7. Subject-wise Marks
Displays the marks of all students in a table and the average of each subject. A two-dimensional array is used for this (rows are students, columns are subjects), processed with nested loops.

```text
Maths    Average : 77.00
Physics  Average : 78.50
English  Average : 85.25
```

### 8. Exit
Ends the program.

---

## Grade Calculation Rules

| Percentage | Grade |
|------------|-------|
| 90 – 100   | A+    |
| 80 – 89    | A     |
| 70 – 79    | B     |
| 60 – 69    | C     |
| 50 – 59    | D     |
| 40 – 49    | E     |
| Below 40   | F     |

## Pass Criteria

A student is considered **PASS** only if the marks in **all three subjects** are 40 or more:

```java
subject1Marks >= 40 && subject2Marks >= 40 && subject3Marks >= 40
```

Otherwise the status is **FAIL**.

---

## Validation Requirements

The application validates that:

1. Student ID is positive.
2. Age is reasonable (between 5 and 100).
3. Marks are between 0 and 100 (`marks >= 0 && marks <= 100`).
4. Student ID is not duplicated.
5. The array capacity (maximum 10 students) is not exceeded.

---

## Implementation Requirements

### Student class

Instance variables:

```java
int studentId;
String name;
int age;
double subject1Marks;   // Maths
double subject2Marks;   // Physics
double subject3Marks;   // English
```

- A parameterized constructor initialises all values using the `this` keyword.
- `displayStudent()` displays the details of a student.

### Storing multiple students

- Students are stored in an array: `Student[] students`.
- Maximum of **10 students**.
- Arrays only (no separate objects like `s1`, `s2`, `s3`).

### Methods

The application is divided into multiple methods and avoids putting all the logic in `main()`:

```java
addStudent()
displayAllStudents()
searchStudent()
displayResult()
updateMarks()
displayStatistics()
displaySubjectWiseMarks()

calculateTotal()
calculateAverage()
calculateGrade()
isPassed()
```

### Method overloading

`calculateAverage` is overloaded to show that one method name can have different parameter lists:

```java
calculateAverage(int mark1, int mark2)
calculateAverage(int mark1, int mark2, int mark3)
calculateAverage(double mark1, double mark2, double mark3)
```

### Switch statement

The main menu is implemented with a `switch` statement:

```java
switch (choice) {
    case 1:
        addStudent();
        break;
    case 2:
        displayAllStudents();
        break;
    // ...
}
```

### Loops

| Loop | Where it is used |
|------|------------------|
| `while` | Keeps the menu running until the user selects Exit |
| `for` | Traverses the student array, displays records, calculates statistics |
| `do-while` | Repeats input until the entered value is valid (marks, age, ID) |

### Nested loops

A two-dimensional array `double[][] marks` is used, where rows are students and columns are subjects. Nested loops display and process this matrix:

```java
for (int i = 0; i < rows; i++) {
    for (int j = 0; j < cols; j++) {
        // process marks
    }
}
```

### Type casting

Explicit casting avoids integer division when calculating averages:

```java
double average = (double) total / 3;
```

Without the cast, `total / 3` would be integer division and would drop the decimal part.

### Operators

- **Arithmetic:** `+ - * / %`
- **Relational:** `> < >= <= == !=`
- **Logical:** `&& || !`

### Scanner

All user input is read with `Scanner`: menu choice, student ID, name, age, subject marks, search ID and updated marks.

---

## Technical Restrictions

The project uses **Core Java only**. The following are **not** used:

- `ArrayList`
- Java Collections Framework
- Streams API
- Lambda expressions
- Database connectivity
- External libraries
- GUI frameworks
- Spring Framework
- Hibernate
- Any third-party framework

---

## Project Structure

```text
StudentManagementSystem
├── pom.xml
└── src
    └── main
        └── java
            └── org
                └── example
                    └── StudentManagementSystem.java
```

`StudentManagementSystem.java` contains both the `Student` class and the `StudentManagementSystem` class (which holds `main`).

---

## How to Run

### In IntelliJ IDEA

1. Open the project.
2. Open `StudentManagementSystem.java`.
3. Click the green **Run** arrow next to the `main` method.
4. Type your inputs in the **Run** window at the bottom.

### From the terminal

From the project root folder:

```bash
javac src/main/java/org/example/StudentManagementSystem.java
java -cp src/main/java org.example.StudentManagementSystem
```

**Requirements:** JDK 8 or higher installed (`javac -version` to check).

---

## Sample Program Flow

```text
Enter your choice: 1

Enter Student ID: 101
Enter Student Name: Ravi
Enter Age: 20
Enter Maths Marks: 78
Enter Physics Marks: 85
Enter English Marks: 91

Student added successfully.

Enter your choice: 4

Enter Student ID: 101
...
```
