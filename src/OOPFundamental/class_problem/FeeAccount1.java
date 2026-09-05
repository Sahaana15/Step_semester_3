package OOPFundamental.class_problem;
public class FeeAccount1 {

    String studentName;
    int studentId;
    FeeAccount fee;
    HostelRoom room;

    static int totalStudents = 0;

    FeeAccount1(String name, int id, FeeAccount fee, HostelRoom room) {
        studentName = name;
        studentId = id;
        this.fee = fee;
        this.room = room;
        totalStudents++;
    }

    void display() {
        System.out.println("Name: " + studentName);
        System.out.println("Student ID: " + studentId);
        System.out.println("Fee Balance: " + fee.balance);

        if (room != null) {
            System.out.println("Room No: " + room.roomNo);
        } else {
            System.out.println("Room: Not Allocated");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        FeeAccount f1 = new FeeAccount(50000);
        FeeAccount f2 = new FeeAccount(30000);

        HostelRoom r1 = new HostelRoom(101);
        HostelRoom r2 = new HostelRoom(102);

        FeeAccount1 s1 = new FeeAccount1("Aditi", 1001, f1, r1);
        FeeAccount1 s2 = new FeeAccount1("Rohan", 1002, f2, r2);

        s1.display();
        s2.display();

        System.out.println("Total Students: " + totalStudents);
    }
}

class FeeAccount {

    double balance;

    FeeAccount(double balance) {
        this.balance = balance;
    }
}

class HostelRoom {

    int roomNo;

    HostelRoom(int roomNo) {
        this.roomNo = roomNo;
    }
}