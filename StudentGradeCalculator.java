package gradecalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StudentGradeCalculator {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addGrade();
                    break;
                case 3:
                    displayAllStudents();
                    break;
                case 4:
                    computeStatistics();
                    break;
                case 5:
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n===== Student Grade Calculator =====");
        System.out.println("1. Add a new student");
        System.out.println("2. Add grade for a student");
        System.out.println("3. Display all students and their grades");
        System.out.println("4. Compute statistics");
        System.out.println("5. Exit");
    }

    private static void addStudent() {
        String name = getStringInput("Enter student name: ");
        students.add(new Student(name));
        System.out.println("Student added successfully.");
    }

    private static void addGrade() {
        if (students.isEmpty()) {
            System.out.println("No students added yet. Please add a student first.");
            return;
        }

        displayAllStudents();
        int index = getIntInput("Enter the student number: ") - 1;
        
        if (index >= 0 && index < students.size()) {
            double grade = getDoubleInput("Enter grade (0-100): ");
            if (grade >= 0 && grade <= 100) {
                students.get(index).addGrade(grade);
                System.out.println("Grade added successfully.");
            } else {
                System.out.println("Invalid grade. Please enter a number between 0 and 100.");
            }
        } else {
            System.out.println("Invalid student number.");
        }
    }

    private static void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students added yet.");
            return;
        }

        System.out.println("\nStudent List:");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i));
        }
    }

    private static void computeStatistics() {
        if (students.isEmpty()) {
            System.out.println("No students added yet.");
            return;
        }

        double overallSum = 0;
        int totalGrades = 0;
        double overallHighest = Double.MIN_VALUE;
        double overallLowest = Double.MAX_VALUE;

        for (Student student : students) {
            if (!student.getGrades().isEmpty()) {
                overallSum += student.getAverageGrade() * student.getGrades().size();
                totalGrades += student.getGrades().size();
                overallHighest = Math.max(overallHighest, student.getHighestGrade());
                overallLowest = Math.min(overallLowest, student.getLowestGrade());
            }
        }

        if (totalGrades == 0) {
            System.out.println("No grades have been entered for any student.");
            return;
        }

        double overallAverage = overallSum / totalGrades;

        System.out.println("\nOverall Statistics:");
        System.out.printf("Average score: %.2f\n", overallAverage);
        System.out.printf("Highest score: %.2f\n", overallHighest);
        System.out.printf("Lowest score: %.2f\n", overallLowest);

        System.out.println("\nIndividual Student Statistics:");
        for (Student student : students) {
            System.out.println(student.getName() + ":");
            if (!student.getGrades().isEmpty()) {
                System.out.printf("  Average: %.2f\n", student.getAverageGrade());
                System.out.printf("  Highest: %.2f\n", student.getHighestGrade());
                System.out.printf("  Lowest: %.2f\n", student.getLowestGrade());
            } else {
                System.out.println("  No grades entered yet.");
            }
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}