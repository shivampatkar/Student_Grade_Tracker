package gradecalculator;

import java.util.ArrayList;
import java.util.Collections;

class Student {
    private String name;
    private ArrayList<Double> grades;

    public Student(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public void addGrade(double grade) {
        grades.add(grade);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Double> getGrades() {
        return grades;
    }

    public double getAverageGrade() {
        if (grades.isEmpty()) {
            return 0;
        }
        return grades.stream().mapToDouble(Double::doubleValue).average().orElse(0);
    }

    public double getHighestGrade() {
        return grades.isEmpty() ? 0 : Collections.max(grades);
    }

    public double getLowestGrade() {
        return grades.isEmpty() ? 0 : Collections.min(grades);
    }

    @Override
    public String toString() {
        return name + " - Grades: " + grades;
    }
}