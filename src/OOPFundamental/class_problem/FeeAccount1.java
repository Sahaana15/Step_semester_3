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

    void fullProfile() {

        System.out.println("Student Name: " + studentName);
        System.out.println("Student ID: " + studentId);

        System.out.println("Fee Balance: Rs " + feeAccount.getBalance());

        if (hostelRoom != null) {
            System.out.println("Hostel Room: " + hostelRoom.roomNo);
            System.out.println("Room Type: " + hostelRoom.roomType);
        } else {
            System.out.println("Hostel Room: Not Allocated");
        }

        System.out.println();
    }

    static void printTotalStudents() {
        System.out.println("Total Students: " + totalStudents);
    }

    public static void main(String[] args) {

        FeeAccount f1 = new FeeAccount(50000);
        FeeAccount f2 = new FeeAccount(30000);
        FeeAccount f3 = new FeeAccount(45000);

        HostelRoom h1 = new HostelRoom(101, "Single");
        HostelRoom h2 = new HostelRoom(202, "Double");

        FeeAccount1 s1 = new FeeAccount1("Aditi", 1001, f1, h1);
        FeeAccount1 s2 = new FeeAccount1("Rohan", 1002, f2, h2);
        FeeAccount1 s3 = new FeeAccount1("Meera", 1003, f3, null);

        s1.fullProfile();
        s2.fullProfile();
        s3.fullProfile();

        printTotalStudents();
    }
}

class FeeAccount {

    private double balance;

    FeeAccount(double balance) {
        this.balance = balance;
    }

    double getBalance() {
        return balance;
    }
}

class HostelRoom {

    int roomNo;
    String roomType;

    HostelRoom(int roomNo, String roomType) {
        this.roomNo = roomNo;
        this.roomType = roomType;
    }
}