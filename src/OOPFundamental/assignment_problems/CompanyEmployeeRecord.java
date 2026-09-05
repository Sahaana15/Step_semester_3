package OOPFundamental.assignment_problems;
public class CompanyEmployeeRecord {

    String name;
    String empId;
    Employee employee;
    ParkingSlot slot;

    static int totalRecords = 0;

    CompanyEmployeeRecord(String name, String empId,
                          Employee employee) {

        this.name = name;
        this.empId = empId;
        this.employee = employee;

        totalRecords++;
    }

    String fullProfile() {

        String slotNumber;

        if (slot == null)
            slotNumber = "no parking assigned";
        else
            slotNumber = slot.slotNo;

        double pay;

        if (employee instanceof ManagerEmployee)
            pay = ((ManagerEmployee) employee).effectiveSalary();
        else if (employee instanceof InternEmployee)
            pay = ((InternEmployee) employee).effectiveSalary();
        else
            pay = employee.getSalary();

        return name + " | Pay: Rs " + pay
                + " | Slot: " + slotNumber;
    }

    public static void main(String[] args) {

        ParkingSlot slot1 =
                new ParkingSlot("A1", 1, 0);

        ParkingSlot slot2 =
                new ParkingSlot("A2", 1, 0);

        Employee e1 =
                new ManagerEmployee("E101", "Divya", 70000, 8000);

        Employee e2 =
                new Employee("E102", "Karan", 40000);

        Employee e3 =
                new InternEmployee("E103", "Meera", 12000, 10000);

        CompanyEmployeeRecord r1 =
                new CompanyEmployeeRecord("Divya", "E101", e1);

        CompanyEmployeeRecord r2 =
                new CompanyEmployeeRecord("Karan", "E102", e2);

        CompanyEmployeeRecord r3 =
                new CompanyEmployeeRecord("Meera", "E103", e3);

        ParkingSlot[] slots = {slot1, slot2};

        r1.slot = ParkingSlot.findAvailableSlot(slots);
        if (r1.slot != null)
            r1.slot.allot(r1.empId);

        r2.slot = ParkingSlot.findAvailableSlot(slots);
        if (r2.slot != null)
            r2.slot.allot(r2.empId);

        r1.fullProfile();
        System.out.println(r1.fullProfile());
        System.out.println(r2.fullProfile());
        System.out.println(r3.fullProfile());

        System.out.println("Total records: "
                + CompanyEmployeeRecord.totalRecords);
    }
}

class Employee {

    private String empId;
    private String empName;
    private double salary;

    Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    double getSalary() {
        return salary;
    }
}

class ManagerEmployee extends Employee {

    private double teamBonus;

    ManagerEmployee(String empId, String empName,
                    double salary, double teamBonus) {

        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class InternEmployee extends Employee {

    private double stipendCap;

    InternEmployee(String empId, String empName,
                   double salary, double stipendCap) {

        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}

class ParkingSlot {

    String slotNo;
    int capacity;
    int occupiedCount;

    ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    void allot(String vehicleNo) {
        if (occupiedCount < capacity)
            occupiedCount++;
    }

    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {

        for (ParkingSlot slot : slots) {
            if (slot.occupiedCount < slot.capacity)
                return slot;
        }

        return null;
    }
}
