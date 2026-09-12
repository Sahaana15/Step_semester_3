package OOPInheritanceandPolymorphism.assignment_problems;
class RaceEntry {

    private static int bibCounter = 0;

    protected String bibNumber;
    protected double entryFee;
    protected double balanceDue;

    public final String entryCode;

    public RaceEntry(String bibNumber, double entryFee) {

        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException();
        }

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.balanceDue = entryFee;

        bibCounter++;

        entryCode = "ENT-" + bibCounter;
    }

    public void pay(double amount) {
        balanceDue -= amount;
    }

    public void pay(double amount, String mode) {
        System.out.println("Paying via " + mode);
        pay(amount);
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    public static boolean isValidDiscountCode(String code) {

        if (code == null || code.length() != 5) {
            return false;
        }

        if (code.charAt(0) != 'M') {
            return false;
        }

        if (!Character.isDigit(code.charAt(1))) {
            return false;
        }

        if (!Character.isDigit(code.charAt(2))) {
            return false;
        }

        if (!Character.isDigit(code.charAt(3))) {
            return false;
        }

        if (!Character.isUpperCase(code.charAt(4))) {
            return false;
        }

        return true;
    }

    public static int getBibCounter() {
        return bibCounter;
    }
}

class RunnerEntry extends RaceEntry {

    public RunnerEntry(String bibNumber, double entryFee,
                       String category) {
        super(bibNumber, entryFee);
    }
}

class EliteRunnerEntry extends RunnerEntry {

    public EliteRunnerEntry(String bibNumber, double entryFee,
                            String category, double sponsorBonus) {
        super(bibNumber, entryFee, category);
    }
}

class RelayTeamEntry extends RaceEntry {

    private int teamSize;

    public RelayTeamEntry(String bibNumber, double entryFee,
                          int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }
}

public class RaceWideBibIssuanceDiscountCodesNightlySettlementEngine {

    public static String settleNight(RaceEntry[] entries) {

        int processed = 0;
        int nullSkipped = 0;
        int relay = 0;
        int individual = 0;

        for (RaceEntry entry : entries) {

            if (entry == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (entry instanceof RelayTeamEntry) {
                relay++;
            } else {
                individual++;
            }
        }

        return processed + " processed | " +
                nullSkipped + " null skipped | " +
                relay + " relay | " +
                individual + " individual";
    }

    public static void main(String[] args) {

        System.out.println(
                RaceEntry.isValidDiscountCode("M123A")
        );

        System.out.println(
                RaceEntry.isValidDiscountCode("M12A")
        );

        System.out.println(
                RaceEntry.isValidDiscountCode("X123A")
        );

        RaceEntry r =
                new RaceEntry("BIB5001", 50);

        r.pay(10, "UPI");

        EliteRunnerEntry elite =
                new EliteRunnerEntry(
                        "BIB3001", 150,
                        "Elite Full Marathon", 500
                );

        RelayTeamEntry relay =
                new RelayTeamEntry(
                        "BIB4001", 300, 4
                );

        RaceEntry[] entries = {
                elite, null, relay
        };

        System.out.println(
                settleNight(entries)
        );

        System.out.println(
                RaceEntry.getBibCounter()
        );
    }
}
