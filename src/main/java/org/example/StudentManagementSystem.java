package org.example;

import java.util.Scanner;

class Student {
    public static int studentId;
    public static String name;
    public static int age;
    public static double subject1Marks;
    public static double subject2Marks;
    public static double subject3Marks;

    Student(int studentId, String name, int age,
            double subject1Marks, double subject2Marks, double subject3Marks) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.subject1Marks = subject1Marks;
        this.subject2Marks = subject2Marks;
        this.subject3Marks = subject3Marks;
    }

    public static void displayStudent() {
        System.out.printf("%-8d%-14s%-8d%-9.0f%-9.0f%-9.0f\n",
                studentId, name, age, subject1Marks, subject2Marks, subject3Marks);
    }
}

public class StudentManagementSystem {
    private static Student[] students = new Student[10];
    private static int count = 0;

    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int choice = 0;
        while (choice != 8) {
            System.out.println();
            System.out.println("========================================");
            System.out.println("        STUDENT MANAGEMENT SYSTEM");
            System.out.println("========================================");
            System.out.println();
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Display Student Result");
            System.out.println("5. Update Student Marks");
            System.out.println("6. Class Statistics");
            System.out.println("7. Subject-wise Marks");
            System.out.println("8. Exit");
            System.out.println();
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayAllStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    displayResult();
                    break;
                case 5:
                    updateMarks();
                    break;
                case 6:
                    displayStatistics();
                    break;
                case 7:
                    displaySubjectWiseMarks();
                    break;
                case 8:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice, please enter 1 to 8.");
            }
        }
    }

    public static void addStudent() {
        if (count >= 10) {
            System.out.println("Cannot add more students. Maximum limit (10) reached.");
            return;
        }

        int id;
        do {
            System.out.print("Enter Student ID: ");
            id = sc.nextInt();

            if (id <= 0) {
                System.out.println("Student ID should be positive.");
            } else if (findStudent(id) != -1) {
                System.out.println("This Student ID already exists.");
                id = -1;
            }
        } while (id <= 0);

        sc.nextLine();
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        int age;
        do {
            System.out.print("Enter Age: ");
            age = sc.nextInt();

            if (age < 5 || age > 100) {
                System.out.println("Please enter a reasonable age (5 - 100).");
            }
        } while (age < 5 || age > 100);

        double m1 = getMarks("Enter Maths Marks: ");
        double m2 = getMarks("Enter Physics Marks: ");
        double m3 = getMarks("Enter English Marks: ");
        students[count] = new Student(id, name, age, m1, m2, m3);
        count++;

        System.out.println();
        System.out.println("Student added successfully.");
    }

    public static double getMarks(String message) {
        double marks;
        do {
            System.out.print(message);
            marks = sc.nextDouble();
            if (marks < 0 || marks > 100) {
                System.out.println("Marks must be between 0 and 100.");
            }
        } while (marks < 0 || marks > 100);

        return marks;
    }

    public static int findStudent(int id) {
        for (int i = 0; i < count; i++) {
            if (students[i].studentId == id) {
                return i;
            }
        }
        return -1;
    }


    public static void displayAllStudents() {
        if (count == 0) {
            System.out.println("No students added yet.");
            return;
        }
        printTableHeader();
        for (int i = 0; i < count; i++) {
            students[i].displayStudent();
        }
        System.out.println("---------------------------------------------------------");
    }

    public static void printTableHeader() {
        System.out.println("---------------------------------------------------------");
        System.out.printf("%-8s%-14s%-8s%-9s%-9s%-9s\n",
                "ID", "Name", "Age", "Maths", "Physics", "English");
        System.out.println("---------------------------------------------------------");
    }

    public static void searchStudent() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        int index = findStudent(id);
        if (index == -1) {
            System.out.println("Student not found.");
        } else {
            printTableHeader();
            students[index].displayStudent();
            System.out.println("---------------------------------------------------------");
        }
    }

    public static void displayResult() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        int index = findStudent(id);
        if (index == -1) {
            System.out.println("Student not found.");
            return;
        }

        Student s = students[index];
        double total = calculateTotal(s);
        double average = calculateAverage(s.subject1Marks, s.subject2Marks, s.subject3Marks);
        double percentage = total / 300 * 100;
        String grade = calculateGrade(percentage);

        String status;
        if (isPassed(s)) {
            status = "PASS";
        } else {
            status = "FAIL";
        }

        System.out.println();
        System.out.println("====================================");
        System.out.println("          STUDENT RESULT");
        System.out.println("====================================");
        System.out.println();
        System.out.println("Student ID     : " + s.studentId);
        System.out.println("Name           : " + s.name);
        System.out.println("Age            : " + s.age);
        System.out.println();
        System.out.println("Maths          : " + s.subject1Marks);
        System.out.println("Physics        : " + s.subject2Marks);
        System.out.println("English        : " + s.subject3Marks);
        System.out.println();
        System.out.println("Total Marks    : " + total);
        System.out.printf("Average        : %.2f\n", average);
        System.out.printf("Percentage     : %.2f%%\n", percentage);
        System.out.println("Grade          : " + grade);
        System.out.println("Status         : " + status);
        System.out.println();
        System.out.println("====================================");
    }

    public static void updateMarks() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        int index = findStudent(id);
        if (index == -1) {
            System.out.println("Student not found.");
            return;
        }

        students[index].subject1Marks = getMarks("Enter new Maths Marks: ");
        students[index].subject2Marks = getMarks("Enter new Physics Marks: ");
        students[index].subject3Marks = getMarks("Enter new English Marks: ");
        System.out.println("Student marks updated successfully.");
    }

    public static void displayStatistics() {
        if (count == 0) {
            System.out.println("No students added yet.");
            return;
        }
        double sum = 0;
        double highest = 0;
        double lowest = 100;
        int passCount = 0;
        int failCount = 0;

        for (int i = 0; i < count; i++) {
            double percentage = calculateTotal(students[i]) / 300 * 100;
            sum = sum + percentage;
            if (percentage > highest) {
                highest = percentage;
            }
            if (percentage < lowest) {
                lowest = percentage;
            }
            if (isPassed(students[i])) {
                passCount++;
            } else {
                failCount++;
            }
        }

        double averagePercentage = sum / count;

        System.out.println();
        System.out.println("====================================");
        System.out.println("          CLASS STATISTICS");
        System.out.println("====================================");
        System.out.println();
        System.out.println("Number of Students : " + count);
        System.out.printf("Average Percentage : %.2f%%\n", averagePercentage);
        System.out.printf("Highest Percentage : %.2f%%\n", highest);
        System.out.printf("Lowest Percentage  : %.2f%%\n", lowest);
        System.out.println("Pass Count         : " + passCount);
        System.out.println("Fail Count         : " + failCount);
        System.out.println();
        System.out.println("====================================");
    }

    public static void displaySubjectWiseMarks() {
        if (count == 0) {
            System.out.println("No students added yet.");
            return;
        }

        double[][] marks = new double[count][3];

        for (int i = 0; i < count; i++) {
            marks[i][0] = students[i].subject1Marks;
            marks[i][1] = students[i].subject2Marks;
            marks[i][2] = students[i].subject3Marks;
        }

        double[] subjectTotal = new double[3];
        String[] subjectName = {"Maths", "Physics", "English"};
        System.out.println("--------------------------------------------------");
        System.out.printf("%-10s%-16s%-10s%-10s%-10s\n",
                "ID", "Name", "Maths", "Physics", "English");
        System.out.println("--------------------------------------------------");

        for (int i = 0; i < count; i++) {
            System.out.printf("%-10d%-16s", students[i].studentId, students[i].name);
            for (int j = 0; j < 3; j++) {
                System.out.printf("%-10.0f", marks[i][j]);
                subjectTotal[j] = subjectTotal[j] + marks[i][j];
            }
            System.out.println();
        }
        System.out.println("--------------------------------------------------");
        System.out.println();

        for (int j = 0; j < 3; j++) {
            double avg = subjectTotal[j] / (double) count;   // cast to double
            System.out.printf("%-8s Average : %.2f\n", subjectName[j], avg);
        }
    }

    public static double calculateTotal(Student s) {
        return s.subject1Marks + s.subject2Marks + s.subject3Marks;
    }

    public static double calculateAverage(int mark1, int mark2) {
        int total = mark1 + mark2;
        double average = (double) total / 2;
        return average;
    }

    public static double calculateAverage(int mark1, int mark2, int mark3) {
        int total = mark1 + mark2 + mark3;
        double average = (double) total / 3;
        return average;
    }

    public static double calculateAverage(double mark1, double mark2, double mark3) {
        return (mark1 + mark2 + mark3) / 3;
    }

    public static String calculateGrade(double percentage) {
        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B";
        } else if (percentage >= 60) {
            return "C";
        } else if (percentage >= 50) {
            return "D";
        } else if (percentage >= 40) {
            return "E";
        } else {
            return "F";
        }
    }

    public static boolean isPassed(Student s) {
        if (s.subject1Marks >= 40 && s.subject2Marks >= 40 && s.subject3Marks >= 40) {
            return true;
        } else {
            return false;
        }
    }
}