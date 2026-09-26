package AbstractionandInterface.assignment_problems;
interface Exportable {
    String exportData();
}

class ReportGenerator implements Exportable {

    private String reportName;
    private static int totalExports = 0;

    public ReportGenerator(String reportName) {
        this.reportName = reportName;
    }

    @Override
    public String exportData() {
        totalExports++;
        return "Exported report: " + reportName;
    }

    public static int getTotalExports() {
        return totalExports;
    }
}

class UserProfile implements Exportable {

    private String username;
    private static int totalExports = 0;

    public UserProfile(String username) {
        this.username = username;
    }

    @Override
    public String exportData() {
        totalExports++;
        return "Exported profile: " + username;
    }

    public static int getTotalExports() {
        return totalExports;
    }
}

public class OneClickDataExport {

    private static int totalExports = 0;

    static void exportAll(Exportable[] items) {

        for (Exportable item : items) {
            System.out.println(item.exportData());
            totalExports++;
        }
    }

    static int getTotalExports() {
        return totalExports;
    }

    public static void main(String[] args) {

        ReportGenerator r =
                new ReportGenerator("Sales Q1");

        System.out.println(r.exportData());

        UserProfile u =
                new UserProfile("jane_doe");

        System.out.println(u.exportData());

        // Upcasting
        Exportable ref = r;

        exportAll(new Exportable[]{ref, u});

        System.out.println("Total exports: " + getTotalExports());
    }
}
