package OOPInheritanceandPolymorphism.assignment_problems;
class RaceEntry {
    protected double entryFee;
    protected double balanceDue;

    private double[] lateFeeHistory;
    private int feeCount;

    public RaceEntry(String bibNumber, double entryFee) {
        this.entryFee = entryFee;
        this.balanceDue = entryFee;
        this.lateFeeHistory = new double[10];
        this.feeCount = 0;
    }

    public void pay(double amount) {
        balanceDue -= amount;
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    protected void applyLateFee(double amount) {
        balanceDue += amount;

        lateFeeHistory[feeCount] = amount;
        feeCount++;
    }

    public double[] getLateFeeHistory() {
        double[] copy = new double[feeCount];

        for (int i = 0; i < feeCount; i++) {
            copy[i] = lateFeeHistory[i];
        }

        return copy;
    }
}

class RunnerEntry extends RaceEntry {

    public RunnerEntry(String bibNumber, double entryFee,
                       String category) {
        super(bibNumber, entryFee);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class LateWithdrawalPenaltyOverrideAuditTrail {

    public static void main(String[] args) {

        RunnerEntry r =
                new RunnerEntry(
                        "BIB2001", 80, "Open 10K"
                );

        r.pay(30);

        r.applyLateFee(20);

        System.out.println(
                r.getBalanceDue()
        );

        double[] history =
                r.getLateFeeHistory();

        history[0] = 999;

        double[] newHistory =
                r.getLateFeeHistory();

        System.out.println(newHistory[0]);
    }
}
