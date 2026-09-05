package OOPFundamental.assignment_problems;
public class Employee {

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

    public static void main(String[] args) {

        Employee plain =
                new Employee("E101", "Ravi", 40000);

        ManagerEmployee manager =
                new ManagerEmployee("E102", "Divya", 70000, 8000);

        InternEmployee intern =
                new InternEmployee("E103", "Meera", 12000, 10000);

        Employee[] employees = {plain, manager, intern};

        for (Employee employee : employees) {

            if (employee instanceof ManagerEmployee) {
                System.out.println("Manager effective pay: Rs "
                        + ((ManagerEmployee) employee).effectiveSalary());

            } else if (employee instanceof InternEmployee) {
                System.out.println("Intern effective pay: Rs "
                        + ((InternEmployee) employee).effectiveSalary());

            } else {
                System.out.println("Plain employee pay: Rs "
                        + employee.getSalary());
            }
        }
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
