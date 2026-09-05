package OOPFundamental.class_problem;
public class SrmStudent1 {

    // Instance variables
    String name;
    String studentId;
    int coursesRegistered;

    // Static variables
    static String collegeName = "SRM Institute of Science and Technology";
    static int studentCount = 0;

    // Constructor
    SrmStudent1(String name) {
        this.name = name;
        studentCount++;

        studentId = "SRM-" + (1000 + studentCount);
    }

    // Instance method
    void printStudentCard() {
        System.out.println("Name: " + name);
        System.out.println("Student ID: " + studentId);
        System.out.println("Courses Registered: " + coursesRegistered);
        System.out.println("College: " + collegeName);
    }

    // Static method
    static void printTotalStudents() {
        System.out.println("Total Students: " + studentCount);
    }

    public static void main(String[] args) {

        SrmStudent1 s1 = new SrmStudent1("Aditi");
        SrmStudent1 s2 = new SrmStudent1("Rohan");

        s1.coursesRegistered = 5;
        s2.coursesRegistered = 4;

        s1.printStudentCard();
        System.out.println();

        s2.printStudentCard();
        System.out.println();

        SrmStudent1.printTotalStudents();
    }
}
