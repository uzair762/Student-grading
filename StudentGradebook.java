package P12;
import java.util.Scanner;

public class StudentGradebook {
    private static final int MAX_STUDENTS = 50;
    private static int[] rollNumbers = new int[MAX_STUDENTS];
    private static String[] names = new String[MAX_STUDENTS];
    private static int[] marks = new int[MAX_STUDENTS];
    private static int studentCount = 0;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n==== Student Gradebook Manager ====");
            System.out.println("1) Add Student");
            System.out.println("2) Display All");
            System.out.println("3) Search Student (by Roll / by Name)");
            System.out.println("4) Class Average & Topper");
            System.out.println("5) Exit");
            System.out.print("Choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                    
                case 2:
                    displayAll();
                    break;
                    
                case 3:
                    searchStudent(scanner);
                    break;
                    
                case 4:
                    showClassStats();
                    break;
                    
                case 5:
                    System.out.println("Goodbye!");
                    return;
                    
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
    
    private static void addStudent(Scanner scanner) {
        if (studentCount >= MAX_STUDENTS) {
            System.out.println("Gradebook is full!");
            return;
        }
        
        System.out.print("Enter Roll: ");
        int roll = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        
        int mark;
        do {
            System.out.print("Enter Marks (0-100): ");
            mark = scanner.nextInt();
            if (mark < 0 || mark > 100) {
                System.out.println("Invalid marks! Please enter a value between 0 and 100.");
            }
        } while (mark < 0 || mark > 100);
        
        rollNumbers[studentCount] = roll;
        names[studentCount] = name;
        marks[studentCount] = mark;
        studentCount++;
        
        System.out.println("Student added successfully.");
    }
    
    private static void displayAll() {
        if (studentCount == 0) {
            System.out.println("No students in gradebook.");
            return;
        }
        
        System.out.println("Roll\tName\tMarks\tGrade");
        for (int i = 0; i < studentCount; i++) {
            System.out.println(rollNumbers[i] + "\t" + names[i] + "\t" + 
                               marks[i] + "\t" + grade(marks[i]));
        }
        System.out.println("Total Students: " + studentCount);
    }
    
    private static void searchStudent(Scanner scanner) {
        System.out.println("Search by: 1) Roll 2) Name");
        int searchType = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        if (searchType == 1) {
            System.out.print("Enter Roll to search: ");
            int roll = scanner.nextInt();
            search(roll);
        } else if (searchType == 2) {
            System.out.print("Enter Name to search: ");
            String name = scanner.nextLine();
            search(name);
        } else {
            System.out.println("Invalid search type!");
        }
    }
    
    private static void search(int roll) {
        for (int i = 0; i < studentCount; i++) {
            if (rollNumbers[i] == roll) {
                System.out.println("Found:");
                System.out.println("Roll: " + rollNumbers[i]);
                System.out.println("Name: " + names[i]);
                System.out.println("Marks: " + marks[i]);
                System.out.println("Grade: " + grade(marks[i]));
                return;
            }
        }
        System.out.println("No record found.");
    }
    
    private static void search(String name) {
        for (int i = 0; i < studentCount; i++) {
            if (names[i].equalsIgnoreCase(name)) {
                System.out.println("Found:");
                System.out.println("Roll: " + rollNumbers[i]);
                System.out.println("Name: " + names[i]);
                System.out.println("Marks: " + marks[i]);
                System.out.println("Grade: " + grade(marks[i]));
                return;
            }
        }
        System.out.println("No record found.");
    }
    
    private static void showClassStats() {
        if (studentCount == 0) {
            System.out.println("No students in gradebook.");
            return;
        }
        
        double average = computeAverage(marks, studentCount);
        System.out.printf("Class Average: %.2f\n", average);
        
        // Find topper
        int maxMarks = -1;
        int topperIndex = -1;
        for (int i = 0; i < studentCount; i++) {
            if (marks[i] > maxMarks) {
                maxMarks = marks[i];
                topperIndex = i;
            }
        }
        
        System.out.println("Topper:");
        System.out.println("Roll: " + rollNumbers[topperIndex]);
        System.out.println("Name: " + names[topperIndex]);
        System.out.println("Marks: " + marks[topperIndex]);
        System.out.println("Grade: " + grade(marks[topperIndex]));
    }
    
    private static double computeAverage(int[] marks, int count) {
        int sum = 0;
        for (int i = 0; i < count; i++) {
            sum += marks[i];
        }
        return (double) sum / count;
    }
    
    private static char grade(int marks) {
        if (marks >= 85) return 'A';
        else if (marks >= 70) return 'B';
        else if (marks >= 50) return 'C';
        else return 'F';
    }
}