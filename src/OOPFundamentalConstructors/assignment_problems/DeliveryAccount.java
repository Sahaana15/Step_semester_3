package OOPFundamentalConstructors.assignment_problems;
public class DeliveryAccount {

    protected String studentId;
    protected double orderValue;

    static int processedCount;

    static {
        processedCount = 0;
    }

    public DeliveryAccount(String studentId, double orderValue) {
        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0);
    }

    public final double calculateSurgeFee(int delayMinutes) {

        if (delayMinutes < 0) {
            throw new IllegalArgumentException("Invalid delay");
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        double fee = 0.0;

        int first = Math.min(delayMinutes, 5);
        fee += first * orderValue * 0.005;

        if (delayMinutes > 5) {
            int second = Math.min(delayMinutes - 5, 10);
            fee += second * orderValue * 0.01;
        }

        if (delayMinutes > 15) {
            int third = delayMinutes - 15;
            fee += third * orderValue * 0.02;
        }

        double minimumFee = orderValue * 0.01;

        return Math.max(fee, minimumFee);
    }

    void processAccount(DeliveryAccount account,
                        double amount,
                        int delayMinutes) {

        if (account == null) {
            return;
        }

        double surgeFee = account.calculateSurgeFee(delayMinutes);

        if (account instanceof Premium) {
            surgeFee = surgeFee * 0.5;
        }

        System.out.println("Student: " + account.studentId);
        System.out.println("Surge Fee: Rs " + surgeFee);

        processedCount++;
    }

    static void processBatch(DeliveryAccount[] accounts,
                             double[] amounts,
                             int[] delayMinutesArray) {

        if (accounts == null ||
                amounts == null ||
                delayMinutesArray == null ||
                accounts.length != amounts.length ||
                accounts.length != delayMinutesArray.length) {

            throw new IllegalArgumentException(
                    "Array lengths must match");
        }

        int nullSkipped = 0;
        int premiumCount = 0;
        int regularCount = 0;
        double totalSurgeFee = 0;

        for (int i = 0; i < accounts.length; i++) {

            DeliveryAccount account = accounts[i];

            if (account == null) {
                nullSkipped++;
                continue;
            }

            double fee =
                    account.calculateSurgeFee(
                            delayMinutesArray[i]);

            if (account instanceof Premium) {
                fee = fee * 0.5;
                premiumCount++;
            } else {
                regularCount++;
            }

            account.processAccount(
                    account,
                    amounts[i],
                    delayMinutesArray[i]);

            totalSurgeFee += fee;
        }

        System.out.println();

        System.out.println(
                processedCount + " processed | " +
                        nullSkipped + " null skipped | " +
                        premiumCount + " premium | " +
                        regularCount + " regular");

        System.out.println(
                "Grand total surge fees = Rs " +
                        totalSurgeFee);
    }

    public static void main(String[] args) {

        DeliveryAccount[] accounts = {
                new Premium("STU001", 500),
                null,
                new DeliveryAccount("STU002", 300)
        };

        double[] amounts = {
                500, 400, 300
        };

        int[] delayMinutesArray = {
                10, 5, 0
        };

        processBatch(
                accounts,
                amounts,
                delayMinutesArray);
    }
}

class Premium extends DeliveryAccount {

    public Premium(String studentId, double orderValue) {
        super(studentId, orderValue);
    }

    public Premium(String studentId) {
        super(studentId);
    }
}
