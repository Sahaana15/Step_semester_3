package ObjectClassMethodsInnerClasses.assignment_probelms;
import java.util.*;

interface CreditPolicy {
    int getCreditLimit();
}

class RegularPolicy implements CreditPolicy {

    @Override
    public int getCreditLimit() {
        return 24;
    }
}

class HonorsPolicy implements CreditPolicy {

    @Override
    public int getCreditLimit() {
        return 28;
    }
}

class ExchangePolicy implements CreditPolicy {

    @Override
    public int getCreditLimit() {
        return 20;
    }
}

class Student {

    private String name;
    private int currentCredits;
    private CreditPolicy policy;

    public Student(
            String name,
            int currentCredits,
            CreditPolicy policy) {

        this.name = name;
        this.currentCredits = currentCredits;
        this.policy = policy;
    }

    public String getName() {
        return name;
    }

    public int getCurrentCredits() {
        return currentCredits;
    }

    public int getCreditLimit() {
        return policy.getCreditLimit();
    }

    public boolean canAddCredits(int credits) {

        return currentCredits + credits
                <= getCreditLimit();
    }

    public void addCredits(int credits) {
        currentCredits += credits;
    }

    public void removeCredits(int credits) {
        currentCredits -= credits;
    }
}

class Elective {

    private String name;
    private int creditValue;
    private int capacity;

    private List<Student> enrolledStudents;

    private Queue<Student> waitlist;

    public Elective(
            String name,
            int creditValue,
            int capacity) {

        this.name = name;
        this.creditValue = creditValue;
        this.capacity = capacity;

        enrolledStudents = new ArrayList<>();
        waitlist = new LinkedList<>();
    }

    public boolean isEnrolled(Student student) {
        return enrolledStudents.contains(student);
    }

    public boolean isWaiting(Student student) {
        return waitlist.contains(student);
    }

    public boolean isFull() {
        return enrolledStudents.size() >= capacity;
    }

    public boolean enroll(Student student) {

        if (isEnrolled(student)
                || isWaiting(student)) {

            return false;
        }

        if (student.canAddCredits(creditValue)) {

            enrolledStudents.add(student);
            student.addCredits(creditValue);

            System.out.println(
                    student.getName()
                            + " enrolled in "
                            + name
                            + " (credits: "
                            + student.getCurrentCredits()
                            + "/"
                            + student.getCreditLimit()
                            + ")."
            );

            return true;
        }

        return false;
    }

    public void addToWaitlist(Student student) {

        if (!isEnrolled(student)
                && !isWaiting(student)) {

            waitlist.add(student);

            System.out.println(
                    name
                            + " is full. "
                            + student.getName()
                            + " added to waitlist (position "
                            + waitlist.size()
                            + ")."
            );
        }
    }

    public void drop(Student student) {

        if (!enrolledStudents.remove(student)) {
            return;
        }

        student.removeCredits(creditValue);

        System.out.println(
                student.getName()
                        + " dropped "
                        + name
                        + " (credits: "
                        + student.getCurrentCredits()
                        + "/"
                        + student.getCreditLimit()
                        + ")."
        );

        promoteNext();
    }

    private void promoteNext() {

        while (!waitlist.isEmpty()
                && !isFull()) {

            Student student =
                    waitlist.poll();

            if (student.canAddCredits(creditValue)) {

                enrolledStudents.add(student);

                student.addCredits(
                        creditValue
                );

                System.out.println(
                        student.getName()
                                + " promoted from waitlist "
                                + "and enrolled in "
                                + name
                                + " (credits: "
                                + student.getCurrentCredits()
                                + "/"
                                + student.getCreditLimit()
                                + ")."
                );

                break;

            } else {

                System.out.println(
                        student.getName()
                                + " cannot be promoted because "
                                + "the credit limit would be exceeded."
                );
            }
        }
    }

    public String getName() {
        return name;
    }

    public int getCreditValue() {
        return creditValue;
    }
}

class EnrollmentService {

    public void enroll(
            Student student,
            Elective elective) {

        if (elective.isEnrolled(student)
                || elective.isWaiting(student)) {

            System.out.println(
                    "Enrollment failed: "
                            + student.getName()
                            + " is already enrolled or waiting."
            );

            return;
        }

        // Credit limit is checked BEFORE seat availability
        if (!student.canAddCredits(
                elective.getCreditValue())) {

            System.out.println(
                    "Enrollment failed: "
                            + student.getName()
                            + " would exceed the credit limit ("
                            + (student.getCurrentCredits()
                            + elective.getCreditValue())
                            + "/"
                            + student.getCreditLimit()
                            + ")."
            );

            return;
        }

        if (elective.isFull()) {

            elective.addToWaitlist(student);

        } else {

            elective.enroll(student);
        }
    }

    public void drop(
            Student student,
            Elective elective) {

        elective.drop(student);
    }
}

public class ElectiveSeatRush {

    public static void main(String[] args) {

        Elective cloudComputing =
                new Elective(
                        "Cloud Computing",
                        4,
                        2
                );

        Student asha =
                new Student(
                        "Asha",
                        20,
                        new RegularPolicy()
                );

        Student ravi =
                new Student(
                        "Ravi",
                        22,
                        new HonorsPolicy()
                );

        Student neha =
                new Student(
                        "Neha",
                        12,
                        new ExchangePolicy()
                );

        Student kiran =
                new Student(
                        "Kiran",
                        22,
                        new RegularPolicy()
                );

        EnrollmentService service =
                new EnrollmentService();

        service.enroll(asha, cloudComputing);

        service.enroll(ravi, cloudComputing);

        service.enroll(neha, cloudComputing);

        service.enroll(kiran, cloudComputing);

        service.drop(asha, cloudComputing);
    }
}