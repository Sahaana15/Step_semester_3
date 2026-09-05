package OOPFundamentalConstructors.class_problems;
public class BusTicketAccount {

    protected String bookingId;
    protected double ticketFare;
    protected double amountPaid;

    static int processedCount;

    static {
        processedCount = 0;
    }

    public BusTicketAccount(String bookingId, double ticketFare) {
        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
        this.amountPaid = 0;
    }

    public BusTicketAccount(String bookingId) {
        this(bookingId, 0);
    }

    public final double calculatePenalty(int minutesLate) {

        if (minutesLate < 0) {
            throw new IllegalArgumentException("Invalid minutes");
        }

        return minutesLate * ticketFare * 0.01;
    }

    void processAccount(BusTicketAccount account,
                        double amount,
                        int minutesLate) {

        if (account == null) {
            return;
        }

        amountPaid += amount;

        double penalty = calculatePenalty(minutesLate);

        System.out.println("Booking: " + bookingId);
        System.out.println("Amount: Rs " + amount);
        System.out.println("Penalty: Rs " + penalty);

        processedCount++;
    }

    static void processBatch(BusTicketAccount[] accounts,
                             double[] amounts,
                             int[] minutesLateArray) {

        if (accounts == null ||
                amounts == null ||
                minutesLateArray == null ||
                accounts.length != amounts.length ||
                accounts.length != minutesLateArray.length) {

            throw new IllegalArgumentException(
                    "Array lengths must match");
        }

        int nullCount = 0;
        int sleeperCount = 0;
        int regularCount = 0;
        double totalPenalty = 0;

        for (int i = 0; i < accounts.length; i++) {

            BusTicketAccount account = accounts[i];

            if (account == null) {
                nullCount++;
                continue;
            }

            double penalty =
                    account.calculatePenalty(minutesLateArray[i]);

            account.processAccount(
                    account,
                    amounts[i],
                    minutesLateArray[i]);

            totalPenalty += penalty;

            if (account instanceof SleeperAccount) {
                sleeperCount++;
            } else {
                regularCount++;
            }
        }

        System.out.println();
        System.out.println(processedCount +
                " processed | " +
                nullCount + " null skipped | " +
                sleeperCount + " sleeper | " +
                regularCount + " regular");

        System.out.println(
                "Grand total penalties = Rs " + totalPenalty);
    }

    public static void main(String[] args) {

        BusTicketAccount[] accounts = {
                new SleeperAccount("BK001", 2000),
                null,
                new BusTicketAccount("BK002", 1200)
        };

        double[] amounts = {
                1200, 900, 700
        };

        int[] minutesLate = {
                10, 5, 0
        };

        processBatch(accounts, amounts, minutesLate);
    }
}

class SleeperAccount extends BusTicketAccount {

    public SleeperAccount(String bookingId, double ticketFare) {
        super(bookingId, ticketFare);
    }

    public SleeperAccount(String bookingId) {
        super(bookingId);
    }
}
