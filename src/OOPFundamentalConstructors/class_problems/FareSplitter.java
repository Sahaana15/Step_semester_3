package OOPFundamentalConstructors.class_problems;
public class FareSplitter {

    private String tripId;
    private double totalFare;
    private int passengerCount;

    public FareSplitter(String tripId,
                        double totalFare,
                        int passengerCount) {

        if (totalFare < 0 || passengerCount <= 0) {
            throw new IllegalArgumentException("Invalid values");
        }

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 2);
    }

    public FareSplitter(String tripId) {
        this(tripId, 0.0, 2);
    }

    double[] fareBreakdown() {

        double[] result = new double[passengerCount];

        double share = Math.floor((totalFare / passengerCount) * 100) / 100;
        double remaining = totalFare;

        for (int i = 0; i < passengerCount - 1; i++) {
            result[i] = share;
            remaining -= share;
        }

        result[passengerCount - 1] =
                Math.round(remaining * 100) / 100.0;

        return result;
    }

    boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }

    public static void main(String[] args) {

        FareSplitter f =
                new FareSplitter("TRIP001", 100000, 3);

        double[] result = f.fareBreakdown();

        for (double x : result) {
            System.out.println(x);
        }

        System.out.println(
                new FareSplitter("TRIP003").fareBreakdown()[0]);
    }
}
