package OOPInheritanceandPolymorphism.assignment_problems;
class RaceEntry {
    protected String bibNumber;
    protected double balanceDue;

    public RaceEntry(String bibNumber, double entryFee) {
        this.bibNumber = bibNumber;
        this.balanceDue = entryFee;
    }

    public String announce() {
        return "Race Entry | Bib: " + bibNumber +
                " | Balance: " + balanceDue;
    }
}

class RunnerEntry extends RaceEntry {
    private String category;

    public RunnerEntry(String bibNumber, double entryFee,
                       String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    @Override
    public String announce() {
        return "Runner Entry | Bib: " + bibNumber +
                " | Category: " + category +
                " | Balance: " + balanceDue;
    }
}

class RelayTeamEntry extends RaceEntry {
    private int teamSize;

    public RelayTeamEntry(String bibNumber, double entryFee,
                          int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }

    public int getTeamSize() {
        return teamSize;
    }

    @Override
    public String announce() {
        return "Relay Team | Bib: " + bibNumber +
                " | Team Size: " + teamSize +
                " | Balance: " + balanceDue;
    }
}

public class RaceDayAnnouncerBoard {

    public static String announceAll(RaceEntry[] entries) {

        StringBuilder result = new StringBuilder();

        for (RaceEntry entry : entries) {

            result.append(entry.announce());

            if (entry instanceof RelayTeamEntry) {

                RelayTeamEntry relay =
                        (RelayTeamEntry) entry;

                result.append(
                        " [Team size via downcast: "
                );

                result.append(
                        relay.getTeamSize()
                );

                result.append("]");
            }

            result.append(" | ");
        }

        return result.toString();
    }

    public static void main(String[] args) {

        RunnerEntry runner =
                new RunnerEntry(
                        "BIB2001", 90, "Open 10K"
                );

        RelayTeamEntry relay =
                new RelayTeamEntry(
                        "BIB4001", 300, 4
                );

        RaceEntry[] entries = {
                runner, relay
        };

        System.out.println(
                announceAll(entries)
        );
    }
}
