package OOPFundamental.class_problem;
public class FeeAccount1 {

    String studentName;
    int studentId;
    FeeAccount feeAccount;
    HostelRoom hostelRoom;

    static int totalStudents = 0;

    FeeAccount1(String studentName, int studentId,
                FeeAccount feeAccount, HostelRoom hostelRoom) {

        this.studentName = studentName;
        this.studentId = studentId;
        this.feeAccount = feeAccount;
        this.hostelRoom = hostelRoom;

        totalStudents++;
    }

    void display() {

        System.out.println("Student Name: " + studentName);
        System.out.println("Student ID: " + studentId);
        System.out.println("Fee Due: Rs " + feeAccount.getDue());

        if (hostelRoom != null)
            System.out.println("Hostel Room: Allocated");
        else
            System.out.println("Hostel Room: Not Allocated");

        System.out.println();
    }

    public static void main(String[] args) {

        FeeAccount f1 = new FeeAccount("RA101", 150000);
        FeeAccount f2 = new FeeAccount("RA102", 200000);

        FeeAccount1 s1 =
                new FeeAccount1("Aditi", 1001, f1, null);

        FeeAccount1 s2 =
                new FeeAccount1("Rohan", 1002, f2, null);

        f1.pay(50000);
        f2.pay(100000);

        s1.display();
        s2.display();

        System.out.println("Total Students: " + totalStudents);
    }
}